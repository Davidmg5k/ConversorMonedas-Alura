package interfaz;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JList;

import mundo.ExcepcionMonedas;
import mundo.Moneda;

public class DialogoEditarMoneda extends JDialog{
	//-------------------------
	// Atributos
	//-------------------------
	/**
	 * una referencia con la pagina principal
	 */
	private FrameConversorMonedas interfaz;
	
	//-------------------------
	// Atributos de la interfaz
	//-------------------------
	/**
	 * el panel que contiene la lista de las monedas
	 */
	private PanelLista panelLista;
	/**
	 * la lista de las monedas
	 */
	private JList<String> lista;
	
	/**
	 * el listado de las monedas
	 */
	private ArrayList<Moneda> monedas;

	//-------------------------
	// Constructor
	//-------------------------
	/**
	 * el dialogo que muestra la informacion para editar una moneda
	 * @param pVentana la venta principal para convertir una moneda
	 * @param pMonedas las monedas
	 */
	public DialogoEditarMoneda(FrameConversorMonedas pVentana, ArrayList<Moneda> pMonedas) {
		super( pVentana, true );
		interfaz=pVentana;
		monedas=pMonedas;
		panelLista=new PanelLista(pMonedas,"Seleccione la moneda que desea editar.");
		lista=panelLista.lista();
	    lista.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String nombreMonedaSeleccionada = lista.getSelectedValue();   
                dialogoEditar(nombreMonedaSeleccionada);
            }
        });
		getContentPane( ).add(panelLista, BorderLayout.CENTER);
        setResizable( false );
        setTitle( "Editar Monedas" );
        pack( );		
	}
	//-------------------------
	// Metodos
	//-------------------------
	/**
	 * metodo que muestra el dialogo editar
	 * @param pMoneda
	 */
	public void dialogoEditar(String pMoneda) {
		DialogoEditarInformacion editar = new DialogoEditarInformacion(this, pMoneda, valor(pMoneda));
		editar.setVisible(true);
	}
	
	/**
	 * editar el nombre de la moneda
	 * @param pMoneda el valor actualizado
	 * @param pNombre el nombre actual
	 */
	public void editarNombre(String pMoneda, String pNombre) {
		Moneda moneda=interfaz.buscarMoneda(pMoneda);
		moneda.nombre(pNombre);
	}
	
	/**
	 * editar el tipo de cambio de la moneda
	 * @param pValor el valor actualizado
	 * @param pMoneda la moneda a buscar
	 */
	public void editarTipoCambio(String pMoneda,double pValor) {
		Moneda moneda=interfaz.buscarMoneda(pMoneda);
		moneda.valor(pValor);
	}	
	
	/**
	 * obtiene el valor del tipo de cambio de la moneda
	 * @param pMoneda nombre de la moneda
	 * @return el valor de la moneda
	 */
	public String valor(String pMoneda) {
		Moneda moneda=interfaz.buscarMoneda(pMoneda);
		String valor=String.valueOf(moneda.darValor());
		return valor;
	}
	
	/**
	 * valida la existencia de la moneda en la lista de monedas
	 * @param pMoneda nombre de la moneda
	 * @throws ExcepcionMonedas valida la existencia de una moneda
	 */
	public void validarExcepcion(String pMoneda) throws ExcepcionMonedas{
		Moneda moneda=interfaz.buscarMoneda(pMoneda);
		if (moneda!=null && moneda.darNombre().equals(pMoneda)) {
			throw new ExcepcionMonedas(pMoneda);
		}
	}
}
