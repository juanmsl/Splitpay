/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Pago;
import integration.facades.PagoFacade;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author sala_a
 */
@Stateless
@LocalBean
public class PaymentController {

    @EJB
    private PagoFacade pagoFacade;

    public List<Pago> findByDate(Date date) {
        List<Pago> ret = new ArrayList<>();
        List<Pago> findAll = pagoFacade.findAll();
        for (Pago pago : findAll) {
            if(pago.getFecha().equals(date)) {
                ret.add(pago);
            }
        }
        return ret;
    }
    
    public List<Pago> findByDocument(String document) {
        List<Pago> ret = new ArrayList<>();
        List<Pago> findAll = pagoFacade.findAll();
        for (Pago pago : findAll) {
            if(pago.getUsuarioId().getNumdocumento().equals(document)) {
                ret.add(pago);
            }
        }
        return ret;
    }
}
