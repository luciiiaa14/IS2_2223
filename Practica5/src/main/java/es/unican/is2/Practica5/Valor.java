package es.unican.is2.Practica5;
/**
 * Clase que representa un valor en bolsa (paquete de acciones). 
 * Cada valor contiene un número de acciones 
 * de una de las entidades del IBEX 35
 */
public class Valor {
	
	private String entidad;	
	private int numAcciones;
	private double cotizacion;
	
	public Valor(String entidad, int numAcciones, double cotizacionActual) {
		this.entidad = entidad;
		this.numAcciones = numAcciones;
		this.cotizacion = cotizacionActual;
	}
	//WMC = 1
	
	public int getNumValores() {
		return numAcciones;
	}
	//WMC = 1

	public void setNumValores(int numValores) {
		this.numAcciones = numValores;
	}
	//WMC = 1

	public double getCotizacion() {
		return cotizacion;
	}
	//WMC = 1
	
	public void setCotizacion(double cotizacion) {
		this.cotizacion = cotizacion;
	}
	//WMC = 1

	public String getEntidad() {
		return entidad;
	}
	//WMC = 1

	@Override
	public boolean equals(Object obj) {
		Valor other = (Valor)obj;
		return (entidad.equals(other.entidad) & numAcciones==other.numAcciones);

	}
	//WMC = 1
	
	
	//WMC =7
	//WMCn=1
	//CBO=1 Object
	//DIT=0
	//NOC=0
	//CCOG=1 Valor
	
	

}