package conversorMonedas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import mundo.Conversor;
import mundo.ExcepcionMonedas;
import mundo.Moneda;


class ConversorTest {
	//-------------------------
	// Atributos
	//-------------------------
	/**
	 * la clase en donde se haran las pruebas
	 */
	private Conversor conversor;
	
	//-------------------------
	// Metodos
	//-------------------------
	/**
	 * Construye un nuevo conversor y verifica los valores por defecto
	 */
	@Test
	public void setup() {
		conversor=new Conversor();		
		ArrayList<Moneda> resultado = conversor.darMonedas();		
		ArrayList<Moneda> resultadoEsperado = new ArrayList<>();
		resultadoEsperado.add(new Moneda("USD/COP", 3976.47));
	    resultadoEsperado.add(new Moneda("COP/USD", 0.00025));
	    resultadoEsperado.add(new Moneda("USD/EUR", 0.90));
	    resultadoEsperado.add(new Moneda("EUR/USD", 1.11));
	    resultadoEsperado.add(new Moneda("USD/BRL", 4.80));
	    resultadoEsperado.add(new Moneda("BRL/USD", 0.21));
	    boolean sonIguales = false;
	    for (int i = 0; i < resultado.size(); i++) {
	        if (!resultado.get(i).equals(resultadoEsperado.get(i))) {
	            sonIguales = true;
	            break;
	        }
	    }
	    assertTrue( "La lista de monedas por defecto no es la correcta", sonIguales);		
	}
	
	/**
	 * Prueba la adicion de monedas <br>
	 * <b>Metodos a probar</b>
	 * <br>agregarMoneda, darMonedas<br>
	 * <b>Objetivo:</b>Probar que el metodo agregarMoneda() agregue correctamente una moneda a la lista monedas<br>
	 * <b>Resultados esperados</b>
	 * <br>1) Agregar una moneda nueva a la lista monedas con los datos dados por parametros<br>
	 */
	@Test
	public void testAgregarMoneda() {
		setup();		
		try {
			int monedas = conversor.darMonedas().size();			
			conversor.agregarMoneda("USD/CLP", 816.48);	
			ArrayList<Moneda> lasMonedas = conversor.darMonedas();			
			boolean encontrado=false;
			for (Moneda moneda:lasMonedas) {
				if (moneda.darNombre().equals("USD/CLP")) {
					encontrado=true;
				}
			}
			assertTrue( "No se encontro la nueva moneda", encontrado );			
			assertEquals( "El numero de monedas no es el correcto", monedas + 1, lasMonedas.size( ));
		}catch(ExcepcionMonedas e) {
			fail("No se deberia arrojar la ExcepcionMonedas");
		}catch (IllegalArgumentException e) {
			fail("No se deberia arrojar la IllegalArgumentException");
		}
	}
	
	/**
	 * Prueba la adicion de monedas <br>
	 * <b>Metodos a probar</b>
	 * <br>agregarMoneda<br>
	 * <b>Objetivo:</b>Probar que el metodo agregarMoneda() arroje corectamenente los tipos de excepciones<br>
	 * <b>Resultados esperados</b>
	 * <br>1) Al intentar agregar una moneda con los mismos valores base, se debe arrojar un error<br>
	 * <br>2) Al intentar agregar una moneda con el tipo de cambio en 0, se debe arrojar un error<br>
	 */
	@Test	
	public void testErrorAgregarMoneda(){
		setup();
		try {
			try {
				conversor.agregarMoneda("USD/COP", 0);
				fail("Deberia fallar porque el tipo de cambio de la moneda no puede ser 0.");
			}catch(IllegalArgumentException e) {
				ArrayList<Moneda> moneda =conversor.darMonedas();
				assertEquals( "El numero de monedas no debio haber cambiado (Cuando se evalua el tipo de cambio en 0).", 6, moneda.size( ));
			}
			conversor.agregarMoneda("USD/COP", 3976.47);
			fail("Deberia fallar porque la moneda ya existe.");
		}catch(ExcepcionMonedas e) {
			ArrayList<Moneda> moneda =conversor.darMonedas();
			assertEquals( "El numero de monedas no debio haber cambiado.", 6, moneda.size( ));
		}catch(IllegalArgumentException e) {
			fail("No se deberia arrojar esta excepcion.");
		}
	}
	
