package es.unican.is2.SegurosMain.main;

import es.unican.is2.SegurosBusiness.daos.GestionSeguros;
import es.unican.is2.SegurosCommon.interfaces.IClientesDAO;
import es.unican.is2.SegurosCommon.interfaces.ISegurosDAO;
import es.unican.is2.SegurosGUI.daos.VistaAgente;

public class Runner {

	public static void main(String[] args) {
		IClientesDAO daoContribuyentes = new ClientesDAO();
		ISegurosDAO daoVehiculos = new SegurosDAO();
		GestionSeguros negocio = new GestionSeguros(daoContribuyentes, daoVehiculos);
		VistaAgente vista = new VistaAgente(negocio, negocio, negocio);
		vista.setVisible(true);

	}

}
