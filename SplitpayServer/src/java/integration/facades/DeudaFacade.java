/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integration.facades;

import entities.Deuda;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sala-bd
 */
@Stateless
public class DeudaFacade extends AbstractFacade<Deuda> implements DeudaFacadeRemote {

    @PersistenceContext(unitName = "SplitpayServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DeudaFacade() {
        super(Deuda.class);
    }
    
}
