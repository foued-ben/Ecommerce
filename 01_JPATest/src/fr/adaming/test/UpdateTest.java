package fr.adaming.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.adaming.entities.Utilisateur;

public class UpdateTest {

	public static void main(String[] args) {

		// 1 Création de l'entityManagerFactory
		EntityManagerFactory emf =Persistence.createEntityManagerFactory("PU");

		// 2 Créer l'entityManager
		EntityManager em = emf.createEntityManager();
		
		// 3 Création de la transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//4 On crée un nouvel objet.
		Utilisateur u1= new Utilisateur("Duck","Riri","Riri@Duck");

		// 5 On récupère l'objet à modifier
		Utilisateur uChange = em.find(Utilisateur.class,2);
		uChange.setNom("Toto");
		System.out.println(uChange);
		
		// On merge l'objet trouvé avec le nouveau
		em.merge(uChange);
		
		// Commit la transaction
		tx.commit();
		
		//On ferme les flux
		em.close();
		emf.close();
	}

}
