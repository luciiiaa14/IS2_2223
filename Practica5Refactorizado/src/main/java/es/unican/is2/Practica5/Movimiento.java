package es.unican.is2.Practica5;
import java.time.LocalDateTime;

public class Movimiento {
	private String concepto;
	private LocalDateTime fecha;
	private double importe;

	public double getImporte() {
		return importe;
	}
	//WMC = 1

	public void setImporte(double newMImporte) {
		importe = newMImporte;
	}
	
	//WMC = 1
	public String getConcepto() {
		return concepto;
	}
	//WMC = 1

	public void setConcepto(String newMConcepto) {
		concepto = newMConcepto;
	}
	//WMC = 1

	public LocalDateTime getFecha() {
		return fecha;
	}
	//WMC = 1

	public void setFecha(LocalDateTime newMFecha) {
		fecha = newMFecha;
	}
	//WMC = 1
	
	
	//WMC= 6
	//WMCn= 1
	//CBO=0
	//DIT =0
	//NOC=0
	//CCOG= 0
}