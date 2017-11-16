/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integration.facades;

import entities.Documento;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author sala-bd
 */
@Remote
public interface DocumentoFacadeRemote {

    void create(Documento documento);

    void edit(Documento documento);

    void remove(Documento documento);

    Documento find(Object id);

    List<Documento> findAll();

    List<Documento> findRange(int[] range);

    int count();
    
}
