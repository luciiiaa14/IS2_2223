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
	public void retirar(double x) throws SaldoInsuficienteException, DatoErroneoException {
		x += x * COMISION; 
		compruebaCantidad(x);
		compruebaGastos(x);

		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setFecha(now);
		m.setConcepto("Retirada en cajero automático");
		// Añadimos una comisión de un 5%
		m.setImporte(-x);
		
		
			mMovimientosMensuales.add(m); //WMC = 1
		
	}
	private void compruebaGastos(double x) {
		if (getGastosAcumulados()+x > mCredito) //WMC = 1	//CCOG = 1
			throw new SaldoInsuficienteException("Crédito insuficiente");
	}
		//WMC = 3
		//CBO = 3 
		//CCOG = 1
	private void compruebaCantidad(double x) {
		if (x<0) //WMC = 1	//CCOG = 1
			throw new DatoErroneoException("No se puede retirar una cantidad negativa");
	}
	
	

	@Override
	
	public void pagoEnEstablecimiento(String datos, double x) throws SaldoInsuficienteException, DatoErroneoException {
		compruebaCantidad(x);
		compruebaGastos(x);
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setFecha(now);
		m.setConcepto("Compra a crédito en: " + datos);
		m.setImporte(-x);
		mMovimientosMensuales.add(m); //WMC = 1 add
	}
	//WMC = 3
	//CBO = 3
	//CCOG = 2
	
    public double getGastosAcumulados() {
		double r = PRECIO_INICIAL;
		r = calculaMovimientos(r);
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
		liq.setFecha(now);
		liq.setConcepto("Liquidación de operaciones tarjeta crédito");
		double r = 0.0;
		r = calculaMovimientos(r);
		liq.setImporte(r);
		//si hay mmovimiento
		if (r != 0) //WMC = 1	CCOG = 1
			mCuentaAsociada.addMovimiento(liq);
		
		mhistoricoMovimientos.addAll(mMovimientosMensuales); //WMC = 1 add
		mMovimientosMensuales.clear();
	}
	
	private double calculaMovimientos(double r) {
		for (int i = 0; i < this.mMovimientosMensuales.size(); i++) { //WMC = 1   CCOG = 1
			Movimiento m = (Movimiento) mMovimientosMensuales.get(i);
			r += m.getImporte();
		}
		return r;
	}
	

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