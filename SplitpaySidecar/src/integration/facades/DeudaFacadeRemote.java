/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integration.facades;

import entities.Deuda;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author juanm
 */
@Remote
public interface DeudaFacadeRemote {

    void create(Deuda deuda);

    void edit(Deuda deuda);

    void remove(Deuda deuda);

    Deuda find(Object id);

    List<Deuda> findAll();

    List<Deuda> findRange(int[] range);

    int count();
    
}
