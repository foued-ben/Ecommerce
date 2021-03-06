package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.modele.Produit;

@Local
public interface IProduitDao {
	public List<Produit> getAllProduits() ;  
	public Produit addProduit(Produit p) ; 
	public int deleteProduit(Produit p) ; 
	public int updateClient(Produit p) ; 
	public Produit getProduit(Produit p) ;
	public List<Produit> getProduitByName(Produit p);
}
