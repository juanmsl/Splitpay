/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author luisd
 */
@Named(value = "mBGroupController")
@SessionScoped
public class MBGroupController implements Serializable{

    @Inject
    MBHomeController home;
    public MBGroupController() {
    }

    public MBHomeController getHome() {
        return home;
    }

    public void setHome(MBHomeController home) {
        this.home = home;
    }

   
    
    
    
}
