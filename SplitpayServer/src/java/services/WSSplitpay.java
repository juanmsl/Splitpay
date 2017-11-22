/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Deuda;
import entities.Grupo;
import entities.Pago;
import entities.Usuario;
import facades.SplitpayFacadeSOAPRemote;
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
@WebService(serviceName = "WSSplitpay")
@Stateless()
public class WSSplitpay {

    @EJB
    private SplitpayFacadeSOAPRemote ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "registerUser")
    public Usuario registerUser(@WebParam(name = "user") Usuario user) {
        return ejbRef.registerUser(user);
    }

    @WebMethod(operationName = "loginUser")
    public Usuario loginUser(@WebParam(name = "user") Usuario user) {
        return ejbRef.loginUser(user);
    }

    @WebMethod(operationName = "postingBill")
    public boolean postingBill(@WebParam(name = "debt") Deuda debt) {
        return ejbRef.postingBill(debt);
    }

    @WebMethod(operationName = "doPayment")
    public boolean doPayment(@WebParam(name = "payment") Pago payment) {
        return ejbRef.doPayment(payment);
    }

    @WebMethod(operationName = "finalDebtResolution")
    public boolean finalDebtResolution(@WebParam(name = "group") Grupo group) {
        return ejbRef.finalDebtResolution(group);
    }

    @WebMethod(operationName = "createGroup")
    public Grupo createGroup(@WebParam(name = "group") Grupo group) {
        return ejbRef.createGroup(group);
    }

    @WebMethod(operationName = "addMembers")
    public boolean addMembers(@WebParam(name = "members") List<Usuario> members) {
        return ejbRef.addMembers(members);
    }
    
}
