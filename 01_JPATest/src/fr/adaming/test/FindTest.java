package fr.adaming.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.adaming.entities.Utilisateur;

public class FindTest {

	public static void main(String[] args) {
		
		// 1 Création de l'entityManagerFactory
		EntityManagerFactory emf =Persistence.createEntityManagerFactory("PU");

		// 2 Créer l'entityManager
		EntityManager em = emf.createEntityManager();

		// 3 lire à partir de la base de données
		// Pour lire de la base de données je n'ai pas besoin de transaction
		Utilisateur uOut = em.find(Utilisateur.class,1);
		System.out.println(uOut);
				
	
	}

}
