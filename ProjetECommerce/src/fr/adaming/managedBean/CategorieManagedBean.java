package fr.adaming.managedBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import fr.adaming.modele.Categorie;
import fr.adaming.service.ICategorieService;

@ManagedBean(name="categorieMB")
@RequestScoped
public class CategorieManagedBean {

	@EJB
	private ICategorieService categorieService;
	
	// Attributs 
	private Categorie categorie;
	// Constructeurs

	public CategorieManagedBean() {
		super();
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
	public String ajouterCategorie(){
		Categorie categorieAjoute = categorieService.addCategorie(this.categorie);
		if(categorieAjoute.getIdCategorie()!=0){
			// La cat�gorie a bien �t� ajout�e.
			System.out.println("Ajout�e");
			return "accueiladmin";
		}else{
			return "ajoutcategorie";
		}
	}
	
	
}
