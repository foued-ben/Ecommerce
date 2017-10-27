package fr.adaming.managedBean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.modele.Produit;
import fr.adaming.service.IProduitService;

@ManagedBean(name="produitMB")
@RequestScoped
public class ProduitManagedBean {
	
	private Produit produit;
	private List<Produit> listeProduits;

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
	public String ajouterProduit(){
		Produit produitAjoute = produitService.addProduit(this.produit);
		if(produitAjoute.getIdProduit()!=0){
			System.out.println("Ajout effectué");
			// On récupère la liste des produits et on l'ajoute à la session
			List<Produit> listeProduits = produitService.getAllProduits();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeProduits", listeProduits);
			return "accueiladmin"; 
		}else{
			System.out.println("Ajout non effectué");
			return "ajoutproduit";
		}
	}
	
	public String supprimerProduit(){
		int verif = produitService.deleteProduit(this.produit);
		if(verif==1){
			System.out.println("Suppression réussie");
			// On récupère la liste des produits et on l'ajoute à la session
			List<Produit> listeProduits = produitService.getAllProduits();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeProduits", listeProduits);
			return "accueiladmin";
		}else{
			System.out.println("Suppression non effectuée");
			return "suppressionproduit";
		}
	}
	
	public String modifierProduit(){
		int verif = produitService.updateProduit(this.produit);
		if(verif==1){
			System.out.println("Modification effectuée");
			// On récupère la liste des produits et on l'ajoute à la session
			List<Produit> listeProduits = produitService.getAllProduits();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeProduits", listeProduits);
			return "accueiladmin";
		}else{
			System.out.println("Modification non effectuée");
			return "updateproduit";
		}
	}
	
	public void rechercherProduit(){
		Produit produitChercher = produitService.getProduit(this.produit);
		this.produit=produitChercher;
		if(produitChercher!=null){
			System.out.println(produitChercher);
		}else{
			System.out.println("Introuvable");
		}
	}
	
	
}
