/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Deuda;
import entities.Pago;
import javax.ejb.Remote;

/**
 *
 * @author sala-bd
 */
@Remote
public interface SplitpayFacadeSOAPRemote {

    boolean postingBill(Deuda debt);

    boolean doPayment(Pago payment);
    
}
