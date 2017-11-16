/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author sala-bd
 */
@Embeddable
public class UsuariodeudaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "USUARIO_ID")
    private BigInteger usuarioId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DEUDA_ID")
    private BigInteger deudaId;

    public UsuariodeudaPK() {
    }

    public UsuariodeudaPK(BigInteger usuarioId, BigInteger deudaId) {
        this.usuarioId = usuarioId;
        this.deudaId = deudaId;
    }

    public BigInteger getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(BigInteger usuarioId) {
        this.usuarioId = usuarioId;
    }

    public BigInteger getDeudaId() {
        return deudaId;
    }

    public void setDeudaId(BigInteger deudaId) {
        this.deudaId = deudaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioId != null ? usuarioId.hashCode() : 0);
        hash += (deudaId != null ? deudaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuariodeudaPK)) {
            return false;
        }
        UsuariodeudaPK other = (UsuariodeudaPK) object;
        if ((this.usuarioId == null && other.usuarioId != null) || (this.usuarioId != null && !this.usuarioId.equals(other.usuarioId))) {
            return false;
        }
        if ((this.deudaId == null && other.deudaId != null) || (this.deudaId != null && !this.deudaId.equals(other.deudaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.UsuariodeudaPK[ usuarioId=" + usuarioId + ", deudaId=" + deudaId + " ]";
    }
    
}