	/**
	 * Prueba la busqueda de una moneda por su nombre <br>
	 * <b>Metodos a probar</b>
	 * <br>buscarMoneda<br>
	 * <b>Objetivo:</b>Probar que el metodo buscarMoneda(pNombreMoneda) busque de forma correcta la moneda a travez de su nombre <br>
	 * <b>Resultados esperados</b>
	 * <br>1) Buscar la moneda a travez de su nombre y retornarla<br>
	 */
	@Test
	public void testBuscarMonedas() {
		setup();
		ArrayList<Moneda> moneda = conversor.darMonedas();
		Moneda laMoneda=moneda.get(0);
		Moneda monedaEncontrada=conversor.buscarMoneda(laMoneda.darNombre());
		boolean igual=false;
		if (monedaEncontrada.darNombre().equals(laMoneda.darNombre())) {
			igual=true;
		}
		assertTrue("El valor de la moneda encontrada no coincide con el valor esperado.",igual);		
	}
	
	/**
	 * Prueba la conversion de las monedas<br>
	 * <b>Metodos a probar</b>
	 * <br>convertirMonedas<br>
	 * <b>Objetivo:</b>Probar que el metodo convertirMonedas(pNombreMoneda, pValorConvertir) convierta de forma ideal los valores a convertir que recibe por parametro <br>
	 * <b>Resultados esperados</b>
	 * <br>1) Convertir el valor recibido por parametro<br>
	 */
	@Test
	public void testConvertirMonedas() {
		setup();
		//se captura la moneda USD/COP con el tipo de cambio 3976.47
		Moneda moneda=conversor.darMonedas().get(0);
		double valorConvertido=conversor.convertirMonedas(moneda.darNombre(), 5);
		//el resultado de valorConvertido deberia ser: 19882.35
		boolean igual=false;
		if (19882.35==valorConvertido) {
			igual=true;
		}
		assertTrue("El valor convertido no coincide con el valor esperado.",igual);
	}
	
	/**
	 * Prueba la conversion de las temperaturas<br>
	 * <b>Metodos a probar</b>
	 * <br>convertirTemperaturas<br>
	 * <b>Objetivo:</b>Probar que el metodo convertirTemperaturas(pValorConvertir,pNombreMoneda) convierta de forma ideal los valores a convertir que recibe por parametro <br>
	 * <b>Resultados esperados</b>
	 * <br>1) Convertir el valor recibido por parametro<br>
	 */
	@Test
	public void testConvertirTemperaturas() {
		setup();		
		try {
			double valorConvertido=conversor.convertirTemperaturas(5, Conversor.Temperaturas.CELCIUS_A_FAHRENHEIT);
			//valor esperado de valorConvertido es: 41.0
			boolean igual=false;
			if (41.0==valorConvertido) {
				igual=true;
			}
			assertTrue("El valor convertido no coincide con el valor esperado.",igual);
		}catch(IllegalArgumentException e) {
			fail("No se deberia arrojar esta excepcion.");
		}
	}
	
	/**
	 * Prueba la conversion de las temperaturas<br>
	 * <b>Metodos a probar</b>
	 * <br>convertirTemperaturas<br>
	 * <b>Objetivo:</b>Probar que el metodo convertirTemperaturas(pValorConvertir,pNombreMoneda) arroje corectamenente los tipos de excepciones<br>
	 * <b>Resultados esperados</b>
	 * <br>1) Al intentar hacer una conversion con el valor a convertir igual a 0, se debe arrojar un error<br>
	 */
	@Test
	public void testErrorConvertirTemperaturas() {
		setup();		
		try {
			conversor.convertirTemperaturas(0, Conversor.Temperaturas.CELCIUS_A_FAHRENHEIT);
			fail("Deberia fallar porque el valor no puede ser 0");
		}catch(IllegalArgumentException e) {
			//Bloque vacio
		}
	}
	
	/**
	 * Prueba la eliminacion de una moneda<br>
	 * <b>Metodos a probar</b>
	 * <br>eliminarMoneda<br>
	 * <b>Objetivo:</b>Probar que el metodo eliminarMoneda(pNombreMoneda) elimine correctamente una moneda de la lista de monedas<br>
	 * <b>Resultados esperados</b>
	 * <br>1) Eliminar la moneda con el nombre dado por parametro de la lista de monedas<br>
	 */
	@Test
	public void testEliminarMoneda() {
		setup();
		ArrayList<Moneda> moneda=conversor.darMonedas();
		int numeroMonedas=moneda.size();
		Moneda primeraMoneda=moneda.get(0);
		int eliminada=conversor.eliminarMoneda(primeraMoneda.darNombre());
		int numeroMonedasActualizado=conversor.darMonedas().size();	
		assertEquals("No se elimino la moneda",1,eliminada);
		assertEquals("El numero de monedas no es correcto",numeroMonedas-1,numeroMonedasActualizado);
	}
}
