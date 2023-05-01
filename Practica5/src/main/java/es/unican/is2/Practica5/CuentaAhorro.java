package es.unican.is2.Practica5;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CuentaAhorro extends Cuenta {

	private List<Movimiento> mMovimientos;
	private LocalDate mFechaDeCaducidadTarjetaDebito;
	private LocalDate mFechaDeCaducidadTarjetaCredito;
	private double limiteDebito;

	public CuentaAhorro(String numCuenta, LocalDate date, LocalDate date2) throws datoErroneoException {
		super(numCuenta);
		this.mFechaDeCaducidadTarjetaDebito = date;
		this.mFechaDeCaducidadTarjetaCredito = date2;
		mMovimientos = new LinkedList<Movimiento>();
		limiteDebito = 1000;
	}
	//WMC = 1

	public void ingresar(double x) throws datoErroneoException {
		if (x <= 0) //WMC = 1	//CCOG = 1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Ingreso en efectivo");
		m.setI(x);
		this.mMovimientos.add(m); //WMC = 1
	}
	//WMC = 2
	//CCOG = 1

	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {
		if (x <= 0) //WMC = 1	//CCOG = 1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		if (getSaldo() < x) //WMC = 1	//CCOG = 1
			throw new saldoInsuficienteException("Saldo insuficiente");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Retirada de efectivo");
		m.setI(-x);
		this.mMovimientos.add(m); //WMC = 1
	}
	//WMC = 3
	//CCOG = 2

	public void ingresar(String concepto, double x) throws datoErroneoException {
		if (x <= 0) //WMC = 1	//CCOG = 1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC(concepto);
		m.setI(x);
		this.mMovimientos.add(m); //WMC = 1
	}
	//WMC = 2
	//CCOG = 1

	public void retirar(String concepto, double x) throws saldoInsuficienteException, datoErroneoException {
		if (getSaldo() < x) //WMC = 1 	//CCOG = 1
			throw new saldoInsuficienteException("Saldo insuficiente");
		if (x <= 0) //WMC = 1	//CCOG = 1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC(concepto);
		m.setI(-x);
		this.mMovimientos.add(m); //WMC = 1
	}
	//WMC = 3´
	//CCOG = 2

	public double getSaldo() {
		double r = 0.0;
		for (int i = 0; i < this.mMovimientos.size(); i++) { //WMC = 1	//CCOG = 1
			Movimiento m = (Movimiento) mMovimientos.get(i);
			r += m.getI();
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
		return this.mFechaDeCaducidadTarjetaDebito;
	}
	//WMC = 1

	public LocalDate getCaducidadCredito() {
		return this.mFechaDeCaducidadTarjetaCredito;
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