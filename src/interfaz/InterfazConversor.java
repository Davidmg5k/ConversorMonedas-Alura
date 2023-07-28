package interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import interfaz.DialogoConversorTemperatura;
import interfaz.PanelConversiones;
import mundo.Conversor;
import mundo.Conversor.Temperaturas;
import mundo.ExcepcionMonedas;
import mundo.Moneda;

public class InterfazConversor extends JFrame{
	
	//-------------------------
	// Atributos
	//-------------------------
	/**
	 * la clase principal del mundo
	 */
	private Conversor conversor;
	/**
	 * el panel conversiones
	 */
	private PanelConversiones panelConversiones;

	//-------------------------
	// Constructor
	//-------------------------
	/**
	 * construye la ventana principal del conversor
	 */
	public InterfazConversor() {
        setTitle("Conversiones");
        setSize(300, 200);
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        
        conversor=new Conversor();
        
        panelConversiones=new PanelConversiones(this);
        
        add(panelConversiones);
        setVisible(true);
	}
	//-------------------------
	// Metodos
	//-------------------------
	
	/**
     * El metodo que muestra el dialogo para convertir temperaturas
     */
    public void dialogoConversorTemperatura() {
    	DialogoConversorTemperatura laTemperatura = new DialogoConversorTemperatura(this);
    	laTemperatura.setVisible( true );
    }
    
    /**
     * El metodo que permite hacer la conversion de las temperaturas seleccionadas y su valor 
     * @param pNTemperatura el nombre de la temperatura
     * @param pTemperaturas el dialogo que muestra el panel para poder convertir las temperaturas
     * @param pValor el valor que se desea convertir 
     * @param pConvTempe las temperaturas de las cuales se van a convertir las temperaturas
     * @return el valor convertido 
     */
    public double conversorTemperatura(DialogoConversorTemperatura pTemperaturas,String pNTemperatura, double pValor, Temperaturas pConvTempe) {
    	double valorConvertido=0.0;
    	try {
    		valorConvertido=conversor.convertirTemperaturas(pValor, pConvTempe);        	
    	}catch (IllegalArgumentException e) {
    		JOptionPane.showMessageDialog( this, "No se puedo hacer la conversion porque:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
    	}  
    	return valorConvertido;
    }
    
    /**
     * el metodo que muestra el frame frameConversorMonedas
     */
    public void frameConversorMonedas() {
    	FrameConversorMonedas conversionMonedas = new FrameConversorMonedas(this,conversor.darMonedas());
    	conversionMonedas.setVisible(true);
    }
	
    /**
     * el metodo que permite la conversion de monedas
     * @param pMoneda el nombre de la moneda
     * @param pValores el valor que se desea convertir
     * @return el valor convertido
     */
    public double conversorMonedas(String pMoneda, double pValores) {
    	return conversor.convertirMonedas(pMoneda, pValores);
    }
    
    /**
     * El metodo que agrega las monedas
     * @param pMoneda el nombre de la moneda
     * @param pTipoCambio el valor de la moneda
     * @throws ExcepcionMonedas verifica la existencia de una moneda
     * @throws IllegalArgumentException verifica que el valor idoneo del tipo de cambio
     */
    public void agregarMonedas(FrameConversorMonedas pFrame, String pMoneda, double pTipoCambio) throws IllegalArgumentException, ExcepcionMonedas {    	
	    conversor.agregarMoneda(pMoneda, pTipoCambio); 	
    }
    
    /**
     * elimina la moneda
     * @param pMoneda el nombre de la moneda
     */
    public void eliminarMoneda(String pMoneda) {
    	conversor.eliminarMoneda(pMoneda);    	
    }
    
    /**
     * busca la moneda por su nombre
     * @param pMoneda el nombre de la moneda
     * @return la moneda
     */
    public Moneda buscarMoneda(String pMoneda) {
    	return conversor.buscarMoneda(pMoneda);
    }
    
	//-------------------------
	// Main
	//-------------------------
    /**
     * El metodo que inicia la aplicacion.
     * @param args Parametros de la ejecucion. No son necesarios
     */
	public static void main(String[] args) {
		InterfazConversor interfaz=new InterfazConversor();
		interfaz.setVisible(true);
	}
}
