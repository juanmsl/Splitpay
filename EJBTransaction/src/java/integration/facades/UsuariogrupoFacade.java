/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integration.facades;

import entities.Usuariogrupo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sala_a
 */
@Stateless
public class UsuariogrupoFacade extends AbstractFacade<Usuariogrupo> {

    @PersistenceContext(unitName = "EJBTransactionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariogrupoFacade() {
        super(Usuariogrupo.class);
    }
    
}
