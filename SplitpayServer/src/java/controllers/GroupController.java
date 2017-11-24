package controllers;

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

@Stateless
@LocalBean
public class GroupController {

    @EJB
    private UsuariogrupoFacadeRemote usuariogrupoFacade;

    @EJB
    private GrupoFacadeRemote grupoFacade;

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
}
