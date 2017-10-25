package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.modele.Produit;
@Stateless
public class ProduitDaoImpl implements IProduitDao{

	@PersistenceContext(unitName = "ProjetECommerce") 
	EntityManager em;
	
	@Override
	public List<Produit> getAllProduits() {
		String req ="Select prod from Produit prod";
		Query query = em.createQuery(req);
		List<Produit> listeProduits = query.getResultList();
		return listeProduits;
	}

	@Override
	public int addProduit(Produit p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteProduit(Produit p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateClient(Produit p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Produit getProduit(Produit p) {
		// TODO Auto-generated method stub
		return null;
	}

}
