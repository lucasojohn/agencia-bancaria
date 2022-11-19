/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agencia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import agencia.Clientes;
import util.EntityManagerUtil;

/**
 *
 * @author lucas
 */
public class ClienteDAO {
        private EntityManager em;
        private Object email;

        // create
        public void salva(Clientes cliente) { 
            this.em = EntityManagerUtil.getEM();
            this.em.getTransaction().begin();
            this.em.persist(cliente);
            this.em.close();
        }
        
        // delete
        public void deleta(Long numero) {
            this.em = EntityManagerUtil.getEM();
            Clientes c = this.em.find(Clientes.class, numero);

            this.em.getTransaction().begin();
            this.em.remove(c);
            this.em.getTransaction().commit();
            this.em.close();
        }
        
        // search
        public Clientes buscaCpf(Long numero) {
            Clientes c = null;
            this.em = EntityManagerUtil.getEM();
            c = this.em.find(Clientes.class, numero);

            return c;
        }

        // list
        public List<Clientes> buscaPorCliente(String cpfCliente){
            List<Clientes> lista = null;
            this.em = EntityManagerUtil.getEM();

            TypedQuery<Clientes> query = this.em.createQuery(
                "SELECT cnt FROM Conta cnt INNER JOIN conta_cliente\n" +
                   "ON cnt.numero = conta_cliente.numero_conta WHERE conta_cliente.cpf_cliente = :cpfCliente", Clientes.class);

            query.setParameter("email", email);

            lista = query.getResultList();

            return lista;
	}
        
        // update
        public void atualiza(Clientes c) {
            this.em = EntityManagerUtil.getEM();
            this.em.getTransaction().begin();
            this.em.merge(c);
            this.em.getTransaction().commit();
            this.em.close();
        }

	
}
