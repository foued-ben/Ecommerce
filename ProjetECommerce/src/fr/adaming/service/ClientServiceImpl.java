package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ClientDaoImpl;
import fr.adaming.dao.IClientDao;
import fr.adaming.modele.Categorie;
import fr.adaming.modele.Client;
import fr.adaming.modele.Panier;
import fr.adaming.modele.Produit;


@Stateful
public class ClientServiceImpl implements IClientService {

	@EJB
	IClientDao cliDao = new ClientDaoImpl(); 
	
	
	@Override
	public List<Categorie> getAllCategories() {
		return cliDao.getAllCategories();
	}

	@Override
	public List<Produit> getAllProduitByCategorie(Categorie c) {
		return cliDao.getAllProduitByCategorie(c);
	}

	@Override
	public List<Produit> getProduitsSelect(List<Produit> lp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> getProduitsByMot(String mot) {
		return cliDao.getProduitsByMot(mot) ; 
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
		return cliDao.enregitrementClient(c);
	}

	@Override
	public List<Produit> getAllProduits() {
		return cliDao.getAllProduits();
	}

}
