package mundo;

public class Moneda {
	
	//-------------------------
	// Atributos
	//-------------------------
	
	/**
	 * El nombre de la moneda
	 */
	private String nombre;
	
	/**
	 * El valor es el tipo de cambio de la moneda
	 */
	private double valor;
	
	//-------------------------
	// Constructor
	//-------------------------
	/**
	 * Crea una moneda con los valores dados por parametros 
	 * @param pNombre el nombre de la moneda
	 * @param pValor el valor de la moneda
	 */
	public Moneda(String pNombre, double pValor) {
		nombre=pNombre;
		valor=pValor;
		invariante();
	}	
	
	//-------------------------
	// Metodos
	//-------------------------
	
	/**
	 * retorna el nombre de la moneda
	 * @return nombre de la moneda
	 */
	public String darNombre() {
		return nombre;
	}
	
	/**
	 * retorna el valor de la monenda
	 * @return valor de la moneda
	 */
	public double darValor() {
		return valor;
	}
	
	/**
	 * modifica el nombre de la moneda
	 * @param pNombre el nombre de la moneda
	 */
	public void nombre(String pNombre) {
		nombre=pNombre;
	}
	
	/**
	 * modifica el valor de la moneda
	 * @param pValor el valor de la moneda
	 */
	public void valor(Double pValor) {
		valor=pValor;
	}
	
	//-------------------------
	// Invariante
	//-------------------------
	
	/**
	 * evalua la invariante de los atributos
	 */
	private void invariante() {
		assert nombre != null: "El nombre no puede ser nulo";
		assert !nombre.equals("") : "El nombre no puede ser vacio";
		assert valor > 0 : "El valor no puede ser menor o igual a 0";
	}
}
