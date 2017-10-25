package fr.adaming.modele;

public class LigneCommande {
	
	// attributs
	private int quantite ; 
	private double prix ;
	
	// constructeurs
	public LigneCommande() {
		super();
	}


	public LigneCommande(int quantite, double prix) {
		super();
		this.quantite = quantite;
		this.prix = prix;
	}

	//getters-setters
	public int getQuantite() {
		return quantite;
	}


	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}


	public double getPrix() {
		return prix;
	}


	public void setPrix(double prix) {
		this.prix = prix;
	}

	//toString
	@Override
	public String toString() {
		return "LigneCommande [quantite=" + quantite + ", prix=" + prix + "]";
	} 
	
	
	
}
