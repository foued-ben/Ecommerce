package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IAdministrateurDao;
import fr.adaming.modele.Administrateur;

@Stateful
public class AdministrateurServiceImpl implements IAdministrateurService{
	
	@EJB
	private IAdministrateurDao administrateurDao ;

	public IAdministrateurDao getAdministrateurDao() {
		return administrateurDao;
	}

	public void setAdministrateurDao(IAdministrateurDao administrateurDao) {
		this.administrateurDao = administrateurDao;
	}

	@Override
	public Administrateur connexion(Administrateur a) {
		return administrateurDao.connexion(a);
	}
	
	

}

