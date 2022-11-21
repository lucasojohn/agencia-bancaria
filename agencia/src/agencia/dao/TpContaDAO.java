package agencia.dao;

import javax.persistence.EntityManager;

import agencia.TipoConta;
import util.EntityManagerUtil;

public class TpContaDAO {

	private EntityManager em;
        
        public static final String CONTA_CORRENTE = "1";
        public static final String CONTA_POUPANCA = "2";
	
	public TipoConta buscaId(String cod) {
		TipoConta tc = null;
		this.em = EntityManagerUtil.getEM();
		tc = this.em.find(TipoConta.class, cod);

		return tc;
	}
}
