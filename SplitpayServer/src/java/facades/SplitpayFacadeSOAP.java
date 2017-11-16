package facades;

import logic.ControllerPostingBill;
import entities.Deuda;
import entities.Pago;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import logic.ControllerPayment;

@Stateless
public class SplitpayFacadeSOAP implements SplitpayFacadeSOAPRemote {

    @EJB
    private ControllerPayment controllerPayment;

    @EJB
    private ControllerPostingBill controllerPostingBill;
    

    @Override
    public boolean postingBill(Deuda debt) {
        return controllerPostingBill.postingBill(debt);
    }

    @Override
    public boolean doPayment(Pago payment) {
        return  controllerPayment.doPayment(payment);
    }
    
    
}