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
import entities.Usuariogrupo;
import enums.RolTypes;
import facades.SplitpayFacadeSOAP;
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
    private SplitpayFacadeSOAP ejbRef;// Add business logic below. (Right-click in editor and choose
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
    public boolean postingBill(@WebParam(name = "debt") Deuda debt, @WebParam(name = "users") List<Usuario> users) {
        return ejbRef.postingBill(debt, users);
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
    public boolean addMembers(@WebParam(name = "members") List<Usuario> members, @WebParam(name = "grupo") Grupo grupo, @WebParam(name = "type") RolTypes type) {
        return ejbRef.addMembers(members, grupo, type);
    }

    @WebMethod(operationName = "getGroups")
    public List<Usuariogrupo> getGroups(@WebParam(name = "user") Usuario user) {
        return ejbRef.getGroups(user);
    }

    @WebMethod(operationName = "getUsersGroup")
    public List<Usuariogrupo> getUsersGroup(@WebParam(name = "grupo") Grupo grupo) {
        return ejbRef.getUsersGroup(grupo);
    }

    @WebMethod(operationName = "getUsersNotInGroup")
    public List<Usuario> getUsersNotInGroup(@WebParam(name = "grupo") Grupo grupo) {
        return ejbRef.getUsersNotInGroup(grupo);
    }

    @WebMethod(operationName = "getDebtByGroup")
    public List<Deuda> getDebtByGroup(@WebParam(name = "group") Grupo group) {
        return ejbRef.getDebtByGroup(group);
    }

    @WebMethod(operationName = "getRol")
    public String getRol(@WebParam(name = "group") Grupo group, @WebParam(name = "user") Usuario user) {
        return ejbRef.getRol(group, user);
    }
    
}
