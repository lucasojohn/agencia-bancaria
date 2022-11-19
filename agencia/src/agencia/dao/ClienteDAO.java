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

        public void salva(Clientes cliente) { 
            this.em = EntityManagerUtil.getEM();
            this.em.getTransaction().begin();
            this.em.persist(cliente);
            this.em.close();
        }
        
        public void deleta(Long cpf) {
            this.em = EntityManagerUtil.getEM();
            Clientes c = this.em.find(Clientes.class, cpf);

            this.em.getTransaction().begin();
            this.em.remove(c);
            this.em.getTransaction().commit();
            this.em.close();
        }
        
        public Clientes buscaCliente(Long cpf) {
            Clientes c = null;
            this.em = EntityManagerUtil.getEM();
            c = this.em.find(Clientes.class, cpf);

            return c;
        }

	public List<Clientes> lista(){
            List<Clientes> lista = null;
            this.em = EntityManagerUtil.getEM();
            TypedQuery<Clientes> query = this.em.createQuery(
                            "SELECT c FROM Clientes c", Clientes.class);
            lista = query.getResultList();

            return lista;
	}

        public void atualiza(Clientes c) {
            this.em = EntityManagerUtil.getEM();
            this.em.getTransaction().begin();
            this.em.merge(c);
            this.em.getTransaction().commit();
            this.em.close();
        }

	
}
