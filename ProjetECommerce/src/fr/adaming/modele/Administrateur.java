package fr.adaming.modele;

public class Administrateur {
	// Attributs
	private int idAdmin;
	private String identifiant;
	private String mdp;
	// Constructeurs
	public Administrateur() {
		super();
	}
	public Administrateur(String identifiant, String mdp) {
		super();
		this.identifiant = identifiant;
		this.mdp = mdp;
	}
	public Administrateur(int idAdmin, String identifiant, String mdp) {
		super();
		this.idAdmin = idAdmin;
		this.identifiant = identifiant;
		this.mdp = mdp;
	}
	// Getters/Setters
	public int getIdAdmin() {
		return idAdmin;
	}
	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}
	public String getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	//toString
	@Override
	public String toString() {
		return "Administrateur [idAdmin=" + idAdmin + ", identifiant=" + identifiant + ", mdp=" + mdp + "]";
	}
	
	
}
