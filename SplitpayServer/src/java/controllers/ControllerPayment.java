package controllers;

import entities.Pago;
import integration.facades.PagoFacadeRemote;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class ControllerPayment {

    @EJB
    private PagoFacadeRemote pagoFacade;

    public boolean doPayment(Pago payment) {
        pagoFacade.create(payment);
        return true;
    }
    
}
