/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Deuda;
import entities.Grupo;
import entities.Pago;
import entities.Usuario;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author sala-bd
 */
@Remote
public interface SplitpayFacadeSOAPRemote {

    Usuario registerUser(Usuario user);

    Usuario loginUser(Usuario user);

    boolean postingBill(Deuda debt);

    boolean doPayment(Pago payment);

    boolean finalDebtResolution(Grupo group);

    Grupo createGroup(Grupo group);

    boolean addMembers(List<Usuario> members);
    
}
