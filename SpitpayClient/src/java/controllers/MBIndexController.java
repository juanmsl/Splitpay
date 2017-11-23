package controllers;

import integration.splitpaysoap.Usuario;
import integration.splitpaysoap.WSSplitpay;
import integration.splitpaysoap.WSSplitpay_Service;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ViewScoped;
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
    
    public String login(){
        Usuario user = new Usuario();
        user.setEmail(this.getEmail());
        user.setContrasena(this.getPassword());
        if(loginUser(user) != null) {
            this.setResult("Super autenticado wey!!");
        } else {
            this.setResult("Pailas, no existes");
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
