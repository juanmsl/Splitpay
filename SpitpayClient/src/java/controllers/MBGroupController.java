/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import integration.splitpaysoap.Grupo;
import integration.splitpaysoap.RolTypes;
import integration.splitpaysoap.Usuario;
import integration.splitpaysoap.Usuariogrupo;
import integration.splitpaysoap.WSSplitpay;
import integration.splitpaysoap.WSSplitpay_Service;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author luisd
 */
@Named(value = "mBGroupController")
@SessionScoped
public class MBGroupController implements Serializable{

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/server.splitpay.com_8080/WSSplitpay/WSSplitpay.wsdl")
    private WSSplitpay_Service service;

    @Inject
    MBHomeController home;
    
    private List <String> usuariosGrupoSeleccionados;
    private List<SelectItem> listaUsuariosGrupo;
    private List <String> usuariosSeleccionados;
    private List<SelectItem> listaUsuarios;
    private Grupo grupo;
    
    
    public MBGroupController() {
        grupo = home.getGrupo();
    }

    public WSSplitpay_Service getService() {
        return service;
    }

    public void setService(WSSplitpay_Service service) {
        this.service = service;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    
    public MBHomeController getHome() {
        return home;
    }

    public void setHome(MBHomeController home) {
        this.home = home;
    }

    public List<String> getUsuariosGrupoSeleccionados() {
        return usuariosGrupoSeleccionados;
    }

    public void setUsuariosGrupoSeleccionados(List<String> usuariosGrupoSeleccionados) {
        this.usuariosGrupoSeleccionados = usuariosGrupoSeleccionados;
    }

    public List<SelectItem> getListaUsuariosGrupo() {
        return listaUsuariosGrupo;
    }

    public void setListaUsuariosGrupo(List<SelectItem> listaUsuariosGrupo) {
        this.listaUsuariosGrupo = listaUsuariosGrupo;
    }

    public List<String> getUsuariosSeleccionados() {
        return usuariosSeleccionados;
    }

    public void setUsuariosSeleccionados(List<String> usuariosSeleccionados) {
        this.usuariosSeleccionados = usuariosSeleccionados;
    }

    public List<SelectItem> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<SelectItem> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
    public void preRenderView() {
        listaUsuariosGrupo = new ArrayList<>();
        List<Usuariogrupo> groups = getUsersGroup(home.getGrupo());
        for (Usuariogrupo group : groups) {
            if(group.getUsuario() != null) {
                String userName = group.getUsuario().getNombre();
                listaUsuariosGrupo.add(new SelectItem(userName));
            }
        }
        listaUsuarios = new ArrayList<>();
        List<Usuario> notGroup = getUsersNotInGroup(home.getGrupo());
        for (Usuario usuariogrupo : notGroup) {
            String userName = usuariogrupo.getNombre();
            listaUsuarios.add(new SelectItem(userName));
        }
        
    }
    
    public String agregarAlGrupo() {
        List<Usuario> notGroup = getUsersNotInGroup(home.getGrupo());
        List<Usuario> usuariosParaAgregar = new ArrayList<>();
        for (Usuario usuario : notGroup) {
            if(usuariosSeleccionados.contains(usuario.getNombre())) {
                usuariosParaAgregar.add(usuario);
            }
        }
        addMembers(usuariosParaAgregar, home.getGrupo(), RolTypes.MIEMBRO);
        return "grupos";
    }
    
    public String agregarDeuda() {
        return "postingBill";
    }

    private List<Usuariogrupo> getUsersGroup(Grupo group) {
        WSSplitpay_Service service = new WSSplitpay_Service();
        WSSplitpay port = service.getWSSplitpayPort();
        return port.getUsersGroup(group);
    }
    
    private List<Usuario> getUsersNotInGroup(Grupo group) {
        WSSplitpay_Service service = new WSSplitpay_Service();
        WSSplitpay port = service.getWSSplitpayPort();
        return port.getUsersNotInGroup(group);
    }
    
    
    private boolean addMembers(List<Usuario> members, Grupo grupo, RolTypes type) {
        WSSplitpay_Service service = new WSSplitpay_Service();
        WSSplitpay port = service.getWSSplitpayPort();
        return port.addMembers(members, grupo, type);
    }
}
