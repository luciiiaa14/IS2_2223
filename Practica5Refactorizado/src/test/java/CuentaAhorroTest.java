import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.Practica5.CuentaAhorro;
import es.unican.is2.Practica5.DatoErroneoException;
import es.unican.is2.Practica5.Movimiento;
import es.unican.is2.Practica5.SaldoInsuficienteException;

import java.time.LocalDate;

public class CuentaAhorroTest {
	private CuentaAhorro sut;
	private static Movimiento m1, m2, m3;
	
	@BeforeAll
	public static void inicializaAuxiliares() {
		m1 = new Movimiento();
		m1.setImporte(100);
		m2 = new Movimiento();
		m2.setImporte(200);
		m3 = new Movimiento();
		m3.setImporte(1500);
	}

	@BeforeEach
	public void inicializa() {
		sut = new CuentaAhorro("794311", LocalDate.now().plusYears(3), LocalDate.now().plusYears(4));
	}

	@Test
	public void testConstructor() {
		assertEquals(sut.getCaducidadDebito(), LocalDate.now().plusYears(3));
		assertEquals(sut.getCaducidadCredito(),LocalDate.now().plusYears(4));
		assertEquals(sut.getLimiteDebito(), 1000);
		assertEquals(sut.getMovimientos().size(), 0);
		assertEquals(sut.getNumCuenta(), "794311");
	}
	
	@Test
	public void testGetSaldoYAddMovimiento() {
		assertTrue(sut.getSaldo()==0);	

		sut.addMovimiento(m1);
		assertTrue(sut.getSaldo() == 100);
		assertTrue(sut.getMovimientos().size()==1);
		
		sut.addMovimiento(m2);
		sut.addMovimiento(m3);
		assertTrue(sut.getSaldo()==1800);
		assertTrue(sut.getMovimientos().size()==3);
	}
	
	@Test
	public void testRetirarSinConcepto() {
		
		try {
			sut.retirar(-10);
			fail("Debería lanzar DatoErroneoException");
		} catch (DatoErroneoException e) {
		} catch (SaldoInsuficienteException e) {
			fail("Debería lanzar DatoErroneoException");
		}
		
		sut.addMovimiento(m1);
		
		try {
			sut.retirar(50);
			assertTrue(sut.getSaldo()==50);
			assertTrue(sut.getMovimientos().size()==2);
			assertEquals(sut.getMovimientos().get(1).getConcepto(), "Retirada de efectivo");
		} catch (DatoErroneoException e) {
			fail("No debería lanzar DatoErroneoException");
		} catch (SaldoInsuficienteException e) {
			fail("No debería lanzar SaldoInsuficienteException");
		}
		
		
		try {
			sut.retirar(100);
			fail("Debería lanzar SaldoInsuficienteException");
		} catch (DatoErroneoException e) {
			fail("Debería lanzar SaldoInsuficienteException");
		} catch (SaldoInsuficienteException e) { }
	
	}
	
	@Test
	public void testIngresarSinConcepto () {
	
		try {
			sut.ingresar(-1);
			fail("Debería lanzar DatoErroneoException");
		} catch (DatoErroneoException e) {
		}

		try {
			sut.ingresar(0.01);
			assertTrue(sut.getSaldo()==0.01);
			assertTrue(sut.getMovimientos().size()==1);
			assertEquals(sut.getMovimientos().get(0).getConcepto(),"Ingreso en efectivo");
			
			sut.ingresar(100);
			assertTrue(sut.getSaldo()==100.01);
			assertTrue(sut.getMovimientos().size()==2);
			
		} catch (DatoErroneoException e) {
			fail("No debería lanzar la excepción");
		}
		
	}
	
	
	@Test
	public void testIngresarConConcepto () {
	
		// Test ingresar negativo
		try {
			sut.ingresar("Ingreso", -1);
			fail("Debería lanzar DatoErroneoException");
		} catch (DatoErroneoException e) {
		}

		// Test ingresar el limite
		try {
			sut.ingresar("Ingreso1", 0.01);
			assertTrue(sut.getSaldo()==0.01);
			assertTrue(sut.getMovimientos().size()==1);
			assertEquals(sut.getMovimientos().get(0).getConcepto(), "Ingreso1");
			
			sut.ingresar("Ingreso2", 100);
			assertTrue(sut.getSaldo()==100.01);
			assertTrue(sut.getMovimientos().size()==2);
			assertEquals(sut.getMovimientos().get(1).getConcepto(), "Ingreso2");
			
		} catch (DatoErroneoException e) {
			fail("No debería lanzar la excepción");
		}
		
	}
	
	@Test
	public void testRetirarConConcepto() {
		
		try {
			sut.retirar("Retirada", -10);
			fail("Debería lanzar DatoErroneoException");
		} catch (DatoErroneoException e) {
		} catch (SaldoInsuficienteException e) {
			fail("Debería lanzar DatoErroneoException");
		}
		
		sut.addMovimiento(m1);
		
		try {
			sut.retirar("Retirada1", 50);
			assertTrue(sut.getSaldo()==50);
			assertTrue(sut.getMovimientos().size()==2);
			assertEquals(sut.getMovimientos().get(1).getConcepto(),"Retirada1");
		} catch (DatoErroneoException e) {
			fail("No debería lanzar DatoErroneoException");
		} catch (SaldoInsuficienteException e) {
			fail("No debería lanzar SaldoInsuficienteException");
		}
		
		
		try {
			sut.retirar("Retirada2", 100);
			fail("Debería lanzar SaldoInsuficienteException");
		} catch (DatoErroneoException e) {
			fail("Debería lanzar SaldoInsuficienteException");
		} catch (SaldoInsuficienteException e) {
			
		}
	
	}

	
}
