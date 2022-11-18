/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agencia;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gabiz
 */
@Entity
@Table(name = "tipo_conta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoConta.findAll", query = "SELECT t FROM TipoConta t"),
    @NamedQuery(name = "TipoConta.findByCodConta", query = "SELECT t FROM TipoConta t WHERE t.codConta = :codConta"),
    @NamedQuery(name = "TipoConta.findByDescricao", query = "SELECT t FROM TipoConta t WHERE t.descricao = :descricao")})
public class TipoConta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cod_conta")
    private String codConta;
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tpConta")
    private Collection<Conta> contaCollection;

    public TipoConta() {
    }

    public TipoConta(String codConta) {
        this.codConta = codConta;
    }

    public String getCodConta() {
        return codConta;
    }

    public void setCodConta(String codConta) {
        this.codConta = codConta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public Collection<Conta> getContaCollection() {
        return contaCollection;
    }

    public void setContaCollection(Collection<Conta> contaCollection) {
        this.contaCollection = contaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codConta != null ? codConta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoConta)) {
            return false;
        }
        TipoConta other = (TipoConta) object;
        if ((this.codConta == null && other.codConta != null) || (this.codConta != null && !this.codConta.equals(other.codConta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "agencia.TipoConta[ codConta=" + codConta + " ]";
    }
    
}
