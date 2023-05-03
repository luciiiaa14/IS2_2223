package es.unican.is2.Practica5;
public abstract class Tarjeta {
	
	protected String mNumero, mTitular;		
	protected CuentaAhorro mCuentaAsociada;

	public Tarjeta(String numero, String titular, CuentaAhorro c) {
		mNumero = numero;
		mTitular = titular;
		mCuentaAsociada = c;
	}
	//WMC= 1

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. 
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void retirar(double x) throws SaldoInsuficienteException, DatoErroneoException;
	//WMC= 0

	/**
	 * Pago en establecimiento con la tarjeta
	 * @param datos Concepto del pago
	 * @param x Cantidada a pagar
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void pagoEnEstablecimiento(String datos, double x)
			throws SaldoInsuficienteException, DatoErroneoException;
	//WMC= 0
	
}
//WMC= 1
//WMCn=1
//CBO=2
//DIT= 0
//NOC= 2
//CCOG=0