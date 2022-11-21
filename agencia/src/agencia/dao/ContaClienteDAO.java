/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agencia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import agencia.Conta;
import agencia.ContaCliente;
import util.EntityManagerUtil;

public class ContaClienteDAO {

	private EntityManager em;
        
	public void salva(ContaCliente contaCliente) {
		this.em = EntityManagerUtil.getEM();
		this.em.getTransaction().begin();
		this.em.persist(contaCliente);
                this.em.getTransaction().commit();
		this.em.close();
	}

	
	public void deleta(Long numero) {
		this.em = EntityManagerUtil.getEM();
		ContaCliente cc = this.em.find(ContaCliente.class, numero);

		this.em.getTransaction().begin();
		this.em.remove(cc);
		this.em.getTransaction().commit();
		this.em.close();
	}


	public void deletaPorCliente(String cpfCliente){
            List<ContaCliente> lista = null;
            this.em = EntityManagerUtil.getEM();
            this.em.getTransaction().begin();

            TypedQuery<ContaCliente> query = this.em.createQuery(
                            "SELECT cc FROM ContaCliente cc WHERE cc.cpfCliente = :cpfCliente", 
                            ContaCliente.class);

            query.setParameter("cpfCliente", cpfCliente);
            lista = query.getResultList();
            
            for (ContaCliente contaCliente : lista) {
                this.em.remove(contaCliente);
            }
            
            this.em.getTransaction().commit();
            this.em.close();
	}

}
