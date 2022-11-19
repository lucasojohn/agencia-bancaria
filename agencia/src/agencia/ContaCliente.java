/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agencia;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gabiz
 */
@Entity
@Table(name = "conta_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContaCliente.findAll", query = "SELECT c FROM ContaCliente c"),
    @NamedQuery(name = "ContaCliente.findById", query = "SELECT c FROM ContaCliente c WHERE c.id = :id")})
public class ContaCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "cpf_cliente", referencedColumnName = "cpf")
    @ManyToOne(optional = false)
    private Clientes cpfCliente;
    @JoinColumn(name = "numero_conta", referencedColumnName = "numero")
    @OneToOne(optional = false)
    private Conta numeroConta;

    public ContaCliente() {
    }

    public ContaCliente(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Clientes getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(Clientes cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public Conta getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Conta numeroConta) {
        this.numeroConta = numeroConta;
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
        if (!(object instanceof ContaCliente)) {
            return false;
        }
        ContaCliente other = (ContaCliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "agencia.ContaCliente[ id=" + id + " ]";
    }
    
}
