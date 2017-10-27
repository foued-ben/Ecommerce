package fr.adaming.service;

import java.util.List;

import fr.adaming.modele.Produit;

public interface IProduitService {
	public List<Produit> getAllProduits() ;  
	public Produit addProduit(Produit p) ; 
	public int deleteProduit(Produit p) ; 
	public int updateProduit(Produit p) ; 
	public Produit getProduit(Produit p) ;
	public List<Produit> getProduitByName(Produit p);

}
