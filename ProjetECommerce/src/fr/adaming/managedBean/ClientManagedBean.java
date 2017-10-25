package fr.adaming.managedBean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.modele.Administrateur;
import fr.adaming.modele.Client;
import fr.adaming.service.IClientService;

public class ClientManagedBean implements Serializable{

	@EJB
	IClientService cliService;
	
	private Client client;
	private Administrateur admin ; 
	private HttpSession maSession;
	
	
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
	
	
	
	
	
	
}
