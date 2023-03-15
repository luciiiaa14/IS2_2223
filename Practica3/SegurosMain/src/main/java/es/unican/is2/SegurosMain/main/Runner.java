package es.unican.is2.SegurosMain.main;




public class Runner {

	public static void main(String[] args) {
		IClientesDAO daoContribuyentes = new ClientesDAO();
		ISegurosDAO daoVehiculos = new SegurosDAO();
		GestionSeguros negocio = new GestionSeguros(daoContribuyentes, daoVehiculos);
		VistaAgente vista = new VistaAgente(negocio, negocio, negocio);
		vista.setVisible(true);

	}

}
