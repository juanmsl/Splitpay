/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integration.facades;

import entities.Grupo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sala-bd
 */
@Stateless
public class GrupoFacade extends AbstractFacade<Grupo> implements integration.facades.GrupoFacadeRemote {

    @PersistenceContext(unitName = "SplitpayServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoFacade() {
        super(Grupo.class);
    }
    
}
