package es.unican.is2.SegurosCommon.daos;

@SuppressWarnings("serial")
public class DatoIncorrectoException extends Exception {
	public DatoIncorrectoException(String mensaje) {
		super(mensaje);
	}
}
