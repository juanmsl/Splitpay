/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import controllers.PaymentController;
import entities.Pago;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author sala_a
 */
@Stateless
public class FacadeTransactions {

    @EJB
    private PaymentController paymentController;

    public List<Pago> findByDate(Date date) {
        return paymentController.findByDate(date);
    }
    
    public List<Pago> findByDocument(String document) {
        return paymentController.findByDocument(document);
    }
}
