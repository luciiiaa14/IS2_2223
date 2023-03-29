import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import es.unican.is2.SegurosCommon.daos.*;

public class SeguroTest {
	private Cliente c = new Cliente();
	
	


	//CASOS VALIDOS COBERTURA
	public void CoberturaTest() {

		try {
			Seguro s = new Seguro(Cobertura.TODORIESGO, 130, LocalDate.of(2023, 02, 12), c);
			Cobertura cober = s.getCobertura();
			assertEquals(Cobertura.TODORIESGO, cober);

		}catch(DatoIncorrectoException e) {
			fail("Caso1: No se deberia lanzar la excepcion\n");
		}

		try {
			Seguro s = new Seguro(Cobertura.TERCEROS, 110, LocalDate.of(2022, 02, 12), c);
			Cobertura cober = s.getCobertura();
			assertEquals(Cobertura.TODORIESGO, cober);

		}catch(DatoIncorrectoException e) {
			fail("Caso2: No se deberia lanzar la excepcion\n");
		}

		try {
			Seguro s = new Seguro(Cobertura.TERCEROSLUNAS, 92, LocalDate.of(2022, 02, 12), c);
			Cobertura cober = s.getCobertura();
			assertEquals(Cobertura.TERCEROSLUNAS, cober);

		}catch(DatoIncorrectoException e) {
			fail("Caso3: No se deberia lanzar la excepcion\n");
		}
	}

	//CASOS VALIDOS POTENCIA
	public void potenciaTest() {

		try {
			Seguro s = new Seguro(Cobertura.TERCEROS, 110, LocalDate.of(2022, 02, 12), c);
			int pot = s.getPotencia();
			assertTrue(pot > 90);

		}catch(DatoIncorrectoException e) {
			fail("Caso1: No se deberia lanzar la excepcion\n");
		}
	}

	//CASOS VALIDOS PRECIO
	public void precioTest() {

		try {
			Seguro s = new Seguro(Cobertura.TERCEROS, 110, LocalDate.of(2022, 02, 12), c);
			double prec = s.precio();
			assertTrue(prec > 0);

		}catch(DatoIncorrectoException e) {
			fail("Caso1: No se deberia lanzar la excepcion\n");
		}
	}
	
	//CASOS VALIDOS FECHA CONTRATACION
	
	public void fechaTest() {

		try {
			Seguro s = new Seguro(Cobertura.TERCEROS, 110, LocalDate.of(2022, 02, 12), c);
			LocalDate fecha = s.getFechaContratacion();
			assertTrue(fecha.isBefore(LocalDate.now()));
			assertTrue(fecha.isEqual(LocalDate.now()));

		}catch(DatoIncorrectoException e) {
			fail("Caso1: No se deberia lanzar la excepcion\n");
		}
		
		try {
			Seguro s = new Seguro(Cobertura.TERCEROS, 110, LocalDate.of(2022, 02, 12), c);
			LocalDate fecha = s.getFechaContratacion();
			assertTrue(fecha.getYear() - LocalDate.now().getYear() < 1 );	

		}catch(DatoIncorrectoException e) {
			fail("Caso1: No se deberia lanzar la excepcion\n");
		}
		
		try {
			Seguro s = new Seguro(Cobertura.TERCEROS, 110, LocalDate.of(2022, 02, 12), c);
			LocalDate fecha = s.getFechaContratacion();
			assertTrue(fecha.getYear() - LocalDate.now().getYear() > 1 );	
			assertTrue(fecha.getYear() - LocalDate.now().getYear() < 2 );

		}catch(DatoIncorrectoException e) {
			fail("Caso1: No se deberia lanzar la excepcion\n");
		}
		
	}
	
	//CASOS VALIDOS CLIENTE
	public void clienteTest() {
		
		try {
			Seguro s = new Seguro(Cobertura.TERCEROS, 110, LocalDate.of(2022, 02, 12), c);
			boolean minus = c.getMinusvalia();
			assertEquals(true, minus);
		} catch (DatoIncorrectoException e){
			fail("Caso1: No se deberia lanzar la excepcion\n");
			
		}
		
		try {
			Seguro s = new Seguro(Cobertura.TERCEROS, 110, LocalDate.of(2022, 02, 12), c);
			boolean minus = c.getMinusvalia();
			assertEquals(false, minus);
		} catch (DatoIncorrectoException e){
			fail("Caso1: No se deberia lanzar la excepcion\n");
			
		}
	}
	
	//CASOS NO VALIDOS
	
	


}
