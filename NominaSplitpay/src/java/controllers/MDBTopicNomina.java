/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Pagonomina;
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
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "jms/splitpayTopic")
    ,
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/splitpayTopic")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/splitpayTopic")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class MDBTopicNomina implements MessageListener {

    @EJB
    private NominaController nominaController;
    
    public MDBTopicNomina() {
    }
    
    @Override
    public void onMessage(Message message) {
        
        try {
            Pagonomina pago = message.getBody(Pagonomina.class);
            nominaController.insertPayments(pago);
            
        } catch (JMSException ex) {
            Logger.getLogger(MDBTopicNomina.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
