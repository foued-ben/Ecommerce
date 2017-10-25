package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.modele.Administrateur;
import fr.adaming.modele.Categorie;
import fr.adaming.modele.Client;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IClientService;

@ManagedBean(name="cliMB")
@RequestScoped
public class ClientManagedBean implements Serializable{

	@EJB
	IClientService cliService;
	
	@EJB
	ICategorieService catService ; 
	
	private Client client;
	private Administrateur admin ; 
	private HttpSession maSession;
	private Categorie categorie ;
	
	
	public ClientManagedBean() {
		// instancier le client pour éviter l'exception target unreachble
		this.client = new Client();

	}
	
	@PostConstruct // cette annotation fera que cette méthode s'executera directement après l'instanciation du managedBean 
	public void init(){
		// récup context
		FacesContext context = FacesContext.getCurrentInstance();
		
		//récup session
		this.maSession=(HttpSession) context.getExternalContext().getSession(false);
		
		// recuperation e l'agent à partir de la session
		this.admin= (Administrateur) maSession.getAttribute("agentSession");
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Administrateur getAdmin() {
		return admin;
	}

	public void setAdmin(Administrateur admin) {
		this.admin = admin;
	}
	
	// les methodes
	public String afficherCategories() {


		Client client_out = this.client ; 
		List<Categorie> listeCategories = cliService.getAllCategories();
		
		if(listeCategories !=null){
			maSession.setAttribute("categoriesListe", listeCategories);

			//ajout du client dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("clientSession", client_out);
			
			return "categorie"; 
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur s'est produite"));
			return "accueilclient" ; 
		}
	}
	
	
	
	
}
