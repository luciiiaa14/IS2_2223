package es.unican.is2.Practica5;
@SuppressWarnings("serial")
public class SaldoInsuficienteException extends RuntimeException {

	public SaldoInsuficienteException (String mensaje) {
		super(mensaje);
	}
}
