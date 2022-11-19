/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agencia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import agencia.Conta;
import util.EntityManagerUtil;

public class ContaDAO {

	private EntityManager em;
	
	public void salva(Conta conta) {
		this.em = EntityManagerUtil.getEM();
		this.em.getTransaction().begin();
		this.em.persist(conta);
		this.em.close();
	}
	
	public Conta buscaId(Long numero) {
		Conta c = null;
		this.em = EntityManagerUtil.getEM();
		c = this.em.find(Conta.class, numero);
		
		return c;
	}
	
	public void deleta(Long numero) {
		this.em = EntityManagerUtil.getEM();
		Conta c = this.em.find(Conta.class, numero);

		this.em.getTransaction().begin();
		this.em.remove(c);
		this.em.getTransaction().commit();
		this.em.close();
	}
	
	public void atualiza(Conta c) {
		this.em = EntityManagerUtil.getEM();
		this.em.getTransaction().begin();
		this.em.merge(c);
		this.em.getTransaction().commit();
		this.em.close();
	}
	
//	public List<Conta> busca(){
//
//            List<Conta> lista = null;
//
//            this.em = EntityManagerUtil.getEM();
//
//            TypedQuery<Conta> query = this.em.createQuery(
//                            "SELECT usr FROM Usuario usr", Conta.class);
//
//            lista = query.getResultList();
//
//            return lista;
//	}
	
	public List<Conta> buscaPorCliente(String cpfCliente){
            List<Conta> lista = null;
            this.em = EntityManagerUtil.getEM();

            TypedQuery<Conta> query = this.em.createQuery(
                            "SELECT cnt FROM Conta cnt INNER JOIN conta_cliente\n" +
"ON cnt.numero = conta_cliente.numero_conta WHERE conta_cliente.cpf_cliente = :cpfCliente", 
                            Conta.class);

            query.setParameter("email", email);

            lista = query.getResultList();

            return lista;
	}
	
	
	//busca por email
}
