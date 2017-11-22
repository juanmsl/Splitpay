/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integration.facades;

import entities.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author sala_a
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements integration.facades.UsuarioFacadeRemote {

    @PersistenceContext(unitName = "SplitpayServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario findByEmail(String email) {
        Query query = em.createNamedQuery("Usuario.findByEmail", Usuario.class);
        try {
            query.setParameter("email", email);
            return (Usuario)query.getSingleResult();
        } catch(Exception e) {
            return null;
        }
    }

    @Override
    public Usuario login(String email, String password) {
        Usuario usuario = findByEmail(email);
        if(usuario != null) {
            if(usuario.getContrasena().equals(password)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public Usuario register(Usuario user) {
        create(user);
        return findByEmail(user.getEmail());
    }
    
    
}
