package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.modele.Categorie;
import fr.adaming.modele.Client;
import fr.adaming.modele.Panier;
import fr.adaming.modele.Produit;

@Stateless
public class ClientDaoImpl implements IClientDao {

	@PersistenceContext(unitName = "ProjetECommerce")
	EntityManager em;

	@Override
	public List<Categorie> getAllCategories() {
		String req ="Select c from Categorie c" ; 
		
		Query query = em.createQuery(req);
		List<Categorie> listeCategories = query.getResultList() ;
		
		return listeCategories ; 
	}

	@Override
	public List<Produit> getAllProduitByCategorie(Categorie c) {
		String req = "Select p from Produit p where p.categorie.idCategorie=:pidc";

		// creation de query
		Query query = em.createQuery(req);

		// passage des param
		query.setParameter("pidc", c.getIdCategorie());

		List<Produit> listeProduitsCat = query.getResultList();
		// envoyer la requete et récuperer le résultat

		return listeProduitsCat;
	}

	@Override
	public List<Produit> getProduitsSelect(List<Produit> lp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> getProduitsByMot(String mot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produit addProduitPanier(Produit p, int quantite, Panier pan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteProduitPanier(Produit p, Panier pan) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int enregitrementClient(Client c, Panier pan) {
		// TODO Auto-generated method stub
		return 0;
	}

}
