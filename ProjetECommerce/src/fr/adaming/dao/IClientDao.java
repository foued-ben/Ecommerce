package fr.adaming.dao;

import java.util.List;

import fr.adaming.modele.Categorie;
import fr.adaming.modele.Client;
import fr.adaming.modele.Panier;
import fr.adaming.modele.Produit;



public interface IClientDao {
	public List<Categorie> getAllCategories() ; 
	public List<Produit> getAllProduitByCategorie(Categorie c) ; 
	public List<Produit> getProduitsSelect(List<Produit> lp) ; 
	public List<Produit> getProduitsByMot(String mot) ; 
	public Produit addProduitPanier(Produit p, int quantite, Panier pan) ;
	public int deleteProduitPanier(Produit p, Panier pan) ; 
	public int enregitrementClient(Client c, Panier pan) ; 



}
