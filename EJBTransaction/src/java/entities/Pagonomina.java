/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sala_a
 */
@Entity
@Table(name = "PAGONOMINA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagonomina.findAll", query = "SELECT p FROM Pagonomina p")
    , @NamedQuery(name = "Pagonomina.findById", query = "SELECT p FROM Pagonomina p WHERE p.id = :id")
    , @NamedQuery(name = "Pagonomina.findByFecha", query = "SELECT p FROM Pagonomina p WHERE p.fecha = :fecha")
    , @NamedQuery(name = "Pagonomina.findByMonto", query = "SELECT p FROM Pagonomina p WHERE p.monto = :monto")
    , @NamedQuery(name = "Pagonomina.findByUsuarioId", query = "SELECT p FROM Pagonomina p WHERE p.usuarioId = :usuarioId")
    , @NamedQuery(name = "Pagonomina.findByDeudaId", query = "SELECT p FROM Pagonomina p WHERE p.deudaId = :deudaId")})
public class Pagonomina implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "MONTO")
    private BigInteger monto;
    @Column(name = "USUARIO_ID")
    private BigInteger usuarioId;
    @Column(name = "DEUDA_ID")
    private BigInteger deudaId;

    public Pagonomina() {
    }

    public Pagonomina(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigInteger getMonto() {
        return monto;
    }

    public void setMonto(BigInteger monto) {
        this.monto = monto;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagonomina)) {
            return false;
        }
        Pagonomina other = (Pagonomina) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Pagonomina[ id=" + id + " ]";
    }
    
}
