package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.adaming.modele.Categorie;
@Stateless
public class CategorieDaoImpl implements ICategorieDao {

	@PersistenceContext(unitName = "ProjetECommerce") 
	EntityManager em;
	
	@Override
	public List<Categorie> getAllCategories() {
		// TODO Auto-generated method stub
		return null;
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
