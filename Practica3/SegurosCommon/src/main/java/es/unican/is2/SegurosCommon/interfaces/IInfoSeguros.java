package es.unican.is2.SegurosCommon.interfaces;

import es.unican.is2.SegurosCommon.daos.Cliente;
import es.unican.is2.SegurosCommon.daos.Seguro;

/**
 * Interfaz con métodos de consulta de información
 * de la empresa de seguros
 */
public interface IInfoSeguros {
	
	/**
	 * Retorna el cliente cuyo dni se pasa como parámetro
	 * @param dni DNI del cliente buscado
	 * @return El cliente cuyo dni coincide con el parámetro
	 * 		   null en caso de que no exista
	 */
	public Cliente cliente(String dni); 
	
	/**
	 * Retorna el seguro cuya matrícula asociada se pasa como parámetro
	 * @param matricula Identificador del seguro
	 * @return El seguro indicado
	 * 	       null si no existe
	 */
	public Seguro seguro(String matricula); 	
	

}
