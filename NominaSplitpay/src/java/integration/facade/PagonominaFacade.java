/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integration.facade;

import entities.Pagonomina;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sala_a
 */
@Stateless
public class PagonominaFacade extends AbstractFacade<Pagonomina> {

    @PersistenceContext(unitName = "NominaSplitpayPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagonominaFacade() {
        super(Pagonomina.class);
    }
    
}
