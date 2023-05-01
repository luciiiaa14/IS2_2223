package es.unican.is2.Practica5;
import java.util.LinkedList;
import java.util.List;

public class Cliente {
	
	public String nombre;
	public String calle;
	public String zip;
	public String localidad;
	public String telefono;
	public String dni;
	
    private List<Cuenta> Cuentas = new LinkedList<Cuenta>();

 	public Cliente(String titular, String calle, String zip, String localidad, 
 			String telefono, String dni) {  
		this.nombre = titular;
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
		this.telefono = telefono;
		this.dni = dni;
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
	
	public void anhadeCuenta(Cuenta c) {
		Cuentas.add(c);
	}
	//WMC = 1, no realiza operaciones complejas, simplemente anhade
	//CBO = 1, accede a Cuenta pasando el parametro
	
	public double getSaldoTotal() {
		double total = 0.0;
		for (Cuenta c: Cuentas) {  //WMC =1		//CCOG= 1 
			if (c instanceof CuentaAhorro) {//WMC=2		//CCOG = 2
				total += ((CuentaAhorro) c).getSaldo();
			} else if (c instanceof CuentaValores)  {//WMC=1		//CCOG = 1
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
	
//WMC TOTAL = 14
//WMCn = 14 / 10 = 1.4
//CBO TOTAL = 3
//DIT TOTAL = 0
//NOC = 0, no tiene hijos 
//CCOG = 7
	
	//NOC CUENTA 2 DIT 0 tiene 2 hijos
	//cuenta ahorro dit 1 es hijo 
		
}