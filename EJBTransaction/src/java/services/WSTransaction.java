/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Pago;
import facades.FacadeTransactions;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author sala_a
 */
@WebService(serviceName = "WSTransaction")
@Stateless()
public class WSTransaction {

    @EJB
    private FacadeTransactions ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "findByDate")
    public List<Pago> findByDate(@WebParam(name = "date") Date date) {
        return ejbRef.findByDate(date);
    }

    @WebMethod(operationName = "findByDocument")
    public List<Pago> findByDocument(@WebParam(name = "document") String document) {
        return ejbRef.findByDocument(document);
    }
    
}
