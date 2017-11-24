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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juanm
 */
@Entity
@Table(name = "DEUDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Deuda.findAll", query = "SELECT d FROM Deuda d")
    , @NamedQuery(name = "Deuda.findById", query = "SELECT d FROM Deuda d WHERE d.id = :id")
    , @NamedQuery(name = "Deuda.findByNombre", query = "SELECT d FROM Deuda d WHERE d.nombre = :nombre")
    , @NamedQuery(name = "Deuda.findByCosto", query = "SELECT d FROM Deuda d WHERE d.costo = :costo")
    , @NamedQuery(name = "Deuda.findByFecha", query = "SELECT d FROM Deuda d WHERE d.fecha = :fecha")
    , @NamedQuery(name = "Deuda.findByDocumentoId", query = "SELECT d FROM Deuda d WHERE d.documentoId = :documentoId")})
public class Deuda implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(generator = "DEBT_ID")
    @SequenceGenerator(name="DEBT_ID",sequenceName="DEUDA_ID_SEQ", allocationSize=1)
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "COSTO")
    private BigInteger costo;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DOCUMENTO_ID")
    private BigInteger documentoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deuda")
    private List<Usuariodeuda> usuariodeudaList;
    @OneToMany(mappedBy = "deudaId")
    private List<Pago> pagoList;
    @JoinColumn(name = "GRUPO_ID", referencedColumnName = "ID")
    @ManyToOne
    private Grupo grupoId;
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "ID")
    @ManyToOne
    private Usuario usuarioId;

    public Deuda() {
    }

    public Deuda(BigDecimal id) {
        this.id = id;
    }

    public Deuda(BigDecimal id, BigInteger documentoId) {
        this.id = id;
        this.documentoId = documentoId;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigInteger getCosto() {
        return costo;
    }

    public void setCosto(BigInteger costo) {
        this.costo = costo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigInteger getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(BigInteger documentoId) {
        this.documentoId = documentoId;
    }

    @XmlTransient
    public List<Usuariodeuda> getUsuariodeudaList() {
        return usuariodeudaList;
    }

    public void setUsuariodeudaList(List<Usuariodeuda> usuariodeudaList) {
        this.usuariodeudaList = usuariodeudaList;
    }

    @XmlTransient
    public List<Pago> getPagoList() {
        return pagoList;
    }

    public void setPagoList(List<Pago> pagoList) {
        this.pagoList = pagoList;
    }

    public Grupo getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Grupo grupoId) {
        this.grupoId = grupoId;
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
        if (!(object instanceof Deuda)) {
            return false;
        }
        Deuda other = (Deuda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Deuda[ id=" + id + " ]";
    }
    
}
