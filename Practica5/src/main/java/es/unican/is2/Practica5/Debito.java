package es.unican.is2.Practica5;
import java.time.LocalDate;

public class Debito extends Tarjeta {
	
	private double saldoDiarioDisponible;

	public Debito(String numero, String titular, CuentaAhorro c) {
		super(numero, titular, c);
	}
	//WMC = 1
	
	@Override
	public void retirar(double cantidad) throws saldoInsuficienteException, datoErroneoException {
		if (saldoDiarioDisponible<cantidad) { 	//WMC = 1	CCOG=1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.mCuentaAsociada.retirar("Retirada en cajero automático", cantidad);
		saldoDiarioDisponible-=cantidad;
	}
	//WMC = 1
	//CCOG=1
	
	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException {
		if (saldoDiarioDisponible<x) {	//WMC = 1 	CCOG=1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.mCuentaAsociada.retirar("Compra en : " + datos, x);
		saldoDiarioDisponible-=x;
	}
	//WMC = 1
	//CCOG=1
	
	public LocalDate getCaducidadDebito() {
		return this.mCuentaAsociada.getCaducidadDebito();
	}
	//WMC = 1
	
	/**
	 * Método invocado automáticamente a las 00:00 de cada día
	 */
	public void restableceSaldo() {
		saldoDiarioDisponible = mCuentaAsociada.getLimiteDebito();
	}
	//WMC = 1
	
	public CuentaAhorro getCuentaAsociada() {
		return mCuentaAsociada;
	}
	//WMC = 1

}
//WMC = 6
//WMCn = 1
//CBO = 1 CuentaAhorro
//DIT= 1
//NOC = 0
//CCOG= 2