/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integration.facades;

import entities.Usuariogrupo;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author juanm
 */
@Remote
public interface UsuariogrupoFacadeRemote {

    void create(Usuariogrupo usuariogrupo);

    void edit(Usuariogrupo usuariogrupo);

    void remove(Usuariogrupo usuariogrupo);

    Usuariogrupo find(Object id);

    List<Usuariogrupo> findAll();

    List<Usuariogrupo> findRange(int[] range);

    int count();
    
}
