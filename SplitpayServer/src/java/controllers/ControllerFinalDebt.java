package controllers;

import entities.Deuda;
import entities.Grupo;
import entities.Notificacion;
import entities.Usuario;
import entities.Usuariodeuda;
import entities.Usuariogrupo;
import integration.facades.NotificacionFacadeRemote;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@LocalBean
public class ControllerFinalDebt {

    @EJB
    private NotificacionFacadeRemote notificacionFacade;

    @EJB
    private DeudaController deudaController;

    @EJB
    private GroupController groupController;

    @PersistenceContext(unitName = "SplitpayServerPU")
    private EntityManager em;

    public boolean finalDebtResolution(Grupo group) {
        List<Deuda> debtByGroup = groupController.getDebtByGroup(group.getId());
        for(Deuda deuda : debtByGroup) {
            List<Usuariodeuda> usersFromDebt = deudaController.getUsersFromDebt(deuda.getId());
            for(Usuariodeuda usuariodeuda : usersFromDebt) {
                notify(usuariodeuda, "Debes pagar esta deuda de " + usuariodeuda.getMonto() + " porque se cerrara el grupo");
            }
        }
        return false;
    }
    
     private boolean notify(Usuariodeuda usuario, String text) {
        Notificacion notificacion = new Notificacion();
        notificacion.setUsuarioId(usuario.getUsuario());
        notificacion.setTexto(text);
        notificacionFacade.create(notificacion);
        return true;
    }
}
