package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.modele.Administrateur;
import fr.adaming.modele.Categorie;
import fr.adaming.service.IAdministrateurService;
import fr.adaming.service.ICategorieService;

@ManagedBean(name="aMB")
@RequestScoped
public class AdministrateurManagedBean implements Serializable {
	
	@EJB
	private IAdministrateurService adminService ; 
	@EJB
	private ICategorieService categorieService;
	private Administrateur admin ; 
	
	
	public AdministrateurManagedBean() {
		this.admin=new Administrateur();
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


	// les methodes métiers du managedBean
	public String seConnecter(){
		try {
			Administrateur admin_out = adminService.connexion(this.admin); 
			
		
			//ajout de l'admin dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminSession", admin_out);
			
			// On récupère la liste des catégories
			List<Categorie> listeCategories = categorieService.getAllCategories();
			System.out.println("Liste des catégories");
			System.out.println(listeCategories);
			
			return "succes" ; 
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Identifiants érroné(s)"));
			return "echec" ; 
		}
		
	}
	
			// la méthode pour se déconnecter
			public String seDeconnecter(){
				// récupérer la session et la fermer
				FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
				return "login" ; 
			}
	
}
