package agencia.dao;

import agencia.Clientes;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import agencia.Conta;
import agencia.TipoConta;
import util.EntityManagerUtil;

public class ContaDAO {

	private EntityManager em;
        
        public static final String CONTA_CORRENTE = "1";
        public static final String CONTA_POUPANCA = "2";
	
	public TipoConta buscaTipoConta(String cod) {
		TipoConta tc = null;
		this.em = EntityManagerUtil.getEM();
		tc = this.em.find(TipoConta.class, cod);

		return tc;
	}
        
	public void salva(Conta conta) {
		this.em = EntityManagerUtil.getEM();
		this.em.getTransaction().begin();
		this.em.persist(conta);
                this.em.getTransaction().commit();
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
        
	public List<Conta> lista(){
            List<Conta> lista = null;
            this.em = EntityManagerUtil.getEM();
            TypedQuery<Conta> query = this.em.createQuery(
                            "SELECT c FROM Conta c", Conta.class);
            lista = query.getResultList();

            return lista;
	}
	
	public void atualiza(Conta c) {
		this.em = EntityManagerUtil.getEM();
		this.em.getTransaction().begin();
		this.em.merge(c);
		this.em.getTransaction().commit();
		this.em.close();
	}

	public List<Conta> buscaPorCliente(Clientes cliente){
            List<Conta> lista = null;
            this.em = EntityManagerUtil.getEM();

            TypedQuery<Conta> query = this.em.createQuery(
                            "SELECT cnt FROM Conta cnt WHERE cnt.contaCliente.cpfCliente = :cliente", 
                            Conta.class);

            query.setParameter("cliente", cliente);

            lista = query.getResultList();

            return lista;
	}
        
	public void deletaBulk(List<Conta> contas){
            this.em = EntityManagerUtil.getEM();
            this.em.getTransaction().begin();
            
            for (Conta conta : contas) {
                Conta managedConta = this.em.merge(conta);
                this.em.remove(managedConta);
            }
            this.em.getTransaction().commit();
            this.em.close();
	}

}
