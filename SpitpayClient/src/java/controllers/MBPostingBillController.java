/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import integration.splitpaysoap.Deuda;
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
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author sala_a
 */
@Named(value = "mBPostingBillController")
@SessionScoped
public class MBPostingBillController implements Serializable{
    
    private String nombreDeuda;
    private BigInteger monto;
    private Date fecha;
    private String nombreGrupo = "Integrantes";
    private List<String> integrantes = new ArrayList<>();
    
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

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public List<String> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<String> integrantes) {
        this.integrantes = integrantes;
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
}
