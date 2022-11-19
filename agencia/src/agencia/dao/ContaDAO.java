/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agencia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import agencia.model.Conta;
import util.EntityManagerUtil;

public class ContaDAO {

	private EntityManager em;
	
	public void salva(Usuario usuario) {
		
		this.em = EntityManagerUtil.getEM();
		this.em.getTransaction().begin();
		this.em.persist(usuario);
		this.em.close();
	}
	
	public Usuario buscaId(Long id) {
		
		Usuario u = null;
		
		this.em = EntityManagerUtil.getEM();
		
		u = this.em.find(Usuario.class, id);
		
		return u;
	}
	
	public void deleta(Long id) {
		
		this.em = EntityManagerUtil.getEM();
		
		Usuario u = this.em.find(Usuario.class, id);

		this.em.getTransaction().begin();
		this.em.remove(u);
		this.em.getTransaction().commit();
		this.em.close();
	}
	
	public void atualiza(Usuario u) {
		
		this.em = EntityManagerUtil.getEM();
		this.em.getTransaction().begin();
		this.em.merge(u);
		this.em.getTransaction().commit();
		this.em.close();
	}
	
	public List<Usuario> busca(){
		
		List<Usuario> lista = null;
		
		this.em = EntityManagerUtil.getEM();
		
		TypedQuery<Usuario> query = this.em.createQuery(
				"SELECT usr FROM Usuario usr", Usuario.class);
		
		lista = query.getResultList();
		
		return lista;
	}
	
	public List<Usuario> buscaEmail(String email){
		
List<Usuario> lista = null;
		
		this.em = EntityManagerUtil.getEM();
		
		TypedQuery<Usuario> query = this.em.createQuery(
				"SELECT usr FROM Usuario usr WHERE usr.email = :email", 
				Usuario.class);
		
		query.setParameter("email", email);
		
		lista = query.getResultList();
		
		return lista;
	}
	
	
	//busca por email
}
