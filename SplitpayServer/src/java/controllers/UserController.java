package controllers;

import entities.Usuario;
import entities.Usuariogrupo;
import integration.facades.UsuarioFacadeRemote;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@LocalBean
public class UserController {

    @EJB
    private UsuarioFacadeRemote usuarioFacade;

    @PersistenceContext(unitName = "SplitpayServerPU")
    private EntityManager em;
    
    public Usuario findByEmail(String email) {
        Query query = em.createNamedQuery("Usuario.findByEmail", Usuario.class);
        try {
            query.setParameter("email", email);
            return (Usuario)query.getSingleResult();
        } catch(Exception e) {
            return null;
        }
    }
    
    public List<Usuariogrupo> findGroupsByUser(BigDecimal id) {
        Query query = em.createNamedQuery("Usuariogrupo.findByUsuarioId", Usuariogrupo.class);
        try {
            query.setParameter("usuarioId", id);
            return (List<Usuariogrupo>)query.getResultList();
        } catch(Exception e) {
            return null;
        }
    }

    public Usuario login(String email, String password) {
        Usuario usuario = findByEmail(email);
        if(usuario != null) {
            if(usuario.getContrasena().equals(password)) {
                return usuario;
            }
        }
        return null;
    }

    public Usuario register(Usuario user) {
        usuarioFacade.create(user);
        return findByEmail(user.getEmail());
    }

    public List<Usuariogrupo> getGroups(Usuario user) {
        List<Usuariogrupo> groups = findGroupsByUser(user.getId());
        return groups;
    }
    
    
}
