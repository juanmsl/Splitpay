/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Deuda;
import entities.Notificacion;
import entities.Usuariodeuda;
import integration.facades.DeudaFacadeRemote;
import integration.facades.NotificacionFacadeRemote;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@LocalBean
public class ControllerPostingBill {

    @PersistenceContext(unitName = "SplitpayServerPU")
    private EntityManager em;
    
    @EJB
    private NotificacionFacadeRemote notificacionFacade;

    @EJB
    private DeudaFacadeRemote deudaFacade;

    public boolean postingBill(Deuda debt) {
        debt = calculateAmount(debt);
        deudaFacade.create(debt);
        return notify(getUsuariosDeuda(debt.getId()), "Se ha añadido una nueva deuda al grupo " + debt.getNombre());
    }
    
    private List<Usuariodeuda> getUsuariosDeuda(BigDecimal id) {
        Query query = em.createNamedQuery("Usuariodeuda.findByDeudaId", Usuariodeuda.class);
        try {
            query.setParameter("deudaId", id);
            return (List<Usuariodeuda>)query.getResultList();
        } catch(Exception e) {
            return null;
        }
    }
    
    private Deuda calculateAmount(Deuda debt) {
        BigInteger amount = debt.getCosto().divide(new BigInteger("" + debt.getUsuariodeudaList()));
        for(Usuariodeuda usuariodeuda : debt.getUsuariodeudaList()) {
            usuariodeuda.setMonto(amount);
        }
        return debt;
    }
    
    private boolean notify(List<Usuariodeuda> usuarios, String text) {
        for(Usuariodeuda usuario : usuarios) {
            Notificacion notificacion = new Notificacion();
            notificacion.setUsuarioId(usuario.getUsuario());
            notificacion.setTexto(text);
            notificacionFacade.create(notificacion);
        }
        return true;
    }
    
}
