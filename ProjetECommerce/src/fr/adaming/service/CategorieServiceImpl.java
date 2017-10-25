package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.modele.Categorie;
@Stateless
public class CategorieServiceImpl implements ICategorieService{

	@EJB
	private ICategorieDao categorieDao;
	
	@Override
	public List<Categorie> getAllCategories() {
		return categorieDao.getAllCategories();
	}

	@Override
	public Categorie addCategorie(Categorie c) {
		return categorieDao.addCategorie(c);
	}

	@Override
	public int deleteCategorie(Categorie c) {
		// TODO Auto-generated method stub
		return categorieDao.deleteCategorie(c);
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
