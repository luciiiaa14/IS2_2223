package es.unican.is2.Practica5;
import java.time.LocalDateTime;

public class Movimiento {
	private String mConcepto;
	private LocalDateTime mFecha;
	private double mImporte;

	public double getI() {
		return mImporte;
	}
	//WMC = 1

	public void setI(double newMImporte) {
		mImporte = newMImporte;
	}
	
	//WMC = 1
	public String getC() {
		return mConcepto;
	}
	//WMC = 1

	public void setC(String newMConcepto) {
		mConcepto = newMConcepto;
	}
	//WMC = 1

	public LocalDateTime getF() {
		return mFecha;
	}
	//WMC = 1

	public void setF(LocalDateTime newMFecha) {
		mFecha = newMFecha;
	}
	//WMC = 1
	
	
	//WMC= 6
	//WMCn= 1
	//CBO=0
	//DIT =0
	//NOC=0
	//CCOG= 0
}