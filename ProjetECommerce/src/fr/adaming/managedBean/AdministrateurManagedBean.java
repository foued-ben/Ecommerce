package fr.adaming.managedBean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.modele.Administrateur;
import fr.adaming.service.IAdministrateurService;

@ManagedBean(name="aMB")
@RequestScoped
public class AdministrateurManagedBean implements Serializable {
	
	@EJB
	private IAdministrateurService adminService ; 
	
	Administrateur admin ; 
	
	
	public AdministrateurManagedBean() {
		this.admin=new Administrateur();
	}


	public IAdministrateurService getAdminService() {
		return adminService;
	}


	public void setAdminService(IAdministrateurService adminService) {
		this.adminService = adminService;
	}
	
	
	// les methodes m�tiers du managedBean
	public String seConnecter(){
		try {
			Administrateur admin_out = adminService.connexion(this.admin); 
			

			
			//ajout de l'admin dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminSession", admin_out);
			
			
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
