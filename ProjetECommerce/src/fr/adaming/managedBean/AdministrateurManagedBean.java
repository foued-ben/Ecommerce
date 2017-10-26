package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.adaming.modele.Administrateur;
import fr.adaming.modele.Categorie;
import fr.adaming.modele.Produit;
import fr.adaming.service.IAdministrateurService;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name="aMB")
@RequestScoped
public class AdministrateurManagedBean implements Serializable {
	
	@EJB
	private IAdministrateurService adminService ; 
	@EJB
	private ICategorieService categorieService;
	@EJB
	private IProduitService produitService;
	private Administrateur admin ; 
	private List<Categorie> listeCategories;
	private List<Produit> listeProduits;

	public AdministrateurManagedBean() {
		this.admin=new Administrateur();
		this.listeCategories =new ArrayList<>();
	}


	public IAdministrateurService getAdminService() {
		return adminService;
	}
	


	public void setAdminService(IAdministrateurService adminService) {
		this.adminService = adminService;
	}
	
	
	public Administrateur getAdmin() {
		return admin;
	}


	public void setAdmin(Administrateur admin) {
		this.admin = admin;
	}
	
	public List<Categorie> getListeCategories() {
		return listeCategories;
	}


	public void setListeCategories(List<Categorie> listeCategories) {
		this.listeCategories = listeCategories;
	}

	
	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}


	// les methodes m�tiers du managedBean
	public String seConnecter(){
		try {
			Administrateur admin_out = adminService.connexion(this.admin); 
			
		
			//ajout de l'admin dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminSession", admin_out);
			
			// On r�cup�re la liste des cat�gories
			List<Categorie> listeCatTemp = categorieService.getAllCategories();
			if (listeCatTemp!=null){
				this.listeCategories = listeCatTemp;
				// On ajoute la liste � la session
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeCategories", listeCategories);
				System.out.println("Liste des cat�gories");
				System.out.println(listeCategories);
			}else {
				System.out.println("pas de cat�gories");
			}
			
			//On r�cup�re la liste des produits
			List<Produit> listeProdTemp = produitService.getAllProduits();

			if(listeProdTemp!=null){
				this.listeProduits = listeProdTemp;
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeProduits", listeProduits);
				System.out.println("Liste des produits");
				System.out.println(listeProduits);

			}else{
				System.out.println("Pas de produits propos�");
			}
			
			return "succes" ; 
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Identifiants �rron�(s)"));
			return "echec" ; 
		}
		
	}
	
			// la m�thode pour se d�connecter
			public String seDeconnecter(){
				// r�cup�rer la session et la fermer
				FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
				return "login" ; 
			}
	
}
