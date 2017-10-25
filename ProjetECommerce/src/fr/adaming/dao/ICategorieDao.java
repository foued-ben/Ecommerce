package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.modele.Categorie;


@Local
public interface ICategorieDao {
	public List<Categorie> getAllCategories() ;  
	public int addCategorie(Categorie c) ; 
	public int deleteProduit(Categorie c) ; 
	public int updateClient(Categorie c) ; 
	public Categorie getProduit(Categorie c) ;
}
