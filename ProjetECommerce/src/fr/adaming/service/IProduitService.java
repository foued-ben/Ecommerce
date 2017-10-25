package fr.adaming.service;

import java.util.List;

import fr.adaming.modele.Produit;

public interface IProduitService {
	public List<Produit> getAllProduits() ;  
	public int addProduit(Produit p) ; 
	public int deleteProduit(Produit p) ; 
	public int updateClient(Produit p) ; 
	public Produit getProduit(Produit p) ;
}