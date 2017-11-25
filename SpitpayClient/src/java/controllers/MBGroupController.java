/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import integration.splitpaysoap.Deuda;
import integration.splitpaysoap.Grupo;
import integration.splitpaysoap.RolTypes;
import integration.splitpaysoap.Usuario;
import integration.splitpaysoap.Usuariogrupo;
import integration.splitpaysoap.WSSplitpay;
import integration.splitpaysoap.WSSplitpay_Service;
import java.io.Serializable;
import java.math.BigDecimal;
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
    @Inject
    MBIndexController index;
    
    
    transient private List <String> usuariosGrupoSeleccionados;
    transient private List<SelectItem> listaUsuariosGrupo;
    transient private List <String> usuariosSeleccionados;
    transient private List<SelectItem> listaUsuarios;
    transient private List<SelectItem> listaDeudas;
    transient private String deudas;
    private Grupo grupo;
    private Deuda deuda;
    private String result;
    
    
    public MBGroupController() {
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    

    public List<SelectItem> getListaDeudas() {
        return listaDeudas;
    }

    public Deuda getDeuda() {
        return deuda;
    }

    public void setDeuda(Deuda deuda) {
        this.deuda = deuda;
    }
    

    public void setListaDeudas(List<SelectItem> listaDeudas) {
        this.listaDeudas = listaDeudas;
    }

    public String getDeudas() {
        return deudas;
    }

    public void setDeudas(String deudas) {
        this.deudas = deudas;
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

    public MBIndexController getIndex() {
        return index;
    }

    public void setIndex(MBIndexController index) {
        this.index = index;
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
        listaDeudas = new ArrayList<>();
        List<Deuda> debts = getDebtByGroup(home.getGrupo());
        for (Deuda debt : debts) {
            String debtName = debt.getNombre();
            BigDecimal debtId = debt.getId();
            String deudaLista = debtId + " - " + debtName;
            listaDeudas.add(new SelectItem(deudaLista));
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
    
    public String pagarDeuda() {
        List<Deuda> deudas = getDebtByGroup(home.getGrupo());
        for (Deuda debt : deudas) {
            String debtName = debt.getNombre();
            BigDecimal debtId = debt.getId();
            String deudaLista = debtId + " - " + debtName;
            if(deudaLista.equals(getDeudas())) {
                deuda = debt;
                return "pago";
            }
        }
        return "home";
    }
    
    public String finalDebt (){
        this.setResult("");
        String rol = getRol(home.getGrupo(), index.getUser());
        if(RolTypes.LIDER.toString().equalsIgnoreCase(rol)){
            this.setResult("Cierre de grupo activado");
            if(finalDebtResolution(home.getGrupo())){
               this.setResult("Solicitud enviada"); 
            }
        }else{
            this.setResult("Usted no posee permisos para cerrar las deudas del grupo");
        }
        return "grupos";        
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
    
    private List<Deuda> getDebtByGroup (Grupo group) {
        WSSplitpay_Service service = new WSSplitpay_Service();
        WSSplitpay port = service.getWSSplitpayPort();
        return port.getDebtByGroup(group);
    }
    
    
    private boolean addMembers(List<Usuario> members, Grupo grupo, RolTypes type) {
        WSSplitpay_Service service = new WSSplitpay_Service();
        WSSplitpay port = service.getWSSplitpayPort();
        return port.addMembers(members, grupo, type);
    }

    private String getRol(Grupo group, Usuario user) {
        WSSplitpay_Service service = new WSSplitpay_Service();
        WSSplitpay port = service.getWSSplitpayPort();
        return port.getRol(group, user);
    }

    private boolean finalDebtResolution(Grupo group) {
        WSSplitpay_Service service = new WSSplitpay_Service();
        WSSplitpay port = service.getWSSplitpayPort();
        return port.finalDebtResolution(group);
    }
    
}
