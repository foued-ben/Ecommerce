package fr.adaming.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import fr.adaming.entities.Utilisateur;

public class JPSQLTest {

	public static void main(String[] args) {

		EntityManagerFactory emf =Persistence.createEntityManagerFactory("PU");
		
		EntityManager em = emf.createEntityManager();
		// 1 R�cup�rer tous les �l�ments de la table avec JPQL
		// a Construire une requ�te JPQL
		String jpqlReq ="SELECT u FROM Utilisateur u";
		
		// b cr�ation du query pour pouvoir envoyer et r�cup�rer le r�sultat de la requ�te.
		Query query = em.createQuery(jpqlReq);
		
		// Envoi de la requ�te et r�cup�ration du r�sultat.
		List<Utilisateur> liste = query.getResultList();
		System.out.println("--------- Liste r�cup�r�e par JPQL");
		for (Utilisateur u : liste){
			System.out.println(u);
		}
		
		
		// 2 R�cup�ration de tous les �l�ments de la table avec les requ�tes nomm�es.
		Query queryNamed = em.createNamedQuery("liste");
		// Envoi de la requ�te et r�cup�ration du r�sultat.
		List<Utilisateur> liste2 = queryNamed.getResultList();
		System.out.println("--------- Liste r�cup�r�e par JPQL avedc un namedquery");
		for (Utilisateur u : liste2){
			System.out.println(u);
		}
		
		
		// 3 R�cup�ration de tous les �l�ments de la table avec SQL
		String sqlReq = "SELECT * FROM users";
		Query querySQL = em.createNativeQuery(sqlReq);
		List<Utilisateur> listeSQL =  querySQL.getResultList();
		System.out.println("------ Liste R�cup�r�e avec Native Query");
		System.out.println(listeSQL);
		
		
		//4 R�cup�ration d'un utilisateur en utilisant son nom
		String sqlReq2 ="SELECT * FROM users WHERE prenom = ?";
		Query querySQL2 = em.createNativeQuery(sqlReq2,Utilisateur.class);
		querySQL2.setParameter(1, "Donald");
		Utilisateur user = (Utilisateur) querySQL2.getSingleResult();
		System.out.println(user);
		
		// 5 R�cup�rer 1 �l�ment avec JPQL
		// 5 a avec les ?N
		String reqJPQL = "SELECT util FROM Utilisateur util WHERE util.nom =?1 and util.prenom =?2";
		Query jpqlQuery = em.createQuery(reqJPQL);
		jpqlQuery.setParameter(1,"Duck");
		jpqlQuery.setParameter(2,"Donald");
		Utilisateur userJPQL1 = (Utilisateur) jpqlQuery.getSingleResult();
		System.out.println(userJPQL1);
		
		// 5 b Avec les pseudos
		String reqJPQL2 = "SELECT util FROM Utilisateur util WHERE util.nom = :pNom and util.prenom =:pPrenom";
		Query JPQLQuery2 =em.createQuery(reqJPQL2);
		// Passage des param�tres
		JPQLQuery2.setParameter("pNom", "Duck");
		JPQLQuery2.setParameter("pPrenom","Donald");
		Utilisateur userJPQL2 = (Utilisateur) JPQLQuery2.getSingleResult();

		// En utilisant une named Query avec params
		Query queryNamedRecherche = em.createNamedQuery("reqUnit");

		// Passage des param
		queryNamedRecherche.setParameter("pPrenom", "Donald");
		queryNamedRecherche.setParameter("pNom","Duck");
		Utilisateur userNamedQuery = (Utilisateur) queryNamedRecherche.getSingleResult();
		System.out.println("------- R�sultat trouv� avec une namedQuery avec param�tres -------");
		System.out.println(userNamedQuery);

	}

}
