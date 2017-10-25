package fr.adaming.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.adaming.entities.Utilisateur;

public class DeleteTest {

	
	public static void main(String[] args) {
		
		// 1 Création de l'entityManagerFactory
		EntityManagerFactory emf =Persistence.createEntityManagerFactory("PU");

		// 2 Créer l'entityManager
		EntityManager em = emf.createEntityManager();
		
		// 3 Création de la transaction
		EntityTransaction tx = em.getTransaction();
		
		// 4 Ouvrir une transaction
		tx.begin();
		
		// 5 On récupère l'objet que l'on veut supprimer.
		Utilisateur uOut = em.find(Utilisateur.class,1);
		System.out.println(uOut);
		

		// 6 On supprime l'objet du contexte
		em.remove(uOut);
		
		// 7 On commit la transaction
		tx.commit();
		
		// 8 On ferme les flux
		em.close();
		emf.close();

	}
		
}
