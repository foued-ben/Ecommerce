package fr.adaming.service;

import java.util.List;

import fr.adaming.modele.Categorie;

public interface ICategorieService {
	public List<Categorie> getAllCategories() ;  
	public int addCategorie(Categorie c) ; 
	public int deleteProduit(Categorie c) ; 
	public int updateClient(Categorie c) ; 
	public Categorie getProduit(Categorie c) ;
}
