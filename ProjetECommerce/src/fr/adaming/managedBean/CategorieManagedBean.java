package fr.adaming.managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceException;

import fr.adaming.modele.Categorie;
import fr.adaming.modele.Produit;
import fr.adaming.service.ICategorieService;

@ManagedBean(name = "categorieMB")
@RequestScoped
public class CategorieManagedBean {

	@EJB
	private ICategorieService categorieService;

	// Attributs
	private Categorie categorie;

	// Constructeurs

	public CategorieManagedBean() {
		this.categorie = new Categorie();
	}

	// Getters/Setters
	public ICategorieService getCategorieService() {
		return categorieService;
	}

	public void setCategorieService(ICategorieService categorieService) {
		this.categorieService = categorieService;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	// M�thodes du ManagedBean
	public String ajouterCategorie() {
		Categorie categorieAjoute = categorieService.addCategorie(this.categorie);
		if (categorieAjoute.getIdCategorie() != 0) {
			// La cat�gorie a bien �t� ajout�e.
			// On r�cup�re la liste des cat�gories et on l'ajoute dans la
			// session
			List<Categorie> listeCatTemp = categorieService.getAllCategories();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeCategories", listeCatTemp);

			System.out.println("Ajout�e");
			return "accueiladmin";
		} else {
			return "ajoutcategorie";
		}
	}

	public void listerCategorie() {
		List<Categorie> listeCategories = categorieService.getAllCategories();
		System.out.println(listeCategories);
	}

	public String supprimerCategorie() {
		int verif = categorieService.deleteCategorie(this.categorie);
		if (verif == 1) {
			// On r�cup�re la liste des cat�gories et on l'ajoute dans la
			// session
			List<Categorie> listeCatTemp = categorieService.getAllCategories();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeCategories", listeCatTemp);
			return "accueiladmin";
		}
		if (verif == -1) {
			System.out.println("Impossible");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
					"La suppression de cette cat�gorie est impossible certains produits propos�s sont de cette  cat�gorie. Veuillez supprimer les produits et recommencer."));
			return "suppressioncategorie";
		}
		System.out.println("Rien d'ajout�");
		return "suppressioncategorie";

	}

	public String modifierCategorie() {
		int verif = categorieService.updateCategorie(this.categorie);
		if (verif == 1) {
			System.out.println("Modification effectu�e");
			// On r�cup�re la liste des cat�gories et on l'ajoute dans la
			// session
			List<Categorie> listeCatTemp = categorieService.getAllCategories();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeCategories", listeCatTemp);
			return "accueiladmin";
		} else {
			System.out.println("Modification non effectu�e");
			return "accueiladmin";
		}
	}

	public void rechercheCategorie() {
		Categorie categorieCherche = categorieService.getCategorie(this.categorie);
		this.categorie = categorieCherche;
		if (categorieCherche != null) {
			System.out.println("Trouv�e");
			System.out.println(categorieCherche);
		} else {
			System.out.println("Pas trouv�e");
			System.out.println(categorieCherche);
		}
	}

}
