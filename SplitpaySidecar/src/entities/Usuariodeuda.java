/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sala_a
 */
@Entity
@Table(name = "USUARIODEUDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuariodeuda.findAll", query = "SELECT u FROM Usuariodeuda u")
    , @NamedQuery(name = "Usuariodeuda.findByUsuarioId", query = "SELECT u FROM Usuariodeuda u WHERE u.usuariodeudaPK.usuarioId = :usuarioId")
    , @NamedQuery(name = "Usuariodeuda.findByDeudaId", query = "SELECT u FROM Usuariodeuda u WHERE u.usuariodeudaPK.deudaId = :deudaId")
    , @NamedQuery(name = "Usuariodeuda.findByMonto", query = "SELECT u FROM Usuariodeuda u WHERE u.monto = :monto")})
public class Usuariodeuda implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuariodeudaPK usuariodeudaPK;
    @Column(name = "MONTO")
    private BigInteger monto;
    @JoinColumn(name = "DEUDA_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Deuda deuda;
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Usuariodeuda() {
    }

    public Usuariodeuda(UsuariodeudaPK usuariodeudaPK) {
        this.usuariodeudaPK = usuariodeudaPK;
    }

    public Usuariodeuda(BigInteger usuarioId, BigInteger deudaId) {
        this.usuariodeudaPK = new UsuariodeudaPK(usuarioId, deudaId);
    }

    public UsuariodeudaPK getUsuariodeudaPK() {
        return usuariodeudaPK;
    }

    public void setUsuariodeudaPK(UsuariodeudaPK usuariodeudaPK) {
        this.usuariodeudaPK = usuariodeudaPK;
    }

    public BigInteger getMonto() {
        return monto;
    }

    public void setMonto(BigInteger monto) {
        this.monto = monto;
    }

    public Deuda getDeuda() {
        return deuda;
    }

    public void setDeuda(Deuda deuda) {
        this.deuda = deuda;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuariodeudaPK != null ? usuariodeudaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuariodeuda)) {
            return false;
        }
        Usuariodeuda other = (Usuariodeuda) object;
        if ((this.usuariodeudaPK == null && other.usuariodeudaPK != null) || (this.usuariodeudaPK != null && !this.usuariodeudaPK.equals(other.usuariodeudaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Usuariodeuda[ usuariodeudaPK=" + usuariodeudaPK + " ]";
    }
    
}
