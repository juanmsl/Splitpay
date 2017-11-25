/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Notificacion;
import integration.EmailIntegrator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author sala_a
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/splitpayQueue")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MDBEmailQueue implements MessageListener {

    @EJB
    private EmailIntegrator emailIntegrator;
    
    public MDBEmailQueue() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            Notificacion notificacion = (Notificacion) message.getBody(Notificacion.class);
            emailIntegrator.sendMessage(notificacion.getUsuarioId().getEmail(), notificacion.getTexto());
        } catch (JMSException ex) {
            Logger.getLogger(MDBEmailQueue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
