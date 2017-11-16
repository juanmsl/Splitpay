package logic;

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
        // TODO: Conectar a paypal y enviar pago
        // TODO: Enviar a topico
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
