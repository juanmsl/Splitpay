/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integration.facades;

import entities.Usuariodeuda;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author juanm
 */
@Remote
public interface UsuariodeudaFacadeRemote {

    void create(Usuariodeuda usuariodeuda);

    void edit(Usuariodeuda usuariodeuda);

    void remove(Usuariodeuda usuariodeuda);

    Usuariodeuda find(Object id);

    List<Usuariodeuda> findAll();

    List<Usuariodeuda> findRange(int[] range);

    int count();
    
}
