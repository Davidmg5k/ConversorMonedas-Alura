package mundo;

public class ExcepcionMonedas extends Exception{
	
	/**
	 * Numero de version unico
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de la excepcion con el nombre de la moneda
	 * @param pNombre el nombre de la moneda
	 */
	public ExcepcionMonedas(String pNombre) {
		super(String.format("La moneda %s ya se encuentra agregada", pNombre));
	}
}
