package es.unican.is2.Practica5;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


public class Credito extends Tarjeta {
	
	private double mCredito;
	private List<Movimiento> movimientosMensuales;
	private List<Movimiento> historicoMovimientos;
	
	
	public Credito(String numero, String titular, CuentaAhorro c, double credito) {
		super(numero, titular, c);
		mCredito = credito;
		movimientosMensuales = new LinkedList<Movimiento>();
		historicoMovimientos = new LinkedList<Movimiento>();
	}
	//WMC = 1
	//CBO = 1 Movimiento

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. Se aplica una comisión del 5%.
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	@Override
	public void retirar(double cantidad) throws saldoInsuficienteException, datoErroneoException {
		if (cantidad<0) //WMC = 1	//CCOG = 1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Retirada en cajero automático");
		cantidad += cantidad * 0.05; // Añadimos una comisión de un 5%
		m.setI(-cantidad);
		
		if (getGastosAcumulados()+cantidad > mCredito) //WMC = 1	//CCOG = 1
			throw new saldoInsuficienteException("Crédito insuficiente");
		else { //WMC = 1	//CCOG = 1
			movimientosMensuales.add(m); //WMC = 1
		}
	}
	//WMC = 4
	//CBO = 3 
	//CCOG = 2
	

	@Override
	public void pagoEnEstablecimiento(String datos, double cantidad) throws saldoInsuficienteException, datoErroneoException {
		if (cantidad<0) //WMC = 1	//CCOG = 1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		if (getGastosAcumulados() + cantidad > mCredito) //WMC = 1	CCOG = 1
			throw new saldoInsuficienteException("Saldo insuficiente");
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Compra a crédito en: " + datos);
		m.setI(-cantidad);
		movimientosMensuales.add(m); //WMC = 1 add
	}
	//WMC = 3
	//CBO = 3
	//CCOG = 2
	
    public double getGastosAcumulados() {
		double r = 0.0;
		for (int i = 0; i < this.movimientosMensuales.size(); i++) { //WMC = 1  CCOG = 1
			Movimiento m = (Movimiento) movimientosMensuales.get(i);
			r += m.getI();
		}
		return -r;
	}
    //WMC = 1
    //CBO = 1
    //CCOG = 1
	
	
	public LocalDate getCaducidadCredito() {
		return this.mCuentaAsociada.getCaducidadCredito();
	}
	//WMC = 1
	//CBO = 1

	/**
	 * Método que se invoca automáticamente el día 1 de cada mes
	 */
	public void liquidar() {
		Movimiento liq = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		liq.setF(now);
		liq.setC("Liquidación de operaciones tarjeta crédito");
		double r = 0.0;
		for (int i = 0; i < this.movimientosMensuales.size(); i++) { //WMC = 1   CCOG = 1
			Movimiento m = (Movimiento) movimientosMensuales.get(i);
			r += m.getI();
		}
		liq.setI(r);
		//si hay mmovimiento
		if (r != 0) //WMC = 1	CCOG = 1
			mCuentaAsociada.addMovimiento(liq);
		
		historicoMovimientos.addAll(movimientosMensuales); //WMC = 1 add
		movimientosMensuales.clear();
	}
	//WMC = 3
	//CBO = 2, Movimiento y mcuentaAsociada
	//CCOG = 2

	public List<Movimiento> getMovimientosUltimoMes() {
		return movimientosMensuales;
	}
	//WMC = 1
	//CBO = 1
	
	public Cuenta getCuentaAsociada() {
		return mCuentaAsociada;
	}
	//WMC = 1
	//CBO = 1
	
	public List<Movimiento> getMovimientos() {
		return historicoMovimientos;
	}
	//WMC = 1
	//CBO = 1
	
//WMC = 16
//WMCn = 16/9 = 1.77
//CBO = 3, Moviemiento, Tarjeta, CuentaAhorro
//DIT = 0
//NOC= 0, no tiene hijos
//CCOG =  7

}