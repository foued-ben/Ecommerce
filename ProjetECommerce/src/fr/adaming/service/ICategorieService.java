package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.modele.Categorie;
@Local
public interface ICategorieService {
	public List<Categorie> getAllCategories() ;  
	public Categorie addCategorie(Categorie c) ; 
	public int deleteCategorie(Categorie c) ; 
	public Categorie updateClient(Categorie c) ; 
	public Categorie getProduit(Categorie c) ;
}
