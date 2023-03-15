package es.unican.is2.SegurosBusiness.daos;

import es.unican.is2.SegurosCommon.daos.Cliente;
import es.unican.is2.SegurosCommon.daos.Seguro;
import es.unican.is2.SegurosCommon.interfaces.IClientesDAO;
import es.unican.is2.SegurosCommon.interfaces.IGestionSeguros;
import es.unican.is2.SegurosCommon.interfaces.IGestionSeguros.OperacionNoValida;
import es.unican.is2.SegurosCommon.interfaces.ISegurosDAO;


public class GestionSeguros implements IGestionSeguros {
	private ISegurosDAO seguros;
	private IClientesDAO clientes;
 	

	public Seguro nuevoSeguro(Seguro s,String dni) throws OperacionNoValida {
		Cliente c = clientes.cliente(dni);
		
		if (s.getMatricula().equals(seguros.seguro(s.getMatricula()))) {
			throw new OperacionNoValida();
		}
		c.getSeguros().add(s);
		return s;
	}

	public Seguro bajaSeguro(String matricula, String dni)throws OperacionNoValida {
		Cliente c = new Cliente();
		Seguro s = new Seguro();
		
		c.setDni(dni);
		s.setMatricula(matricula);
		if (c.getSeguros().contains(s) ) {
			throw new OperacionNoValida();
		}
		c.getSeguros().remove(s);
		return s;
		
	}
	
	/**
	 * Añade un nuevo cliente
	 * @param c Cliente que desea añadir
	 * @return El cliente añadido
	 * 		   null si no se añade porque ya existe
	 */
	public Cliente nuevoCliente(Cliente c) {
		
		Cliente cli = new Cliente();
		
		cli.setDni(c.getDni());
		cli.setMinusvalia(c.getMinusvalia());
		cli.setNombre(c.getNombre());
		return cli;
		
	}
	
	/**
	 * Elimina el cliente cuyo dni se pasa como parámetro
	 * @param dni DNI del cliente que se quiere eliminar
	 * @return El cliente eliminado
	 * 		   null si no se elimina porque no se encuentra 
	 * @throws OperacionNoValida si el cliente existe 
	 *         pero tiene seguros a su nombre
	 */
	public Cliente bajaCliente(String dni) throws OperacionNoValida {
		Cliente c = new Cliente();
		c.setDni(dni);
		
		
		
		
	}
}
