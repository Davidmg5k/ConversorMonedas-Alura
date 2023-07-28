package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import mundo.Conversor;
import mundo.ExcepcionMonedas;
import mundo.Moneda;

public class FrameConversorMonedas extends JFrame {
	
	//-------------------------
	// Atributos
	//-------------------------
	/**
	 * referencia de la interfaz principal
	 */
	private InterfazConversor interfazConversor;
	/**
	 * referencia del panel opciones
	 */
	private PanelOpcionesCM panelOpciones;
	
	//-------------------------
	// Atributos de la interfaz
	//-------------------------
	/**
	 * la lista de las monedas
	 */
	private JList<String> listaNombresMonedas;

	/**
	 * el panel de la lista
	 */	
	private PanelLista panelLista;
	/**
	 * la lista de las monedas
	 */
	private JList<String> listaNombres;
	
	/**
	 * lista de las monedas
	 */
	private ArrayList<Moneda> monedas;
	
	//-------------------------
	// Constructor
	//-------------------------
	/**
	 * contruye el frame que contiene todo la interactividad del conversor de monedas
	 * @param pVentana la ventana principal
	 * @param pMonedas la lista de las monedas
	 */
	public FrameConversorMonedas(InterfazConversor pVentana,ArrayList<Moneda> pMonedas) {
		setTitle("Conversor de Monedas");
        setSize(500, 350);
        setResizable(false);
        setLayout(new BorderLayout());
        
        monedas = pMonedas;      
        interfazConversor=pVentana;
        
        panelLista=new PanelLista(pMonedas, "Seleccione la moneda a la que desea convertir el valor ingresado.");
        panelOpciones=new PanelOpcionesCM(this);        
        
        listaNombres=panelLista.lista();
	    listaNombres.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String nombreMonedaSeleccionada = listaNombres.getSelectedValue();   
                conversion(nombreMonedaSeleccionada);                
            }
        });
     
        add(panelLista, BorderLayout.CENTER);
        add(panelOpciones, BorderLayout.SOUTH);
	}
	//-------------------------
	// Metodos
	//-------------------------
    /**
     * convierte el valor capturado de campoResultado a el elemento escogido por el usuario
     * @param pElemento la moneda
     */
	public void conversion(String pElemento) {	
		try {			
			double valor = Double.parseDouble(panelOpciones.darCampoValor());
			double resultado = interfazConversor.conversorMonedas(pElemento, valor);
			JOptionPane.showMessageDialog( this, String.format("El valor convertido de %s es: %s", pElemento, resultado), "Conversion de monedas", JOptionPane.INFORMATION_MESSAGE );
		}catch(Exception e) {
			JOptionPane.showMessageDialog( this, "Ingresar el valor a convertir.", "Error", JOptionPane.WARNING_MESSAGE );
		}		
	}
	
	/**
	 * el dialogo que muestra el apartado de agregar monedas
	 */
	public void dialogoAgregarMoneda() {
		DialogoAgregarMoneda moneda = new DialogoAgregarMoneda(this);
		moneda.setVisible(true);
	}
	
	/**
	 * Agregar monedas
	 * @param pDialogo el dialogo agregar monedas
	 * @param pMoneda el nombre de la moneda
	 * @param pValor el tipo de cambio de la moneda
	 * @return 1 si fue agragada la moneda, 0 si no fue agregada
	 */
	public int agregarMoneda(DialogoAgregarMoneda pDialogo, String pMoneda, double pValor) {
		String mensaje="No se pudo agregar la moneda:\n";
		int agregado=0;
    	try {
    	    interfazConversor.agregarMonedas(this, pMoneda, pValor);
            agregado = 1;             
    	} catch (ExcepcionMonedas e) {
    	    JOptionPane.showMessageDialog( this, mensaje + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
    	} catch (Exception e) {
    	    JOptionPane.showMessageDialog( this, mensaje + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
    	}
    	return agregado;
	}
	
	/**
	 * el metodo que muestra el dialogo eliminar
	 */
	public void dialogoEliminar() {
		DialogoEliminarMoneda moneda = new DialogoEliminarMoneda(this, monedas);
		moneda.setVisible(true);
	}
	
	/**
	 * elimina la moneda con el nombre dado por parametro 
	 * @param pMoneda el nombre de la moneda
	 */
	public void eliminar(String pMoneda) {
		interfazConversor.eliminarMoneda(pMoneda);
	}
	
	/**
	 * el dialogo que muestra editar moneda
	 */
	public void dialogoEditarMoneda() {
		DialogoEditarMoneda moneda = new DialogoEditarMoneda(this, monedas);
		moneda.setVisible(true);
	}
	
	/**
	 * busca la moneda por su nombre
	 * @param pMoneda el nombre de la moneda
	 * @return la moneda
	 */
	public Moneda buscarMoneda(String pMoneda) {
		return interfazConversor.buscarMoneda(pMoneda);
	}
}
