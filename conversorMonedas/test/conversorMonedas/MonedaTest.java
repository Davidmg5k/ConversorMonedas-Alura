package conversorMonedas;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import mundo.Moneda;

class MonedaTest {
	//-------------------------
	// Atributos
	//-------------------------
	/**
	 * La clase en donde se haran las pruebas
	 */
	private Moneda moneda;

	//-------------------------
	// Metodos
	//-------------------------
	/**
	 * Crea una maneda
	 */
	@Test
	public void setup() {
		moneda=new Moneda("USD/COP", 4000.0);
	}
	
	/**
	 * verifica que se retorne el nombre de la moneda
	 */
	@Test
	public void testDarNombre() {
		setup();
		String nombre=moneda.darNombre();
		assertEquals("USD/COP",nombre);
	}
	
	/**
	 * verifica que se retorne el valor de la moneda
	 */
	@Test
	public void testDarValor() {
		setup();
		double valor=moneda.darValor();
		boolean igual=false;
		if (4000.0==valor) {
			igual=true;
		}
		assertTrue("El valor no es correcto", igual);
	}
	
	/**
	 * verifica que sea posible cambiar el nombre de una moneda
	 */
	@Test
	public void testNombre() {
		setup();
		moneda.nombre("COP/USD");
		assertEquals("COP/USD",moneda.darNombre());
	}
	
	/**
	 * verifica que sea posible cambiar el valor de una moneda
	 */
	@Test
	public void testValor() {
		setup();
		moneda.valor(3800.97);
		boolean igual=false;
		if (3800.97==moneda.darValor()) {
			igual=true;
		}
		assertTrue("El valor no es correcto", igual);
	}
}
