package fr.adaming.managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name="verifMB")
public class VerifSuppression {

	public void verifCategorie(){
		message("Etes vous sure de supprimer cette cat�gorie ?. Les produits affect�s � cette cat�gorie seront supprim�s.");
		System.out.println("Etes vous sure de supprimer cette cat�gorie ?. Les produits affect�s � cette cat�gorie seront supprim�s.");
	}
	
	public void message(String alerte){
		
	}
}
