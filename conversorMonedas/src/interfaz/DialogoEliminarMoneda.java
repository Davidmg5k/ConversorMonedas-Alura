package interfaz;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;

import mundo.Moneda;

public class DialogoEliminarMoneda extends JDialog{
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

	//-------------------------
	// Constructor
	//-------------------------
	/**
	 * el dialogo que permite eliminar una moneda
	 * @param pVentana la ventana del conversor de monedas
	 * @param pMonedas las monedas
	 */
	public DialogoEliminarMoneda(FrameConversorMonedas pVentana, ArrayList<Moneda> pMonedas) {
		super( pVentana, true );
		interfaz=pVentana;
		panelLista=new PanelLista(pMonedas,"Seleccione la moneda que desea eliminar.");
		lista=panelLista.lista();
	    lista.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String nombreMonedaSeleccionada = lista.getSelectedValue();   
                eliminarMoneda(nombreMonedaSeleccionada); 
            }
        });
		getContentPane( ).add(panelLista, BorderLayout.CENTER);
        setResizable( false );
        setTitle( "ELiminar Monedas" );
        pack( );	
	}
	//-------------------------
	// Metodos
	//-------------------------
	
	/**
	 * Elimina la moneda seleccionada de la lista
	 * @param pMoneda el nombre de la lista
	 */
	public void eliminarMoneda(String pMoneda) {
		int opcion = JOptionPane.showConfirmDialog(null, String.format("¿Está seguro que desea eliminar %s.?", pMoneda), "Eliminar moneda", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
        	interfaz.eliminar(pMoneda);
        	JOptionPane.showMessageDialog( this, String.format("La mononeda %s ha sido eliminada correctamente.\nCierre las ventanas emergentes para actualizar los valores.",pMoneda), "Elimanar Monedas", JOptionPane.INFORMATION_MESSAGE );
        } 		
	}
}
