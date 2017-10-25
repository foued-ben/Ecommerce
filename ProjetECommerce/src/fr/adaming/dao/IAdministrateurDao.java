package fr.adaming.dao;

import javax.ejb.Local;

import fr.adaming.modele.Administrateur;

@Local
public interface IAdministrateurDao {

	
	public Administrateur connexion(Administrateur a) ; 

}
