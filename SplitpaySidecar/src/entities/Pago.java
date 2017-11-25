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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sala_a
 */
@Entity
@Table(name = "PAGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pago.findAll", query = "SELECT p FROM Pago p")
    , @NamedQuery(name = "Pago.findById", query = "SELECT p FROM Pago p WHERE p.id = :id")
    , @NamedQuery(name = "Pago.findByFecha", query = "SELECT p FROM Pago p WHERE p.fecha = :fecha")
    , @NamedQuery(name = "Pago.findByMonto", query = "SELECT p FROM Pago p WHERE p.monto = :monto")
    , @NamedQuery(name = "Pago.findByConcepto", query = "SELECT p FROM Pago p WHERE p.concepto = :concepto")
    , @NamedQuery(name = "Pago.findByDescripcion", query = "SELECT p FROM Pago p WHERE p.descripcion = :descripcion")})
public class Pago implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(generator = "PAY_ID")
    @SequenceGenerator(name="PAY_ID",sequenceName="PAGO_ID_SEQ", allocationSize=1)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "MONTO")
    private BigInteger monto;
    @Size(max = 100)
    @Column(name = "CONCEPTO")
    private String concepto;
    @Size(max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinColumn(name = "DEUDA_ID", referencedColumnName = "ID")
    @ManyToOne
    private Deuda deudaId;
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "ID")
    @ManyToOne
    private Usuario usuarioId;

    public Pago() {
    }

    public Pago(BigDecimal id) {
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

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Deuda getDeudaId() {
        return deudaId;
    }

    public void setDeudaId(Deuda deudaId) {
        this.deudaId = deudaId;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
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
        if (!(object instanceof Pago)) {
            return false;
        }
        Pago other = (Pago) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Pago[ id=" + id + " ]";
    }
    
}
