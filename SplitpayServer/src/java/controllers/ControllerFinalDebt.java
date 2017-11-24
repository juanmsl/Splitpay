package controllers;

import entities.Deuda;
import entities.Grupo;
import entities.Usuario;
import entities.Usuariodeuda;
import entities.Usuariogrupo;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@LocalBean
public class ControllerFinalDebt {

    @PersistenceContext(unitName = "SplitpayServerPU")
    private EntityManager em;

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
    
    
    public List<Grupo> getDebts(BigDecimal id) {
        Query query = em.createNamedQuery("Usuariogrupo.findByGrupoId", Usuariogrupo.class);
        try {
            query.setParameter("grupoId", id);
            return (List<Usuariogrupo>)query.getResultList();
        } catch(Exception e) {
            return null;
        }
    }
}
