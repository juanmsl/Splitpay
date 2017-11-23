/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import integration.splitpaysoap.Usuario;
import integration.splitpaysoap.WSSplitpay;
import integration.splitpaysoap.WSSplitpay_Service;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author sala_a
 */
@Named(value = "mBRegisterController")
@SessionScoped
public class MBRegisterController implements Serializable {
    
    private String nombre;
    private String email;
    private String contrasena;
    private String tipoDocumento;
    private String numDocumento;
    private String result;
        
    public MBRegisterController() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    
    
    public String register(){
        Usuario user = new Usuario();
        user.setNombre(this.getNombre());
        user.setEmail(this.getEmail());
        user.setContrasena(this.getContrasena());
        user.setTipodocumento(this.getTipoDocumento());
        user.setNumdocumento(this.getNumDocumento());
        if(registrarU(user)!= null){
            this.setResult("Registrado pinche putita");
        }else{
            this.setResult("Zonas perdidas loka");
        }
        
        return "register";
    }
    
    private Usuario registrarU (Usuario user){
        WSSplitpay_Service service = new WSSplitpay_Service();
        WSSplitpay port = service.getWSSplitpayPort();
        return port.registerUser(user);
    }    
    
}
