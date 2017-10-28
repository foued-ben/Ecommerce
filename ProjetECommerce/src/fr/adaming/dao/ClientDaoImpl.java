package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.modele.Categorie;
import fr.adaming.modele.Client;
import fr.adaming.modele.Commande;
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
	public List<Produit> getAllProduits() {
		String req ="Select p from Produit p" ; 
		
		Query query = em.createQuery(req);
		List<Produit> listeProduits = query.getResultList() ;
		
		return listeProduits ; 
	}

	@Override
	public List<Produit> getAllProduitByCategorie(Categorie c) {
		String req = "Select p from Produit p where p.categorie.idCategorie=:pidc";

		// creation de query
		Query query = em.createQuery(req);

		// passage des param
		query.setParameter("pidc", c.getIdCategorie());

		try{
		List<Produit> listeProduitsCat = query.getResultList();
		return listeProduitsCat;

		} catch (Exception e){
			System.out.println("Impossible de trouver");
			return null;
		}
		// envoyer la requete et récuperer le résultat

	}

	@Override
	public List<Produit> getProduitsSelect(List<Produit> lp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> getProduitsByMot(String mot) {
		String req = "SELECT prod FROM Produit prod WHERE prod.description LIKE :pDescription";
		Query query = em.createQuery(req);
		// Production du paramètre
		StringBuilder intitule = new StringBuilder();
		intitule.append(mot);
		intitule.append('%');
		String intituleParam = intitule.toString();
		//Passage du paramètre
		query.setParameter("pDescription", intituleParam);
		@SuppressWarnings("unchecked")
		List<Produit> liste = query.getResultList();
		
		return liste;
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
	public Client enregitrementClient(Client c) {
		em.persist(c);
		return c;
	}

	@Override
	public Commande enregistrementCommabde(Commande commande) {
		em.persist(commande);
		return commande;
	}

	@Override
	public Client recuperClient(Client c) {
		String req ="SELECT client from Client client WHERE client.idClient=:pIDClient";
		Query query= em.createQuery(req);
		System.out.println("requête créée");
		query.setParameter("pIDClient",c.getIdClient());
		Client clientChercher = (Client) query.getSingleResult();

		return clientChercher;
	}
	

}
