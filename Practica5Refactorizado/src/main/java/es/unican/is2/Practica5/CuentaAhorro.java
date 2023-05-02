package es.unican.is2.Practica5;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CuentaAhorro extends Cuenta {
	private static final int LIMITE_MIL = 1000;
	private static final double SALDO_INICIAL = 0.00;
	private List<Movimiento> mMovimientos;
	private LocalDate fechaDeCaducidadTarjetaDebito;
	private LocalDate fechaDeCaducidadTarjetaCredito;
	private double limiteDebito;

	public CuentaAhorro(String numCuenta, LocalDate date, LocalDate date2) throws DatoErroneoException {
		super(numCuenta);
		this.fechaDeCaducidadTarjetaDebito = date;
		this.fechaDeCaducidadTarjetaCredito = date2;
		mMovimientos = new LinkedList<Movimiento>();
		limiteDebito = LIMITE_MIL;
	}
	//WMC = 1

	public void ingresar(double x) throws DatoErroneoException {
		compruebaCantidadNegativa(x);
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setFecha(now);
		m.setConcepto("Ingreso en efectivo");
		m.setImporte(x);
		this.mMovimientos.add(m); //WMC = 1
	}
	//WMC = 2
	//CCOG = 1

	public void retirar(double x) throws SaldoInsuficienteException, DatoErroneoException {
		compruebaCantidadNegativa(x);
		compruebaSaldo(x);
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setFecha(now);
		m.setConcepto("Retirada de efectivo");
		m.setImporte(-x);
		this.mMovimientos.add(m); //WMC = 1
	}
	//WMC = 3
	//CCOG = 2

	private void compruebaCantidadNegativa(double x) {
		if (x <= 0) //WMC = 1	//CCOG = 1
			throw new DatoErroneoException("No se puede retirar una cantidad negativa");
	}
	

	public void ingresar(String concepto, double x) throws DatoErroneoException {
		compruebaCantidadNegativa(x);
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setFecha(now);
		m.setConcepto(concepto);
		m.setImporte(x);
		this.mMovimientos.add(m); //WMC = 1
	}
	//WMC = 2
	//CCOG = 1

	public void retirar(String concepto, double x) throws SaldoInsuficienteException, DatoErroneoException {
		compruebaSaldo(x);
		compruebaCantidadNegativa(x);
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setFecha(now);
		m.setConcepto(concepto);
		m.setImporte(-x);
		this.mMovimientos.add(m); //WMC = 1
	}
	private void compruebaSaldo(double x) {
		if (getSaldo() < x) //WMC = 1 	//CCOG = 1
			throw new SaldoInsuficienteException("Saldo insuficiente");
	}
	//WMC = 3´
	//CCOG = 2

	public double getSaldo() {
		double r = SALDO_INICIAL;
		for (int i = 0; i < this.mMovimientos.size(); i++) { //WMC = 1	//CCOG = 1
			Movimiento m = (Movimiento) mMovimientos.get(i);
			r += m.getImporte();
		}
		return r;
	}
	//WMC = 1
	//CCOG = 1
	public void addMovimiento(Movimiento m) {
		mMovimientos.add(m);
	}
	//WMC = 2

	public List<Movimiento> getMovimientos() {
		return mMovimientos;
	}
	//WMC = 1

	public LocalDate getCaducidadDebito() {
		return this.fechaDeCaducidadTarjetaDebito;
	}
	//WMC = 1

	public LocalDate getCaducidadCredito() {
		return this.fechaDeCaducidadTarjetaCredito;
	}
	//WMC = 1

	public double getLimiteDebito() {
		return limiteDebito;
	}
	//WMC = 1

}
//WMC= 18
//WMCn= 1.63
//DIT =1, es un hijo de Cuenta
//NOC= 0, no tiene hijos
//CBO= 1, Movimiento
//CCOG= 7