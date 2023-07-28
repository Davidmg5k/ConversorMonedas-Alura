package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelOpcionesCM extends JPanel implements ActionListener{
	//-------------------------
	// Constantes
	//-------------------------
	private static String AGREGAR="Agregar";
	private static String ELIMINAR="Eliminar";
	private static String EDITAR="Editar";
	
	//-------------------------
	// Atributos
	//-------------------------
	/**
	 * referencia de la ventana conversor de monedas
	 */
	private FrameConversorMonedas conversor;
	
	//-------------------------
	// Atributos de la interfaz
	//-------------------------
	/**
	 * el label de la informacion superior de la ventana
	 */
	private JLabel informacion;
	/**
	 * el campo de texto donde se ingresa el valor a convertir
	 */
	private JTextField campoValor;
	/**
	 * el panel opciones
	 */
	private JPanel opciones;
	/**
	 * el boton agregar
	 */
	private JButton agregar;
	/**
	 * el boton eliminar
	 */
	private JButton eliminar;
	/**
	 * el boton editar
	 */
	private JButton editar;
	//-------------------------
	// Constructor
	//-------------------------

	/**
	 * construye el panel opciones cm
	 * @param pVentana la ventana priincipal del conversor de monedas
	 */
	public PanelOpcionesCM(FrameConversorMonedas pVentana) {
		conversor=pVentana;
		setLayout(new GridBagLayout());
		
		opciones=new JPanel();		
		opciones.setBorder(BorderFactory.createTitledBorder("Opciones"));
		
		informacion=new JLabel("Ingrese el valor que desea convertir: ");
		campoValor=new JTextField();
		campoValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!((c >= '0' && c <= '9') || c == '.' || c == KeyEvent.VK_BACK_SPACE)) {
                    evt.consume();
                }
            }
        });	
		
		agregar=new JButton("Agregar");
		agregar.addActionListener( this );
        agregar.setActionCommand( AGREGAR );
        
		eliminar=new JButton("Eliminar");
		eliminar.addActionListener( this );
        eliminar.setActionCommand( ELIMINAR );
        
		editar=new JButton("Editar");
		editar.addActionListener( this );
        editar.setActionCommand( EDITAR );
		
		opciones.add(agregar);
		opciones.add(eliminar);
		opciones.add(editar);
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.gridwidth = 1; 
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH; 
        gbc.weightx = 1.0; 
        gbc.weighty = 1.0;
        add(informacion, gbc);
        
        gbc.gridx = 1; 
        gbc.gridy = 0; 
        add(campoValor, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(opciones, gbc);        
		
        
	}
	//-------------------------
	// Metodos
	//-------------------------
	
	/**
	 * el campo valor
	 * @return el texto del campo
	 */
	public String darCampoValor() {
		return campoValor.getText();
	}

    /**
     * Manejo de eventos de los botones
     * @param e Evento que generó la acción - e != null.
     */
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand( );
    	if( comando.equals( AGREGAR ) ) {
    		conversor.dialogoAgregarMoneda();
    	}
    	else if( comando.equals( ELIMINAR ) ) {
    		conversor.dialogoEliminar();
    	}
    	else if( comando.equals( EDITAR ) ) {
    		conversor.dialogoEditarMoneda();
    	}
	}

}
