package controllers;

import integration.splitpaysoap.Usuario;
import integration.splitpaysoap.WSSplitpay;
import integration.splitpaysoap.WSSplitpay_Service;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author sala_a
 */
@Named(value = "mbIndexController")
@SessionScoped
public class MBIndexController implements Serializable {

    private String email;
    private String password;
    private String result;
    private Usuario user;
    
    public MBIndexController() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    
  
    
    public String login(){
        Usuario usuario = new Usuario();
        usuario.setEmail(this.getEmail());
        usuario.setContrasena(this.getPassword());
        usuario = loginUser(usuario);
        setResult("");
        if(usuario != null) {
            this.setUser(usuario);
            return "home";
        } else {
            this.setResult("Usuario invalido");
        }
        return "index";
    }
    
    public String register() {
        return "register";
    }

    private Usuario loginUser(Usuario user) {
        WSSplitpay_Service service = new WSSplitpay_Service();
        WSSplitpay port = service.getWSSplitpayPort();
        return port.loginUser(user);
    }
}
