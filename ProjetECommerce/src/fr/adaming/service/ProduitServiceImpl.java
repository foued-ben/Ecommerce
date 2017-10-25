package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.adaming.dao.IProduitDao;
import fr.adaming.modele.Categorie;
import fr.adaming.modele.Produit;
@Stateless
public class ProduitServiceImpl implements IProduitService{

	@EJB
	private IProduitDao produitDao;
	
	@Override
	public List<Produit> getAllProduits() {
		
		return produitDao.getAllProduits();
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
		// TODO Auto-generated method stubs
		return null;
	}

	

}
