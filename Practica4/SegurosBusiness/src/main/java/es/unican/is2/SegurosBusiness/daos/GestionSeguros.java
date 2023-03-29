package es.unican.is2.SegurosBusiness.daos;

import es.unican.is2.SegurosCommon.daos.Cliente;
import es.unican.is2.SegurosCommon.daos.Seguro;
import es.unican.is2.SegurosCommon.interfaces.IClientesDAO;
import es.unican.is2.SegurosCommon.interfaces.IGestionClientes;
import es.unican.is2.SegurosCommon.interfaces.IGestionSeguros;
import es.unican.is2.SegurosCommon.interfaces.IInfoSeguros;
import es.unican.is2.SegurosCommon.interfaces.ISegurosDAO;


public class GestionSeguros implements IGestionSeguros, IGestionClientes, IInfoSeguros{
	private ISegurosDAO seguros;
	private IClientesDAO clientes;
	
	public GestionSeguros(IClientesDAO clientes, ISegurosDAO seguros) {
		this.seguros = seguros;
		this.clientes = clientes;
		
	}
 	
	/**
	 * A�ade un nuevo seguro al cliente cuyo dni se pasa
	 * como par�metro.
	 * @param s Seguro que desea a�adir
	 * @param dni DNI del cliente
	 * @return El seguro a�adido
	 * 		   null si no se a�ade porque no se encuentra el cliente
	 * @throws OperacionNoValida si el seguro ya existe
	 */
	public Seguro nuevoSeguro(Seguro s,String dni) throws OperacionNoValida {
		
		if (s.equals(seguros.seguro(s.getMatricula()))) {
			throw new OperacionNoValida();
		}
		
		if (clientes.cliente(dni) ==  null) {
			throw new OperacionNoValida();	
		}
		seguros.creaSeguro(s);
		return s;
	}
	
	/**
	 * Elimina el seguro cuya matr�cula asociada se pasa como par�metro y 
	 * que pertenece al cliente cuyo dni se pasa como par�metro
	 * @param matr�cula Identificador del seguro a eliminar
	 * @param dni DNI del propietario del veh�culo
 	 * @return El seguro eliminado
 	 *         null si el seguro o el cliente no existen
 	 * @throws OperacionNoValida si el seguro no pertenece al dni indicado
	 */

	public Seguro bajaSeguro(String matricula, String dni)throws OperacionNoValida {
		if (clientes.cliente(dni) ==  null) {
			throw new OperacionNoValida();	
		}
		if (seguros.seguro(matricula) != null) {
			throw new OperacionNoValida();	
		}
		return seguros.eliminaSeguro(matricula);
	}
	
	/**
	 * A�ade un nuevo cliente
	 * @param c Cliente que desea a�adir
	 * @return El cliente a�adido
	 * 		   null si no se a�ade porque ya existe
	 */
	public Cliente nuevoCliente(Cliente c) {
		if (clientes.cliente(c.getDni()) !=  null) {
			throw new OperacionNoValida();	
		}
		return clientes.creaCliente(c);	
	}
	
	/**
	 * Elimina el cliente cuyo dni se pasa como par�metro
	 * @param dni DNI del cliente que se quiere eliminar
	 * @return El cliente eliminado
	 * 		   null si no se elimina porque no se encuentra 
	 * @throws OperacionNoValida si el cliente existe 
	 *         pero tiene seguros a su nombre
	 */
	public Cliente bajaCliente(String dni) throws OperacionNoValida {
		if (clientes.cliente(dni) ==  null) {
			throw new OperacionNoValida();	
		}
		if(!(clientes.cliente(dni).getSeguros().isEmpty())) {
			throw new OperacionNoValida();
		}
		return clientes.eliminaCliente(dni);
		
		
	}
	
	/**
	 * Retorna el cliente cuyo dni se pasa como par�metro
	 * @param dni DNI del cliente buscado
	 * @return El cliente cuyo dni coincide con el par�metro
	 * 		   null en caso de que no exista
	 */
	public Cliente cliente(String dni) {
		if (clientes.cliente(dni) ==  null) {
			throw new OperacionNoValida();	
		}
		return clientes.cliente(dni);
		
	}
	
	/**
	 * Retorna el seguro cuya matr�cula asociada se pasa como par�metro
	 * @param matricula Identificador del seguro
	 * @return El seguro indicado
	 * 	       null si no existe
	 */
	public Seguro seguro(String matricula) {
		if (seguros.seguro(matricula) != null) {
			throw new OperacionNoValida();	
		}
		return seguros.seguro(matricula);
		
	}
	
}
