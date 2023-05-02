package es.unican.is2.Practica5;
import java.util.LinkedList;
import java.util.List;

public class Cliente {
	public static final double CERO_EUROS = 0.0;
	private String calle;
	private String zip;
	private String localidad;
	public String nombre;
	
	public String telefono;
	public String dni;
	
    private List<Cuenta> Cuentas = new LinkedList<Cuenta>();
 
    
    
 	public Cliente(String titular,String telefono, String dni, Direccion direccion) {  
		this.nombre = titular;
		this.telefono = telefono;
		this.dni = dni;
		
	}
 	//WMC= 1, no hay bucles; solo hay un camino posible
 	//CBO = 0
	
	
	
	public void anhadeCuenta(Cuenta c) {
		Cuentas.add(c);
	}
	//WMC = 1, no realiza operaciones complejas, simplemente anhade
	//CBO = 1, accede a Cuenta pasando el parametro
	
	public void cambiaDireccion(Direccion dir) {
		this.calle = dir.calle;
		this.zip = dir.zip;
		this.localidad = dir.localidad;
	}
	//WMC= 1, no hay bucles; solo hay un camino posible
	//CBO = 0
	
	
	public double getSaldoTotal() {
		double total = CERO_EUROS;
		for (Cuenta c: Cuentas) {  //WMC =1		//CCOG= 1 
			if (c instanceof CuentaAhorro) {//WMC=2		//CCOG = 2
				total += ((CuentaAhorro) c).getSaldo();
			} else {//WMC=1		//CCOG = 1
				for (Valor v: ((CuentaValores) c).getValores()) { //WMC=1		//CCOG = 3
					total += v.getCotizacion()*v.getNumValores();
				}
			}
		}
		return total;
	}
	//WMC = 5
	//CBO = 2 Accede a CuentaAhorro y CuentaValores luego depende de las clases Cuenta y Valor
	//CCOG = 7
	
	public String getNombre() {
		return nombre;
	}
	//WMC = 1
	//CBO = 0

	public String getTelefono() {
		return telefono;
	}
	//WMC = 1
	//CBO = 0

	public String getDni() {
		return dni;
	}
	//WMC = 1
	//CBO = 0
	
//WMC TOTAL = 10
//WMCn = 10 / 6 = 1.67
//CBO TOTAL = 4
//DIT TOTAL = 0
//NOC = 0, no tiene hijos 
//CCOG = 7
	

		
}