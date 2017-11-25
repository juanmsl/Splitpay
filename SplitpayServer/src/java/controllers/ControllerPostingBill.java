/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Deuda;
import entities.Notificacion;
import entities.Usuario;
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

    public boolean postingBill(Deuda debt, List<Usuario> users) {
        debt = calculateAmount(debt);
        deudaFacade.create(debt);
        return notify(users, "Se ha añadido una nueva deuda al grupo " + debt.getNombre());
    }
    
    private Deuda calculateAmount(Deuda debt) {
        BigInteger amount = debt.getCosto().divide(new BigInteger("" + debt.getUsuariodeudaList()));
        for(Usuariodeuda usuariodeuda : debt.getUsuariodeudaList()) {
            usuariodeuda.setMonto(amount);
        }
        return debt;
    }
    
    private boolean notify(List<Usuario> usuarios, String text) {
        for(Usuario usuario : usuarios) {
            Notificacion notificacion = new Notificacion();
            notificacion.setUsuarioId(usuario);
            notificacion.setTexto(text);
            notificacionFacade.create(notificacion);
        }
        return true;
    }
    
}
