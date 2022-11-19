package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	private static EntityManagerFactory emf;
	
	public static EntityManager getEM() {
		emf = Persistence.createEntityManagerFactory("agenciaPU");
		return emf.createEntityManager();
	}
	
	public static void fecharEMF() {
		emf.close();
	}
}