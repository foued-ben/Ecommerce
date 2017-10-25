package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.adaming.modele.Administrateur;

@Stateless
public class AdministrateurDaoImpl implements IAdministrateurDao {

	@PersistenceContext(unitName="ProjetECommerce")
	EntityManager em;
	@Override
	public Administrateur connexion(Administrateur a) {
		// TODO Auto-generated method stub
		return null;
	}

}
