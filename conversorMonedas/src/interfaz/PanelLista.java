package interfaz;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import mundo.Moneda;

public class PanelLista extends JPanel{
	//-------------------------
	// Atributos de la interfaz
	//-------------------------
	/**
	 * la informacion principal
	 */
	private JLabel informacionPrincipal;
	/**
	 * el panel que contendra la lista
	 */
	private JPanel panelLista;
	/**
	 * el listado de las monedas
	 */
	private JList<String> listaNombresMonedas;
	
	/**
	 * la lista de las monedas
	 */
	private ArrayList<Moneda> monedas;
	//-------------------------
	// Constructor
	//-------------------------
	/**
	 * contruye el panel que contiene el listado de las monedas
	 * @param pMonedas el listado de monedas que se mostraran
	 * @param pMensaje el mensaje de informacion
	 */
	public PanelLista(ArrayList<Moneda> pMonedas, String pMensaje) {
		setLayout(new BorderLayout());
		panelLista=new JPanel(new BorderLayout());
        
        informacionPrincipal=new JLabel(pMensaje);
        
        panelLista.setBorder(BorderFactory.createTitledBorder("Lista de monedas"));
        
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Moneda moneda : pMonedas) {
            model.addElement(moneda.darNombre());
        }
        
        listaNombresMonedas = new JList<>(model);        
        panelLista.add(new JScrollPane(listaNombresMonedas));
        
        add(informacionPrincipal, BorderLayout.NORTH);        
        add(panelLista, BorderLayout.CENTER);
	}
	//-------------------------
	// Metodos
	//-------------------------
	/**
	 * La lista que se muestra en pantalla
	 * @return la lista de monedas
	 */
	public JList<String> lista() {
		return listaNombresMonedas;
	}
}
