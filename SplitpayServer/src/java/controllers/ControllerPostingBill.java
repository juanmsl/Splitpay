/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Deuda;
import entities.Grupo;
import entities.Notificacion;
import entities.Usuario;
import entities.Usuariodeuda;
import integration.facades.DeudaFacadeRemote;
import integration.facades.NotificacionFacadeRemote;
import integration.facades.UsuariodeudaFacadeRemote;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@LocalBean
public class ControllerPostingBill {

    @Resource(mappedName = "jms/splitpayQueue")
    private Queue splitpayQueue;

    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;

    @EJB
    private UsuariodeudaFacadeRemote usuariodeudaFacade;

    @PersistenceContext(unitName = "SplitpayServerPU")
    private EntityManager em;
    
    @EJB
    private NotificacionFacadeRemote notificacionFacade;

    @EJB
    private DeudaFacadeRemote deudaFacade;

    public boolean postingBill(Deuda debt, List<Usuario> users) {
        deudaFacade.create(debt);
        List<Deuda> findAll = deudaFacade.findAll();
        BigDecimal maxId = new BigDecimal(0);
        for (Deuda deuda1 : findAll) {
            if(maxId.compareTo(deuda1.getId()) < 0) {
                maxId = deuda1.getId();
            }
        }
        debt.setId(maxId);
        calculateAmount(debt, users);
        System.out.println("Sali");
        return notify(users, "Se ha añadido una nueva deuda, " + debt.getNombre());
    }
    
    private boolean calculateAmount(Deuda debt, List<Usuario> users) {
        BigInteger amount = debt.getCosto().divide(new BigInteger(users.size() + ""));
        System.out.println("Amount: " + amount + " / " + debt.getCosto());
        for(Usuario user : users) {
            System.out.println(debt.getId());
            Usuariodeuda usuariodeuda = new Usuariodeuda(user.getId().toBigInteger(), debt.getId().toBigInteger());
            usuariodeuda.setDeuda(debt);
            usuariodeuda.setUsuario(user);
            usuariodeuda.setMonto(amount);
            usuariodeudaFacade.create(usuariodeuda);
        }
        return true;
    }
    
    private boolean notify(List<Usuario> usuarios, String text) {
        for(Usuario usuario : usuarios) {
            Notificacion notificacion = new Notificacion();
            notificacion.setUsuarioId(usuario);
            notificacion.setTexto(text);
            notificacionFacade.create(notificacion);
            sendJMSMessageToSplitpayQueue(notificacion);
        }
        return true;
    }

    private void sendJMSMessageToSplitpayQueue(Notificacion notificacion) {
        context.createProducer().send(splitpayQueue, notificacion);
    }
    
}
