package interfaz;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class DialogoAgregarMoneda extends JDialog{
	//-------------------------
	// Atributos
	//-------------------------
	/**
	 * una referencia con la pagina principal
	 */
	private FrameConversorMonedas interfaz;
	/**
	 * referencia del panel agregar moneda
	 */
	private AgregarMonedaPanel agregarMoneda;
		
	//-------------------------
	// Constructor
	//-------------------------
	/**
	 * El constructor del dialogo conversor de temperaturas
	 * @param pVentana la ventana principal
	 */
	public DialogoAgregarMoneda(FrameConversorMonedas pVentana) {
		super( pVentana, true );
		interfaz=pVentana;
		
		agregarMoneda = new AgregarMonedaPanel(this);
		getContentPane( ).add( agregarMoneda );
        setResizable( false );
        setTitle( "Agregar moneda" );
        pack( );		
	}
	//-------------------------
	// Metodos
	//-------------------------
	/**
	 * agrega la moneda
	 * @param pMoneda el nombre
	 * @param pValor el valor 
	 * @return 0 si no se agrega, 1 si se agrega
	 */
	public int agregarMoneda(String pMoneda, String pValor) {
		double valor = Double.parseDouble(pValor);
		int agregado = 0;
		agregado = interfaz.agregarMoneda(this,pMoneda,valor);
		return agregado;
	}
}
