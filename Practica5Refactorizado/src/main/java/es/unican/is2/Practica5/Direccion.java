package es.unican.is2.Practica5;

public class Direccion {
	public String calle;
	public String zip;
	public String localidad;
	
	public Direccion(String calle, String zip, String localidad) {
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
	}
	//WMC= 1, no hay bucles; solo hay un camino posible
	//CBO = 0
	
	public void cambiaDireccion(String calle, String zip, String localidad) {
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
	}
	//WMC= 1, no hay bucles; solo hay un camino posible
	//CBO = 0
	
	
	public String getCalle() {
		return calle;
	}
	//WMC = 1
	//CBO = 0
	
	public String getZip() {
		return zip;
	}
	//WMC = 1
	//CBO = 0

	public String getLocalidad() {
		return localidad;
	}
	//WMC = 1
	//CBO = 0
	
	
	//WMC TOTAL = 5
	//WMCn =1
	//CBO=0
	//DIT=0
	//NOC =0
	//CCOG=0

}
