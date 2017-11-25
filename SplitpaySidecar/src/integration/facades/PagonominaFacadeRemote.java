/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integration.facades;

import entities.Pagonomina;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author sala_a
 */
@Remote
public interface PagonominaFacadeRemote {

    void create(Pagonomina pagonomina);

    void edit(Pagonomina pagonomina);

    void remove(Pagonomina pagonomina);

    Pagonomina find(Object id);

    List<Pagonomina> findAll();

    List<Pagonomina> findRange(int[] range);

    int count();
    
}
