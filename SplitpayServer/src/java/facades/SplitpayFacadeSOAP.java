package facades;

import logic.ControllerPostingBill;
import entities.Deuda;
import entities.Grupo;
import entities.Pago;
import entities.Usuario;
import entities.Usuariodeuda;
import integration.facades.UsuarioFacadeRemote;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import logic.ControllerFinalDebt;
import logic.ControllerPayment;

@Stateless
public class SplitpayFacadeSOAP implements SplitpayFacadeSOAPRemote {

    @EJB
    private ControllerFinalDebt controllerFinalDebt;

    @EJB
    private UsuarioFacadeRemote usuarioFacade;

    @EJB
    private ControllerPayment controllerPayment;

    @EJB
    private ControllerPostingBill controllerPostingBill;

    @Override
    public Usuario registerUser(Usuario user) {
        return usuarioFacade.register(user);
    }

    @Override
    public Usuario loginUser(Usuario user) {
        return usuarioFacade.login(user.getEmail(), user.getContrasena());
    }
    
    @Override
    public boolean postingBill(Deuda debt) {
        return controllerPostingBill.postingBill(debt);
    }

    @Override
    public boolean doPayment(Pago payment) {
        return  controllerPayment.doPayment(payment);
    }

    @Override
    public boolean finalDebtResolution(Grupo group) {
        return controllerFinalDebt.finalDebtResolution(group);
    }

    @Override
    public Grupo createGroup(Grupo group) {
        return null;
    }

    @Override
    public boolean addMembers(List<Usuario> members) {
        return false;
    }
    
    
}