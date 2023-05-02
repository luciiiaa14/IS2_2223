package es.unican.is2.Practica5;
import java.util.LinkedList;
import java.util.List;

public class CuentaValores extends Cuenta {

	private List<Valor> valores;
	
	public CuentaValores(String numCuenta) {
		super(numCuenta);
		valores = new LinkedList<Valor>();
	}
	
	//WMC = 1
	public List<Valor> getValores() {
		return valores;
	}
	//WMC = 1
	
	public boolean anhadeValor(Valor valor) {
		for (Valor v:valores) {	//WMC = 1	CCOG=1
			if (v.getEntidad().equals(valor.getEntidad()))	//WMC = 2	CCOG=1
				return false;
		}
		valores.add(valor); //WMC = 1
		return true;
	}
	//WMC = 1
	
}
//WMC= 3
//WMCn= 1
//DIT =1, es un hijo de Cuenta
//NOC= 0, no tiene hijos
//CBO= 1, Valor
//CCOG = 0
