package controllers;

import entities.Deuda;
import entities.Usuariodeuda;
import entities.Usuariogrupo;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@LocalBean
public class DeudaController {

    @PersistenceContext(unitName = "SplitpayServerPU")
    private EntityManager em;

    public List<Usuariodeuda> getUsersFromDebt(BigDecimal id) {
        Query query = em.createNamedQuery("Usuariodeuda.findByDeudaId", Usuariodeuda.class);
        try {
            query.setParameter("deudaId", id);
            return (List<Usuariodeuda>)query.getResultList();
        } catch(Exception e) {
            return null;
        }
    }

    
}
