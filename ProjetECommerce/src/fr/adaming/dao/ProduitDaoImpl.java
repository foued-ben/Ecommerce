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
	public Produit addProduit(Produit p) {
		em.persist(p);
		return p;
	}

	@Override
	public int deleteProduit(Produit p) {
		String req = "DELETE Produit prod WHERE prod.idProduit=:pIDProduit";
		Query query = em.createQuery(req);
		//Passage paramètre
		query.setParameter("pIDProduit", p.getIdProduit());
		int verif = query.executeUpdate();
		return verif;
	}

	@Override
	public int updateClient(Produit p) {
		String req ="UPDATE Produit prod SET prod.designation=:pDesignation, prod.description=:pDescription, prod.prix=:pPrix, prod.quantite=:pQuantite WHERE prod.idProduit=:pIDProduit";
		Query query=em.createQuery(req);
		
		//Passage des paramètres
		query.setParameter("pDesignation", p.getDesignation());
		query.setParameter("pDescription", p.getDescription());
		query.setParameter("pPrix", p.getPrix());
		query.setParameter("pQuantite", p.getQuantite());
		query.setParameter("pIDProduit", p.getIdProduit());

		int verif = query.executeUpdate();
		return verif;
	}

	@Override
	public Produit getProduit(Produit p) {
		// TODO Auto-generated method stub
		return null;
	}

}
