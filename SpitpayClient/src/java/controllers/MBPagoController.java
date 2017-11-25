/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import integration.splitpaysoap.Pago;
import integration.splitpaysoap.WSSplitpay;
import integration.splitpaysoap.WSSplitpay_Service;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author sala_a
 */
@Named(value = "mBPagoController")
@Dependent
public class MBPagoController {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/server.splitpay.com_8080/WSSplitpay/WSSplitpay.wsdl")
    private WSSplitpay_Service service;

    private BigInteger montoAPagar;
    private String concepto;
    private String descripcion;
    private String usuarioPayPal;
    private String passwordPaypal;
    private String result;
    
    @Inject
    MBHomeController home;
    @Inject
    MBGroupController grupo;
    @Inject
    MBIndexController indexs;
    
    public MBPagoController() {
    }

    public MBGroupController getGrupo() {
        return grupo;
    }

    public WSSplitpay_Service getService() {
        return service;
    }

    public void setService(WSSplitpay_Service service) {
        this.service = service;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    

    public void setGrupo(MBGroupController grupo) {
        this.grupo = grupo;
    }

    public BigInteger getMontoAPagar() {
        return montoAPagar;
    }

    public void setMontoAPagar(BigInteger montoAPagar) {
        this.montoAPagar = montoAPagar;
    }

    public MBIndexController getIndexs() {
        return indexs;
    }

    public void setIndexs(MBIndexController indexs) {
        this.indexs = indexs;
    }
    
    
    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuarioPayPal() {
        return usuarioPayPal;
    }

    public void setUsuarioPayPal(String usuarioPayPal) {
        this.usuarioPayPal = usuarioPayPal;
    }

    public String getPasswordPaypal() {
        return passwordPaypal;
    }

    public void setPasswordPaypal(String passwordPaypal) {
        this.passwordPaypal = passwordPaypal;
    }

    public MBHomeController getHome() {
        return home;
    }

    public void setHome(MBHomeController home) {
        this.home = home;
    }
    
    public  String pagar(){
        Pago pago = new Pago();
        pago.setMonto(this.getMontoAPagar());
        pago.setConcepto(this.getConcepto());
        pago.setDescripcion(this.getDescripcion());
        pago.setDeudaId(grupo.getDeuda());
        pago.setUsuarioId(indexs.getUser());
        GregorianCalendar c = new GregorianCalendar();
        XMLGregorianCalendar date2;
        try {
            date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            pago.setFecha(date2);
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(MBPostingBillController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(doPayment(pago)){
            this.setResult("El pago se esta procesando");
            //consumo servicio PayPal
        }else{
            this.setResult("No se pudo procesar el pago");
        }
        return "grupos";
    }

    private boolean doPayment(Pago payment) {
        WSSplitpay_Service service = new WSSplitpay_Service();
        WSSplitpay port = service.getWSSplitpayPort();
        return port.doPayment(payment);
    }
    
    
}
