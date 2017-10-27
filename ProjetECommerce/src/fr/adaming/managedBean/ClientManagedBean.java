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
import fr.adaming.modele.LigneCommande;
import fr.adaming.modele.Panier;
import fr.adaming.modele.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IClientService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "cliMB")
@RequestScoped
public class ClientManagedBean implements Serializable {

	@EJB
	IClientService cliService;

	@EJB
	ICategorieService catService;
	@EJB
	IProduitService produitService;
	
	private Client client;
	private Categorie categorie ; 
	private List<Categorie> listeCategories;
	private List<Produit> listeProduits ;
	private Produit produitDemande;
	private int nombreCommande;
	private Administrateur admin;
	private HttpSession maSession;
	private LigneCommande ligneCommande;
	private Panier panier;
	private int id;

	public ClientManagedBean() {
		// instancier le client pour éviter l'exception target unreachble
		this.client = new Client();
		this.categorie = new Categorie() ; 
		this.produitDemande = new Produit();
		this.listeCategories = new ArrayList<Categorie>();
		this.listeProduits = new ArrayList<Produit>() ; 
		this.ligneCommande = new LigneCommande();
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
		//this.listeProduits = new ArrayList<>();
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
	
	public Produit getProduitDemande() {
		return produitDemande;
	}

	public void setProduitDemande(Produit produitDemande) {
		this.produitDemande = produitDemande;
	}

	
	public int getNombreCommande() {
		return nombreCommande;
	}

	public void setNombreCommande(int nombreCommande) {
		this.nombreCommande = nombreCommande;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	// les methodes
	public String instancierPanierDansSession(){
		// On créer un panier
		Panier panierSession =new Panier();
		// On créer une liste de ligne de commande
		System.out.println("Création de la liste");
		List<LigneCommande> listeCommande = new ArrayList<>();
		System.out.println(listeCommande);
		System.out.println("Ajout de la liste dans le panier");
		panierSession.setListeLignesCommande(listeCommande);
		System.out.println("Liste ajoutée");
		// Ajouter le panier à la session
		maSession.setAttribute("panierSession", panierSession);
		System.out.println("Panier ajouté");
		
		// On créer la liste de produit contenant tous les produits.
		List<Produit> listeTousProduits = produitService.getAllProduits();
		// On ajoute la liste à la session
		this.listeProduits = listeTousProduits;
		maSession.setAttribute("listeProduits", listeProduits);
		System.out.println("La liste des produits est"+listeProduits);

		return "accueilclient";
	}
	
	
	
	public String afficherCategories() {

		List<Categorie> listeOut = cliService.getAllCategories();

		if (listeOut != null) {
			this.listeCategories = listeOut;

			 maSession.setAttribute("categoriesListe", listeCategories);

			return "categorie";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur s'est produite"));
			return "accueilclient";
		}
	}

	
	public String afficherProduits() {

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
	
	public String afficherProduitsByCat() {

		
		List<Produit> listeOut = cliService.getAllProduitByCategorie(this.categorie);
		
		System.out.println(listeOut);
		if (listeOut != null) {
			// maSession.setAttribute("categoriesListe", listeCategories);
			this.listeProduits = listeOut;
			maSession.setAttribute("listeProduits", listeProduits);
			System.out.println("La liste des produits est"+listeProduits);

			return "produitclient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur s'est produite"));
			return "accueilclient";
		}
	}
	
	public void ajouterPanier(){
		//On récupère le panier de la session
		Panier panier =(Panier) maSession.getAttribute("panierSession");
		System.out.println(panier);
		// On récupère la liste des commandes déjà effectuée
		List<LigneCommande> listeCommande = panier.getListeLignesCommande();
		System.out.println("La liste");
		System.out.println(listeCommande);
		// On récupère le produit demandé.
		Produit produitCherche= produitService.getProduit(this.produitDemande);
		// On ajoute le produit à la ligne de commande
		this.ligneCommande.setProduit(produitCherche);
		// On ajoute le nombre d'objet commande à la ligne de commande
		this.ligneCommande.setQuantite(nombreCommande);
		// On calcule le prix. 
		double prixLigne = nombreCommande*produitCherche.getPrix();
		// On ajoute le prix dans la ligne de commande.
		ligneCommande.setPrix(prixLigne);
		
		//On ajoute la ligne de commande à la liste des commandes.
		listeCommande.add(ligneCommande);
		System.out.println(listeCommande);
		
		// On ajoute la liste dans le panier.
		panier.setListeLignesCommande(listeCommande);
		// On ajoute le panier à la session
		maSession.setAttribute("panierSession", panier);
		
	}
	
}
