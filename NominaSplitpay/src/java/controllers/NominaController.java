/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Pagonomina;
import integration.facade.PagonominaFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sala_a
 */
@Stateless
@LocalBean
public class NominaController {

    @EJB
    private PagonominaFacade pagonominaFacade;

    public void insertPayments(Pagonomina payment) {
        
        pagonominaFacade.create(payment);
        System.out.println("Se recibio el pago de: " + payment.getMonto() + " del usuario: " + payment.getUsuarioId());
        
    }

    
}
