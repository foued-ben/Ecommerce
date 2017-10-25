package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.modele.Administrateur;

@Stateless
public class AdministrateurDaoImpl implements IAdministrateurDao {

		@PersistenceContext(unitName = "ProjetECommerce") 
		EntityManager em;

	@Override
	public Administrateur connexion(Administrateur a) {
		String req = "Select a from Administrateur a where a.identifiant=:pIdentifiant and a.mdp=:pMdp";

		// creation de query
		Query query = em.createQuery(req);

		// passage des param
		query.setParameter("pIdentifiant", a.getIdentifiant());
		query.setParameter("pMdp", a.getMdp());

		// envoyer la requete et récuperer le résultat
		Administrateur a_out =  (Administrateur) query.getSingleResult();
		System.out.println(a_out);
		return a_out;
	}

}
