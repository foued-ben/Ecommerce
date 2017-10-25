package fr.adaming.managedBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import fr.adaming.modele.Produit;
import fr.adaming.service.IProduitService;

@ManagedBean(name="produitMB")
@RequestScoped
public class ProduitManagedBean {
	
	private Produit produit;
	
	@EJB
	private IProduitService produitService;
// Constructeur
	public ProduitManagedBean() {
		this.produit=new Produit();
	}
	
	// Getters/Setters
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public IProduitService getProduitService() {
		return produitService;
	}
	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}

	// Méthodes Propres.
	public void ajouterProduit(){
		Produit produitAjoute = produitService.addProduit(this.produit);
		if(produitAjoute.getIdProduit()!=0){
			System.out.println("Ajout effectué");
		}else{
			System.out.println("Ajout non effectué");
		}
	}
	
	public void supprimerProduit(){
		int verif = produitService.deleteProduit(this.produit);
		if(verif==1){
			System.out.println("Suppression réussie");
		}else{
			System.out.println("Suppression non effectuée");
		}
	}
	
	
}
