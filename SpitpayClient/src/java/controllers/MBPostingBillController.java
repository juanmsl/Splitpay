/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import integration.splitpaysoap.Deuda;
import integration.splitpaysoap.Grupo;
import integration.splitpaysoap.Usuario;
import integration.splitpaysoap.Usuariogrupo;
import integration.splitpaysoap.WSSplitpay;
import integration.splitpaysoap.WSSplitpay_Service;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author sala_a
 */
@Named(value = "mBPostingBillController")
@SessionScoped
public class MBPostingBillController implements Serializable{

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/server.splitpay.com_8080/WSSplitpay/WSSplitpay.wsdl")
    private WSSplitpay_Service service;
    
    private String nombreDeuda;
    private BigInteger monto;
    private Date fecha;
    private List <String> usuariosGrupoSeleccionados;
    private List<SelectItem> listaUsuariosGrupo;
    private Usuario usuario;
    private Grupo grupo;
    
    @Inject
    MBGroupController grupos;
    
    public MBPostingBillController() {
    }

    public String getNombreDeuda() {
        return nombreDeuda;
    }

    public void setNombreDeuda(String nombreDeuda) {
        this.nombreDeuda = nombreDeuda;
    }

    public BigInteger getMonto() {
        return monto;
    }

    public void setMonto(BigInteger monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
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

    public MBGroupController getGrupos() {
        return grupos;
    }

    public void setGrupos(MBGroupController grupos) {
        this.grupos = grupos;
    }
    
    public void preRenderView() {
        listaUsuariosGrupo = new ArrayList<>();
        List<Usuariogrupo> groups = getUsersGroup(grupos.getGrupo());
        for (Usuariogrupo group : groups) {
            if(group.getUsuario() != null) {
                String userName = group.getUsuario().getNombre();
                listaUsuariosGrupo.add(new SelectItem(userName));
            }
        }
        
    }
    
    public String posting(){
        Deuda deuda = new Deuda();
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(this.fecha);
        XMLGregorianCalendar date2;
        try {
            date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            deuda.setFecha(date2);
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(MBPostingBillController.class.getName()).log(Level.SEVERE, null, ex);
        }
        deuda.setNombre(this.getNombreDeuda());
        deuda.setCosto(this.getMonto());
        return "home";
        
        
    }

    private List<Usuariogrupo> getUsersGroup(Grupo group) {
        WSSplitpay_Service service = new WSSplitpay_Service();
        WSSplitpay port = service.getWSSplitpayPort();
        return port.getUsersGroup(group);
    }
    
    
}
