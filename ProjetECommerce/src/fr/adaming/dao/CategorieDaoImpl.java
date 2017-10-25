package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.modele.Categorie;
@Stateless
public class CategorieDaoImpl implements ICategorieDao {

	@PersistenceContext(unitName = "ProjetECommerce") 
	EntityManager em;
	
	@Override
	public List<Categorie> getAllCategories() {
		String req ="SELECT cat FROM Categorie cat";
		Query query = em.createQuery(req);
		@SuppressWarnings("unchecked")
		List<Categorie> listeCategorie = query.getResultList();
		
		
		return listeCategorie;
	}

	@Override
	public Categorie addCategorie(Categorie c) {
		em.persist(c);
		return c;
	}

	@Override
	public int deleteProduit(Categorie c) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Categorie updateClient(Categorie c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categorie getProduit(Categorie c) {
		// TODO Auto-generated method stub
		return null;
	}

}
