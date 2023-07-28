package interfaz;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import mundo.Conversor.Temperaturas;

public class DialogoConversorTemperatura extends JDialog{
	//-------------------------
	// Atributos
	//-------------------------
	/**
	 * una referencia con la pagina principal
	 */
	private InterfazConversor interfaz;
	
	/**
	 * una referencia con el panel del conversor de temperaturas
	 */
	private ConversorTemperaturasPanel panelTemperaturas;
	//-------------------------
	// Constructor
	//-------------------------
	/**
	 * El constructor del dialogo conversor de temperaturas
	 * @param pVentana la ventana principal
	 */
	public DialogoConversorTemperatura(InterfazConversor pVentana) {
		super( pVentana, true );
		interfaz=pVentana;
		
        panelTemperaturas = new ConversorTemperaturasPanel( this );
        getContentPane( ).add( panelTemperaturas );
        setResizable( false );
        setTitle( "Conversor de temperatura" );
        pack( );
	}
	//-------------------------
	// Metodos
	//-------------------------
	/**
	 * Convierte la temperatura
	 * @param pNTemperatura el numbre de la nemperatura
	 * @param pTemperatura el tipo de conversion que desea ejecutar
	 * @param pValor el valor que se desea convertir
	 */
	public void conversorTemperatura(String pNTemperatura, Temperaturas pTemperatura, String pValor) {
		double valor = Double.parseDouble(pValor);
		double resultado = interfaz.conversorTemperatura(this, pNTemperatura, valor, pTemperatura);
		JOptionPane.showMessageDialog( this, String.format("El valor convertido de %s es: %s", pNTemperatura, resultado), "Conversion de Temperaturas", JOptionPane.INFORMATION_MESSAGE );
	}
}
