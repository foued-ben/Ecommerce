package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

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
	public int deleteCategorie(Categorie c) {
		String req ="DELETE Categorie cat WHERE cat.idCategorie=:pIDCategorie";
		Query query = em.createQuery(req);
		
		// Passage paramètre
		query.setParameter("pIDCategorie", c.getIdCategorie());
		try{
			int verif = query.executeUpdate();
			return verif;
		} catch(PersistenceException e){
			System.out.println("Veuiller supprimer les produits de cette catégorie avant de supprimer la catégorie");
			return -1;
		}
	}

	@Override
	public int updateCategorie(Categorie c) {
		String req ="UPDATE Categorie cat SET cat.nomCategorie=:pNom, cat.description=:pDescription WHERE cat.idCategorie=:pIDCategorie";
		Query query =em.createQuery(req);
		// Passage des paramètre
		query.setParameter("pNom", c.getNomCategorie());
		query.setParameter("pDescription", c.getDescription());
		query.setParameter("pIDCategorie", c.getIdCategorie());
		int verif = query.executeUpdate();
		//System.out.println(c);
		return verif;
	}

	@Override
	public Categorie getCategorie(Categorie c) {
		String req ="SELECT cat FROM Categorie cat WHERE cat.idCategorie=:pIDCategorie";
		Query query = em.createQuery(req);
		
		//Passage du paramètre.
		query.setParameter("pIDCategorie", c.getIdCategorie());
		Categorie categorieCherche = (Categorie) query.getSingleResult();
		return categorieCherche;
	}

}
