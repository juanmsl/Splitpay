package facades;

import controllers.ControllerFinalDebt;
import controllers.ControllerPayment;
import controllers.ControllerPostingBill;
import controllers.GroupController;
import controllers.UserController;
import entities.Deuda;
import entities.Grupo;
import entities.Pago;
import entities.Usuario;
import entities.Usuariogrupo;
import enums.RolTypes;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class SplitpayFacadeSOAP {

    @EJB
    private GroupController groupController;

    @EJB
    private UserController usersController;
    

    @EJB
    private ControllerFinalDebt controllerFinalDebt;

    @EJB
    private ControllerPayment controllerPayment;

    @EJB
    private ControllerPostingBill controllerPostingBill;

    public Usuario registerUser(Usuario user) {
        return usersController.register(user);
    }

    public Usuario loginUser(Usuario user) {
        return usersController.login(user.getEmail(), user.getContrasena());
    }
    
    public boolean postingBill(Deuda debt) {
        return controllerPostingBill.postingBill(debt);
    }

    public boolean doPayment(Pago payment) {
        return  controllerPayment.doPayment(payment);
    }

    public boolean finalDebtResolution(Grupo group) {
        return controllerFinalDebt.finalDebtResolution(group);
    }

    public Grupo createGroup(Grupo group) {
        return groupController.createGroup(group);
    }

    public boolean addMembers(List<Usuario> members, Grupo grupo, RolTypes type) {
        return groupController.addMember(members, grupo, type);
    }
    
    public List<Usuariogrupo> getGroups(Usuario user) {
        return usersController.getGroups(user);
    }
    
    public List<Usuariogrupo> getUsersGroup(Grupo grupo) {
        return groupController.getUsersGroup(grupo);
    }
    
    public List<Usuario> getUsersNotInGroup(Grupo grupo) {
        return groupController.getUsersNotInGroup(grupo);
    }
}