package controllers;

import entities.Pago;
import entities.Pagonomina;
import integration.facades.PagoFacadeRemote;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Topic;

@Stateless
@LocalBean
public class ControllerPayment {

    @Resource(mappedName = "jms/splitpayTopic")
    private Topic splitpayTopic;

    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;

    @EJB
    private PagoFacadeRemote pagoFacade;

    public boolean doPayment(Pago payment) {
        pagoFacade.create(payment);
        Pagonomina pago = new Pagonomina();
        pago.setDeudaId(payment.getDeudaId().getId().toBigInteger());
        pago.setFecha(payment.getFecha());
        pago.setMonto(payment.getMonto());
        pago.setUsuarioId(payment.getUsuarioId().getId().toBigInteger());
        sendJMSMessageToSplitpayTopic(pago);
        return true;
    }

    private void sendJMSMessageToSplitpayTopic(Pagonomina pagonomina) {
        context.createProducer().send(splitpayTopic, pagonomina);
    }
    
}
