package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import fr.adaming.modele.Commande;
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
	private Categorie categorie;
	private List<Categorie> listeCategories;
	private List<Produit> listeProduits;
	private List<Produit> listeProduitsByMot;

	private Produit produitDemande;
	private int nombreCommande;
	private double prixTot;
	private String mot;
	private Administrateur admin;
	private HttpSession maSession;
	private LigneCommande ligneCommande;
	private Panier panier;
	private int id;

	public ClientManagedBean() {
		// instancier le client pour �viter l'exception target unreachble
		this.client = new Client();
		this.categorie = new Categorie();
		this.produitDemande = new Produit();
		this.listeCategories = new ArrayList<Categorie>();
		this.listeProduits = new ArrayList<Produit>();
		this.listeProduitsByMot = new ArrayList<Produit>();

		this.ligneCommande = new LigneCommande();
	}

	@PostConstruct // cette annotation fera que cette m�thode s'executera
					// directement apr�s l'instanciation du managedBean
	public void init() {
		// r�cup context
		FacesContext context = FacesContext.getCurrentInstance();

		// r�cup session
		this.maSession = (HttpSession) context.getExternalContext().getSession(false);

		// recuperation e l'agent � partir de la session
		this.admin = (Administrateur) maSession.getAttribute("adminSession");

		this.listeCategories = cliService.getAllCategories();
		this.listeProduits = cliService.getAllProduits();
		// this.listeProduits = new ArrayList<>();
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

	public double getPrixTot() {
		return prixTot;
	}

	public void setPrixTot(double prixTot) {
		this.prixTot = prixTot;
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

	public String getMot() {
		return mot;
	}

	public void setMot(String mot) {
		this.mot = mot;
	}

	public LigneCommande getLigneCommande() {
		return ligneCommande;
	}

	public void setLigneCommande(LigneCommande ligneCommande) {
		this.ligneCommande = ligneCommande;
	}

	public List<Produit> getListeProduitsByMot() {
		return listeProduitsByMot;
	}

	public void setListeProduitsByMot(List<Produit> listeProduitsByMot) {
		this.listeProduitsByMot = listeProduitsByMot;
	}

	// les methodes
	public String instancierPanierDansSession() {
		// On cr�er un panier
		Panier panierSession = new Panier();
		// On cr�er une liste de ligne de commande
		System.out.println("Cr�ation de la liste");
		List<LigneCommande> listeCommande = new ArrayList<>();
		System.out.println(listeCommande);
		System.out.println("Ajout de la liste dans le panier");
		panierSession.setListeLignesCommande(listeCommande);
		System.out.println("Liste ajout�e");
		// Ajouter le panier � la session
		maSession.setAttribute("panierSession", panierSession);
		System.out.println("Panier ajout�");

		// On cr�er la liste de produit contenant tous les produits.
		List<Produit> listeTousProduits = produitService.getAllProduits();
		// On ajoute la liste � la session
		this.listeProduits = listeTousProduits;
		maSession.setAttribute("listeProduits", listeProduits);
		System.out.println("La liste des produits est" + listeProduits);

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
			System.out.println("La liste des produits est" + listeProduits);

			return "produitclient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur s'est produite"));
			return "accueilclient";
		}
	}

	public String ajouterPanier() {
		// On r�cup�re le panier de la session
		Panier panier = (Panier) maSession.getAttribute("panierSession");
		// On r�cup�re la liste des commandes d�j� effectu�e
		List<LigneCommande> listeCommande = panier.getListeLignesCommande();
		// On r�cup�re le produit demand�.
		Produit produitCherche = produitService.getProduit(this.produitDemande);
		
		
		// On initialise la ligne de commande qui devra �tre supprimer.
		LigneCommande ligneCommandeSupp = null;
			
		// On v�rifie si le produit a �t� command� avant 
		for (LigneCommande commande : listeCommande){
			Produit produitTemp = commande.getProduit();
			int idProduitTemp =produitTemp.getIdProduit();
			System.out.println(idProduitTemp);
			if(idProduitTemp == produitDemande.getIdProduit() ){
				// On ajoute la quantit� d�j� command� � la quantit� demand�.
				int nombreCommandeActualise = this.nombreCommande+commande.getQuantite();
				this.nombreCommande = this.nombreCommande+commande.getQuantite();
				System.out.println("Nombre total command� " +nombreCommandeActualise);
				// On supprime la ligne de commande de la liste 
				ligneCommandeSupp = commande;
			}
		}
		// On v�rifie que la commande est possible avec le nouveau nombre demand�.
		if(nombreCommande<= produitCherche.getQuantite()){
			listeCommande.remove(ligneCommandeSupp);
		} else{
			
		}
		
		// On v�rifie qu'une quantit� suffisante de produit est en stock
		if (nombreCommande <= produitCherche.getQuantite() && nombreCommande > 0) {
			System.out.println("Stock suffisant");
			// On ajoute le produit � la ligne de commande
			this.ligneCommande.setProduit(produitCherche);
			// On ajoute le nombre d'objet commande � la ligne de commande
			this.ligneCommande.setQuantite(nombreCommande);
			// On calcule le prix.
			double prixLigne = nombreCommande * produitCherche.getPrix();
			// On ajoute le prix dans la ligne de commande.
			ligneCommande.setPrix(prixLigne);

			// On ajoute la ligne de commande � la liste des commandes.
			listeCommande.add(ligneCommande);
			System.out.println(listeCommande);

			// On ajoute la liste dans le panier.
			panier.setListeLignesCommande(listeCommande);
			// On ajoute le panier � la session
			maSession.setAttribute("panierSession", panier);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Le produit a bien �t� ajout� au panier"));

		} else if (nombreCommande <= 0) {
			System.out.println("Veuiller commander un nombre positif d'objets ");
		} else {
			System.out.println("Stock insuffisant");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Cher client nos stocks sont insuffisants"));

		}

		 return "produitclient";
	}

	public String supprProdPanier() {
		// On r�cup�re le panier de la session
		Panier panier = (Panier) maSession.getAttribute("panierSession");
		System.out.println(panier);
		// On r�cup�re la liste des commandes d�j� effectu�e
		List<LigneCommande> listeCommande = panier.getListeLignesCommande();

		// On supprime la ligne de commande � la liste des commandes.
		listeCommande.remove(this.ligneCommande);
		System.out.println(listeCommande);

		// On ajoute la liste dans le panier.
		panier.setListeLignesCommande(listeCommande);
		// On ajoute le panier � la session
		maSession.setAttribute("panierSession", panier);
		return "panierclient";
	}

	public String rechProdByName() {
		List<Produit> listeChercher = cliService.getProduitsByMot(this.mot);
		System.out.println(listeChercher);
		// this.produitDemande.setDesignation(mot);
		// List<Produit> listeChercher2 =
		// produitService.getProduitByName(this.produitDemande);
		// System.out.println();
		// this.listeProduitsByMot = listeChercher;
		maSession.setAttribute("listeProduitsByMot", listeChercher);
		return "rechProdByMot";
	}

	public String enregistrement() {
		Client cli_enr = cliService.enregitrementClient(this.client);

		this.client = cli_enr;
		// On r�cup�re la liste des commandes
		Panier panier = (Panier) maSession.getAttribute("panierSession");
		List<LigneCommande> listeCommande = panier.getListeLignesCommande();

		double prix = 0;

		for (LigneCommande commande : listeCommande) {
			System.out.println(commande.getPrix());
			prix = prix + commande.getPrix();
		}

		this.prixTot = prix;

		// maSession.setAttribute("panierSession", panier);

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le client a bien �t� enregistr�"));

		// Ajout de la commmande � la base de donn�es. La commande est compos�e
		// de la liste des commandes (identique � celle du panier)
		// d'une date et d'un client.
		Date dateCommande = new Date();
		Commande commandeTemp = new Commande(dateCommande);

		// On ajoute le client et la liste des commandes � commandeTemp.
		commandeTemp.setListeLigneCommande(listeCommande);
		commandeTemp.setClient(cli_enr);
		// On enregistre la commande dans la base
		cliService.enregistrementCommabde(commandeTemp);
		System.out.println(commandeTemp);
		
		// Gestion du stock.
		for(LigneCommande ligne : listeCommande ){
			// on r�cup�re le produit command�
			Produit produitTemp = ligne.getProduit();
			System.out.println(produitTemp);
			// On change la quantit� de chaque produit 
			int nouveauStock = produitTemp.getQuantite()-ligne.getQuantite();
			produitTemp.setQuantite(nouveauStock);
			// Le produit avec le nouveau stocke est utilis� pour modifi� la valeur dans la table.
			produitService.updateProduit(produitTemp);
			System.out.println(produitTemp);
		}
		
		
		// Le panier doit �tre vid�.
		Panier nouveauPanier = new Panier();
		List<LigneCommande> nouvellelisteCommande = new ArrayList<>();
		nouveauPanier.setListeLignesCommande(nouvellelisteCommande);
		// Le panier vide est ajout� dans la session et remplace l'ancien panier. Une nouvelle commande devra �tre refaite de A � Z.

		maSession.setAttribute("panierSession", nouveauPanier);
		return "enregistrementclient";
		

	}

	public String nouvelleCommande() {

		// On r�cup�re le client portant l'id donn�
		Client clientTemp = cliService.recuperClient(this.client);
		this.client = clientTemp;
		// On r�cup�re la liste des commandes
		Panier panier = (Panier) maSession.getAttribute("panierSession");
		List<LigneCommande> listeCommande = panier.getListeLignesCommande();

		// Calcul du prix 
		double prix = 0;

		for (LigneCommande commande : listeCommande) {
			System.out.println(commande.getPrix());
			prix = prix + commande.getPrix();
		}

		this.prixTot = prix;

		// On cr�er la commande avec une date
		Date dateCommande = new Date();
		Commande commandeTemp = new Commande(dateCommande);
		
		// On lui ajoute le contenu du panier et le client
		// On ajoute le client et la liste des commandes � commandeTemp.
		commandeTemp.setListeLigneCommande(listeCommande);
		commandeTemp.setClient(this.client);
		// On enregistre la commande dans la base
		cliService.enregistrementCommabde(commandeTemp);

		
		// Gestion du stock.
		for(LigneCommande ligne : listeCommande ){
			// on r�cup�re le produit command�
			Produit produitTemp = ligne.getProduit();
			System.out.println(produitTemp);
			// On change la quantit� de chaque produit 
			int nouveauStock = produitTemp.getQuantite()-ligne.getQuantite();
			produitTemp.setQuantite(nouveauStock);
			// Le produit avec le nouveau stocke est utilis� pour modifi� la valeur dans la table.
			produitService.updateProduit(produitTemp);
			System.out.println(produitTemp);
		}
		
		// Le panier doit �tre vid�.
		Panier nouveauPanier = new Panier();
		List<LigneCommande> nouvellelisteCommande = new ArrayList<>();
		nouveauPanier.setListeLignesCommande(nouvellelisteCommande);
		// Le panier vide est ajout� dans la session et remplace l'ancien panier. Une nouvelle commande devra �tre refaite de A � Z.

		maSession.setAttribute("panierSession", nouveauPanier);
		return "enregistrementclient";
	}

}
