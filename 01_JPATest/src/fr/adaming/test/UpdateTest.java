package fr.adaming.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.adaming.entities.Utilisateur;

public class UpdateTest {

	public static void main(String[] args) {

		// 1 Cr�ation de l'entityManagerFactory
		EntityManagerFactory emf =Persistence.createEntityManagerFactory("PU");

		// 2 Cr�er l'entityManager
		EntityManager em = emf.createEntityManager();
		
		// 3 Cr�ation de la transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//4 On cr�e un nouvel objet.
		Utilisateur u1= new Utilisateur("Duck","Riri","Riri@Duck");

		// 5 On r�cup�re l'objet � modifier
		Utilisateur uChange = em.find(Utilisateur.class,2);
		uChange.setNom("Toto");
		System.out.println(uChange);
		
		// On merge l'objet trouv� avec le nouveau
		em.merge(uChange);
		
		// Commit la transaction
		tx.commit();
		
		//On ferme les flux
		em.close();
		emf.close();
	}

}
