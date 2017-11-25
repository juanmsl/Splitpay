package controllers;

import entities.Deuda;
import entities.Grupo;
import entities.Usuario;
import entities.Usuariogrupo;
import enums.RolTypes;
import integration.facades.GrupoFacadeRemote;
import integration.facades.UsuariogrupoFacadeRemote;
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
public class GroupController {

    @PersistenceContext(unitName = "SplitpayServerPU")
    private EntityManager em;
    
    @EJB
    private UsuariogrupoFacadeRemote usuariogrupoFacade;

    @EJB
    private GrupoFacadeRemote grupoFacade;
    
    public List<Usuariogrupo> findUsersByGroup(BigDecimal id) {
        Query query = em.createNamedQuery("Usuariogrupo.findByGrupoId", Usuariogrupo.class);
        try {
            query.setParameter("grupoId", id);
            return (List<Usuariogrupo>)query.getResultList();
        } catch(Exception e) {
            return null;
        }
    }
    
    public List<Usuario> findUsersNotInGroup(BigDecimal id) {
        Query query = em.createNativeQuery("SELECT * FROM usuario where id not in (SELECT usuario_id FROM Usuariogrupo WHERE grupo_id = ?)", Usuario.class);
        try {
            query.setParameter(1, id);
            return (List<Usuario>)query.getResultList();
        } catch(Exception e) {
            return null;
        }
    }

    public Grupo createGroup(Grupo grupo) {
        grupoFacade.create(grupo);
        List<Grupo> findAll = grupoFacade.findAll();
        BigDecimal maxId = new BigDecimal(0);
        for (Grupo grupo1 : findAll) {
            if(maxId.compareTo(grupo1.getId()) < 0) {
                maxId = grupo1.getId();
            }
        }
        grupo.setId(maxId);
        return grupo;
    }

    public boolean addMember(List<Usuario> users, Grupo grupo, RolTypes type) {
        System.out.println(grupo.getId());
        Grupo group = grupoFacade.find(grupo.getId());
        for (Usuario user : users) {
            Usuariogrupo usuariogrupo = new Usuariogrupo(user.getId().toBigInteger(), group.getId().toBigInteger());
            usuariogrupo.setGrupo(group);
            usuariogrupo.setUsuario(user);
            usuariogrupo.setRol("Lider");
            usuariogrupoFacade.create(usuariogrupo);
        }
        return true;
    }

    public List<Usuariogrupo> getUsersGroup(Grupo grupo) {
        List<Usuariogrupo> groups = findUsersByGroup(grupo.getId());
        return groups;
    }
    
    public List<Usuario> getUsersNotInGroup(Grupo grupo) {
        List<Usuario> groups = findUsersNotInGroup(grupo.getId());
        return groups;
    }

    public List<Deuda> getDebtByGroup(BigDecimal id) {
        Query query = em.createNativeQuery("select * from DEUDA where GRUPO_ID = ?", Deuda.class);
        try {
            query.setParameter(1, id);
            return (List<Deuda>)query.getResultList();
        } catch(Exception e) {
            return null;
        }
    }

    public String getRol(BigDecimal groupId, BigDecimal userId) {
        Query query = em.createNativeQuery("select rol from USUARIOGRUPO where GRUPO_ID = ? and USUARIO_ID = ?", String.class);
        try {
            query.setParameter(1, groupId);
            query.setParameter(2, userId);
            return (String) query.getSingleResult();
        } catch(Exception e) {
            return null;
        }
    }
}
