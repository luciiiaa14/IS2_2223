package es.unican.is2.Practica5;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


public class Credito extends Tarjeta {
	private static final double COMISION = 0.05;
	private static final double PRECIO_INICIAL = 0.00;
	
	private double mCredito;
	private List<Movimiento> mMovimientosMensuales;
	private List<Movimiento> mhistoricoMovimientos;
	
	
	public Credito(String numero, String titular, CuentaAhorro c, double credito) {
		super(numero, titular, c);
		mCredito = credito;
		mMovimientosMensuales = new LinkedList<Movimiento>();
		mhistoricoMovimientos = new LinkedList<Movimiento>();
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
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {
		x += x * COMISION; 
		if (x<0) //WMC = 1	//CCOG = 1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		if (getGastosAcumulados()+x > mCredito) //WMC = 1	//CCOG = 1
			throw new saldoInsuficienteException("Crédito insuficiente");

		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Retirada en cajero automático");
		// Añadimos una comisión de un 5%
		m.setI(-x);
		
		
			mMovimientosMensuales.add(m); //WMC = 1
		
	}
	//WMC = 3
	//CBO = 3 
	//CCOG = 1
	

	@Override
	
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException {
		if (x<0) //WMC = 1	//CCOG = 1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		if (getGastosAcumulados() + x > mCredito) //WMC = 1	CCOG = 1
			throw new saldoInsuficienteException("Saldo insuficiente");
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Compra a crédito en: " + datos);
		m.setI(-x);
		mMovimientosMensuales.add(m); //WMC = 1 add
	}
	//WMC = 3
	//CBO = 3
	//CCOG = 2
	
    public double getGastosAcumulados() {
		double r = PRECIO_INICIAL;
		for (int i = 0; i < this.mMovimientosMensuales.size(); i++) { //WMC = 1  CCOG = 1
			Movimiento m = (Movimiento) mMovimientosMensuales.get(i);
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
		double r = PRECIO_INICIAL;
		for (int i = 0; i < this.mMovimientosMensuales.size(); i++) { //WMC = 1   CCOG = 1
			Movimiento m = (Movimiento) mMovimientosMensuales.get(i);
			r += m.getI();
		}
		liq.setI(r);
	
		
		mCuentaAsociada.addMovimiento(liq);
		
		mhistoricoMovimientos.addAll(mMovimientosMensuales); //WMC = 1 add
		mMovimientosMensuales.clear();
	}
	//WMC = 1
	//CBO = 2, Movimiento y mcuentaAsociada
	//CCOG = 1

	public List<Movimiento> getMovimientosUltimoMes() {
		return mMovimientosMensuales;
	}
	//WMC = 1
	//CBO = 1
	
	public Cuenta getCuentaAsociada() {
		return mCuentaAsociada;
	}
	//WMC = 1
	//CBO = 1
	
	public List<Movimiento> getMovimientos() {
		return mhistoricoMovimientos;
	}
	//WMC = 1
	//CBO = 1
	
//WMC = 13
//WMCn = 13/9 = 1,44
//CBO = 3, Moviemiento, Tarjeta, CuentaAhorro
//DIT = 0
//NOC= 0, no tiene hijos
//CCOG =  5

}