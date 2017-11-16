/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entities.Deuda;
import entities.Notificacion;
import entities.Usuario;
import entities.Usuariodeuda;
import integration.facades.DeudaFacadeRemote;
import integration.facades.NotificacionFacadeRemote;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class ControllerPostingBill {

    @EJB
    private NotificacionFacadeRemote notificacionFacade;

    @EJB
    private DeudaFacadeRemote deudaFacade;

    public boolean postingBill(Deuda debt) {
        debt = calculateAmount(debt);
        deudaFacade.create(debt);
        notify(debt.getUsuariodeudaList());
        return true;
    }
    
    private Deuda calculateAmount(Deuda debt) {
        BigInteger amount = debt.getCosto().divide(new BigInteger("" + debt.getUsuariodeudaList()));
        for(Usuariodeuda usuariodeuda : debt.getUsuariodeudaList()) {
            usuariodeuda.setMonto(amount);
        }
        return debt;
    }
    
    private void notify(List<Usuariodeuda> usuarios) {
        for(Usuariodeuda usuario : usuarios) {
            Notificacion notificacion = new Notificacion();
            notificacion.setUsuarioId(usuario.getUsuario());
            notificacionFacade.create(notificacion);
        }
    }
    
}
