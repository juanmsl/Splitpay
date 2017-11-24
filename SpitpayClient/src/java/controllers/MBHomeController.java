/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import integration.splitpaysoap.Grupo;
import integration.splitpaysoap.RolTypes;
import integration.splitpaysoap.Usuario;
import integration.splitpaysoap.Usuariogrupo;
import integration.splitpaysoap.WSSplitpay;
import integration.splitpaysoap.WSSplitpay_Service;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.AfterBegin;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.persistence.PostLoad;
import javax.persistence.Transient;
import javax.swing.JOptionPane;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author luisd
 */
@Named(value = "mBHomeController")
@SessionScoped
public class MBHomeController implements Serializable {

    private String nombreUsuario;
    private String gruposSeleccionados;
    private List<SelectItem> listaGrupos;
    private String groupname;
    private Grupo grupo;

    @Inject
    MBIndexController index;

    public MBHomeController() {
    }

    public String getGruposSeleccionados() {
        return gruposSeleccionados;
    }

    public void setGruposSeleccionados(String gruposSeleccionados) {
        this.gruposSeleccionados = gruposSeleccionados;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public MBIndexController getIndex() {
        return index;
    }

    public void setIndex(MBIndexController index) {
        this.index = index;
    }

    public List<SelectItem> getNombreGrupo() {
        return listaGrupos;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public List<SelectItem> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(List<SelectItem> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public void preRenderView() {
        List<Usuariogrupo> groups = getGroups(index.getUser());
        listaGrupos = new ArrayList<>();
        for (Usuariogrupo group : groups) {
            if (group.getGrupo()!= null) {
                String groupName = group.getGrupo().getNombre();
                listaGrupos.add(new SelectItem(groupName));
            }
        }
    }

    public String createGroup() {
        Grupo grupo = new Grupo();
        grupo.setNombre(getGroupname());
        List<Usuario> lider = new ArrayList<>();
        lider.add(index.getUser());
        grupo = createGroup(grupo);
        if (grupo != null) {
            setGrupo(grupo);
            if (addMembers(lider, grupo, RolTypes.LIDER)) {
                return "grupos";
            }
        }
        setGroupname("");
        return "home";
    }

    public String mostrarGrupo() {
        List<Usuariogrupo> groups = getGroups(index.getUser());
        for(Usuariogrupo group : groups) {
            if(group.getGrupo().getNombre().equals(getGruposSeleccionados())) {
                grupo = group.getGrupo();
                return "grupos";
            }
        }
        return "home";
    }

    public List<Usuariogrupo> getGroups(Usuario user) {
        WSSplitpay_Service service = new WSSplitpay_Service();
        WSSplitpay port = service.getWSSplitpayPort();
        return port.getGroups(user);
    }

    private Grupo createGroup(Grupo group) {
        WSSplitpay_Service service = new WSSplitpay_Service();
        WSSplitpay port = service.getWSSplitpayPort();
        return port.createGroup(group);
    }

    private boolean addMembers(List<Usuario> members, Grupo grupo, RolTypes type) {
        WSSplitpay_Service service = new WSSplitpay_Service();
        WSSplitpay port = service.getWSSplitpayPort();
        return port.addMembers(members, grupo, type);
    }

}
