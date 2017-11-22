package logic;

import entities.Deuda;
import entities.Grupo;
import entities.Usuario;
import entities.Usuariodeuda;
import java.math.BigInteger;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class ControllerFinalDebt {

    public boolean finalDebtResolution(Grupo group) {
        for(Deuda deuda : group.getDeudaList()) {
            for(Usuariodeuda usuariodeuda : deuda.getUsuariodeudaList()) {
                Usuario usuario = usuariodeuda.getUsuario();
                BigInteger monto = usuariodeuda.getMonto();
                // TODO: Completar posting
            }
        }
        return false;
    }
    
}
