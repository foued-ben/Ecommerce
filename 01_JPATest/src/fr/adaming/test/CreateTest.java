package fr.adaming.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.adaming.entities.Utilisateur;

public class CreateTest {

	public static void main(String[] args) {

		// 1 Cr�ation de l'entityManagerFactory
		EntityManagerFactory emf =Persistence.createEntityManagerFactory("PU");
		
		// 2 Cr�er l'entityManager
		EntityManager em = emf.createEntityManager();
		
		// 3 Cr�ation de la transaction
		EntityTransaction tx = em.getTransaction();
		
		// Ouvrir une transaction
		tx.begin();
		
		//Instancier les objets � stocker dans la base.
		Utilisateur u1= new Utilisateur("Duck","Riri","Riri@Duck");
		Utilisateur u2= new Utilisateur("Duck","Fifi","Fifi@Duck");
		Utilisateur u3= new Utilisateur("Duck","Loulou","Loulou@Duck");
		Utilisateur u4= new Utilisateur("Duck","Donald","Donald@Duck");

		//Relier les objets avec le context de l'EntityManager (rendre les objets persistents)
		em.persist(u1);
		em.persist(u2);
		em.persist(u3);
		em.persist(u4);

		// commit la transaction pour modifier la base de donn�es.
		tx.commit();
		
		//fermer les flux
		em.close();
		emf.close();
	}

}
