import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.SegurosCommon.daos.*;

public class SeguroTest {
	private Cliente c = new Cliente();
	private Seguro s;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		s = new Seguro(Cobertura.TODORIESGO, 100, LocalDate.of(2022, 02, 12), c);
	}
	

	//CASOS VALIDOS COBERTURA
	@Test 
	public void CoberturaTest() {
		Cobertura cober;
			
			s = new Seguro(Cobertura.TODORIESGO, 130, LocalDate.of(2023, 02, 12), c);
			cober = s.getCobertura();
			assertEquals(Cobertura.TODORIESGO, cober);
			assertThrows(DatoIncorrectoException.class, () -> s.getCobertura());
			
			
			s = new Seguro(Cobertura.TERCEROS, 110, LocalDate.of(2022, 02, 12), c);
			cober = s.getCobertura();
			assertEquals(Cobertura.TERCEROS, cober);
			assertThrows(DatoIncorrectoException.class, () -> s.getCobertura());

		
			s = new Seguro(Cobertura.TERCEROSLUNAS, 92, LocalDate.of(2022, 02, 12), c);
			cober = s.getCobertura();
			assertEquals(Cobertura.TERCEROSLUNAS, cober);
			assertThrows(DatoIncorrectoException.class, () -> s.getCobertura());

	}

	//CASOS VALIDOS POTENCIA
	@Test
	public void potenciaTest() {

			s = new Seguro(Cobertura.TERCEROS, 110, LocalDate.of(2022, 02, 12), c);
			int pot = s.getPotencia();
			assertTrue(pot > 90);
			assertThrows(DatoIncorrectoException.class, () -> s.getPotencia());
	}

	//CASOS VALIDOS PRECIO
	@Test
	public void precioTest() {

			s = new Seguro(Cobertura.TERCEROS, 110, LocalDate.of(2022, 02, 12), c);
			double prec = s.precio();
			assertTrue(prec > 0);
			assertThrows(DatoIncorrectoException.class, () -> s.precio());
	}
	
	//CASOS VALIDOS FECHA CONTRATACION
	
	public void fechaTest() {
		
			s = new Seguro(Cobertura.TERCEROS, 110, LocalDate.of(2022, 02, 12), c);
			LocalDate fecha = s.getFechaContratacion();
			assertTrue(fecha.isBefore(LocalDate.now()));
			assertTrue(fecha.isEqual(LocalDate.now()));
			assertThrows(DatoIncorrectoException.class, () -> s.getFechaContratacion());
		
		
			s = new Seguro(Cobertura.TERCEROS, 110, LocalDate.of(2022, 02, 12), c);
			LocalDate fecha2 = s.getFechaContratacion();
			assertTrue(fecha2.getYear() - LocalDate.now().getYear() < 1 );	
			assertThrows(DatoIncorrectoException.class, () -> s.getFechaContratacion());
		
		
			s = new Seguro(Cobertura.TERCEROS, 110, LocalDate.of(2022, 02, 12), c);
			LocalDate fecha3 = s.getFechaContratacion();
			assertTrue(fecha3.getYear() - LocalDate.now().getYear() > 1 );	
			assertTrue(fecha3.getYear() - LocalDate.now().getYear() < 2 );
			assertThrows(DatoIncorrectoException.class, () -> s.getFechaContratacion());
		
	}
	
	//CASOS VALIDOS CLIENTE
	@Test
	public void clienteTest() {
			s = new Seguro(Cobertura.TERCEROS, 110, LocalDate.of(2022, 02, 12), c);
			boolean minus = c.getMinusvalia();
			assertEquals(true, minus);
			assertThrows(DatoIncorrectoException.class, () -> c.getMinusvalia());
		
			s = new Seguro(Cobertura.TERCEROS, 110, LocalDate.of(2022, 02, 12), c);
			boolean minus2 = c.getMinusvalia();
			assertEquals(false, minus2);
			assertThrows(DatoIncorrectoException.class, () -> c.getMinusvalia());
		
	}

}
