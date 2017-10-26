package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
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
import fr.adaming.modele.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IClientService;

@ManagedBean(name = "cliMB")
@RequestScoped
public class ClientManagedBean implements Serializable {

	@EJB
	IClientService cliService;

	@EJB
	ICategorieService catService;

	private Client client;
	private Categorie categorie ; 
	private List<Categorie> listeCategories;
	private List<Produit> listeProduits ;
	private Administrateur admin;
	private HttpSession maSession;

	public ClientManagedBean() {
		// instancier le client pour éviter l'exception target unreachble
		this.client = new Client();
		this.categorie = new Categorie() ; 
		this.listeCategories = new ArrayList<Categorie>();
		this.listeProduits = new ArrayList<Produit>() ; 

	}

	@PostConstruct // cette annotation fera que cette méthode s'executera
					// directement après l'instanciation du managedBean
	public void init() {
		// récup context
		FacesContext context = FacesContext.getCurrentInstance();

		// récup session
		this.maSession = (HttpSession) context.getExternalContext().getSession(false);

		// recuperation e l'agent à partir de la session
		this.admin = (Administrateur) maSession.getAttribute("adminSession");
		
		this.listeCategories=cliService.getAllCategories();
		this.listeProduits = cliService.getAllProduits();

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

	public List<Categorie> getListeCategories() {
		return listeCategories;
	}

	public void setListeCategories(List<Categorie> listeCategories) {
		this.listeCategories = listeCategories;
	}

	
	
	
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}

	// les methodes
	public String afficherCategories() {

		List<Categorie> listeOut = cliService.getAllCategories();

		if (listeOut != null) {
			// maSession.setAttribute("categoriesListe", listeCategories);
			this.listeCategories = listeOut;

			return "categorie";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur s'est produite"));
			return "accueilclient";
		}
	}

	
	public String afficherProduits() {

		List<Produit> listeOut = cliService.getAllProduits();

		if (listeOut != null) {
			// maSession.setAttribute("categoriesListe", listeCategories);
			this.listeProduits = listeOut;

			return "produitclient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur s'est produite"));
			return "accueilclient";
		}
	}
	
	public String afficherProduitsByCat() {

		List<Produit> listeOut = cliService.getAllProduitByCategorie(this.categorie);

		if (listeOut != null) {
			// maSession.setAttribute("categoriesListe", listeCategories);
			this.listeProduits = listeOut;

			return "produitclient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur s'est produite"));
			return "accueilclient";
		}
	}
	
}
