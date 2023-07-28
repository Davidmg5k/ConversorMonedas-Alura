package mundo;

import java.util.ArrayList;

public class Conversor {
	
	//-------------------------
	// Enum
	//-------------------------

	/**
	 * los tipos de temperatura
	 */
	public enum Temperaturas{
		CELCIUS_A_FAHRENHEIT,
		FAHRENHEIT_A_CELCIUS,
		CELCIUS_A_KELVIN,
		KELVIN_A_CELCIUS,
		FAHRENHEIT_A_KELVIN,
		KELVIN_A_FAHRENHEIT
	}
	
	//-------------------------
	// Atributos
	//-------------------------
	
	/**
	 * lista de las monedas creadas
	 */
	private ArrayList<Moneda> monedas;
	
	/**
	 * referencia Monedas
	 */
	private Moneda moneda;
	
	//-------------------------
	// Constructor
	//-------------------------
	
	/**
	 * construye un conversor
	 * añade por defecto las siguiente monedas: USD/COP-4000, COP/USD-0.00025, BRL-6.0, EUR-0.85
	 * @param pTipo el tipo de conversion
	 */
	public Conversor() {
		monedas = new ArrayList<>();
		monedas.add(new Moneda("USD/COP", 3976.47));
	    monedas.add(new Moneda("COP/USD", 0.00025));
	    monedas.add(new Moneda("USD/EUR", 0.90));
	    monedas.add(new Moneda("EUR/USD", 1.11));
	    monedas.add(new Moneda("USD/BRL", 4.80));
	    monedas.add(new Moneda("BRL/USD", 0.21));
	}
	
	//-------------------------
	// Metodos
	//-------------------------
	
	/**
	 * la lista de las monedas
	 * @return la lista de las monedas
	 */
	public ArrayList<Moneda> darMonedas(){
		return monedas;
	}
	
	/**
	 * agrega a la lista monedas una moneda nueva
	 * @param pNombre el nombre de la moneda
	 * @param pValor el valor de la moneda
	 * @throws ExcepcionMonedas valida la existencia de la moneda dada por parametro
	 */
	public void agregarMoneda(String pNombre, double pValor) throws ExcepcionMonedas, IllegalArgumentException {
		if (pValor==0) {
			throw new IllegalArgumentException("El valor de la moneda no puede ser 0.");
		}
		for (Moneda moneda : monedas) {
		    if (moneda.darNombre().equals(pNombre)) {
		    	throw new ExcepcionMonedas(pNombre);		        
		    }
		}
		moneda = new Moneda(pNombre, pValor);
		monedas.add(moneda);
	}
	
	/**
	 * Convertir las monedas dadas por parametro
	 * @param pMonedaActual el nombre de la moneda actual
	 * @param pMondeaConvertir el nombre de la monneda a la que se va a convertir
	 * @return el valor convertido
	 */
	public double convertirMonedas(String pMoneda, double pValor){
		double monedaActual=buscarMoneda(pMoneda).darValor();
		monedaActual=monedaActual*pValor;
		return monedaActual;
	}
	
	/**
	 * busca la moneda por su nombre
	 * @param pNombre nombre de la moneda
	 * @return la moneda encontrada, null si no se encontro
	 */
	public Moneda buscarMoneda(String pNombre) {
		Moneda laMoneda=null;
		for (Moneda moneda : monedas) {
            if (moneda.darNombre().equals(pNombre)) {
                laMoneda=moneda;
                break;
            }
        }
		return laMoneda;	
	}
	
	/**
	 * convierte el valor dado a la temperatura deseada dada por parametro
	 * @param pValor el valor que se desea convertir
	 * @param pTemperaturaConvertir la temperatura a la que se desea convertir
	 * @return el valor convertido
	 */
	public double convertirTemperaturas(double pValor, Temperaturas pTipo) throws IllegalArgumentException {
		if (pValor==0) {
			throw new IllegalArgumentException("El valor a convertir no puede ser 0.");
		}
		double conversion=0.0;
		switch (pTipo) {
			case CELCIUS_A_FAHRENHEIT:
				conversion=(pValor*9/5)+32;
				break;
			case FAHRENHEIT_A_CELCIUS:
				conversion=(pValor-32)*5/9;
				break;
			case KELVIN_A_CELCIUS:
				conversion=pValor-273.15;
				break;
			case CELCIUS_A_KELVIN:
				conversion=pValor+273.15;
				break;
			case KELVIN_A_FAHRENHEIT:
				conversion = (pValor-32)*(5/9)+273.15;
				break;
			case FAHRENHEIT_A_KELVIN:
				conversion = (pValor-273.15)*(9/5)+32;
				break;			
		}		
		return conversion;
	}
	
	/**
	 * elimina la moneda dada por parametro
	 * @param pMoneda la moneda
	 * @return 0 si no fue eliminada, 1 si fue eliminada
	 */
	public int eliminarMoneda(String pMoneda) {
		int eliminada = 0;
	    eliminada = monedas.removeIf(moneda -> moneda.darNombre().equals(pMoneda)) ? 1 : 0;
	    return eliminada;
	}
}

