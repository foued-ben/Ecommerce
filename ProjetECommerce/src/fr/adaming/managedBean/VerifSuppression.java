package fr.adaming.managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name="verifMB")
public class VerifSuppression {

	public void verifCategorie(){
		message("Etes vous sure de supprimer cette catégorie ?. Les produits affectés à cette catégorie seront supprimés.");
		System.out.println("Etes vous sure de supprimer cette catégorie ?. Les produits affectés à cette catégorie seront supprimés.");
	}
	
	public void message(String alerte){
		
	}
}
