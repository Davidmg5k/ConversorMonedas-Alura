package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class AgregarMonedaPanel extends JPanel implements ActionListener{
	//-------------------------
	// Constantes
	//-------------------------
	private static String AGREGAR="Agregar";
	
	//-------------------------
	// Atributos
	//-------------------------
	/**
	 * referencia del dialogoa agregar moneda
	 */
	private DialogoAgregarMoneda dialogoAgregarMoneda;
	
	//-------------------------
	// Atributos de la interfaz
	//-------------------------
	/**
	 * el nombre de la moneda
	 */
    private JLabel labelNombreMoneda;
    /**
     * el campo de texto del nombre de la moneda
     */
    private JTextField textFieldNombreMoneda;
    /**
     * el tipo de cambio de la moneda
     */
    private JLabel labelTipoCambio;
    /**
     * el tipo de cambio de la moneda
     */
    private JTextField textFieldTipoCambio;
    /**
     * el boton agregar
     */
    private JButton agregar;

	//-------------------------
	// Constructor
	//-------------------------
    public AgregarMonedaPanel(DialogoAgregarMoneda pDialogo) {
    	dialogoAgregarMoneda=pDialogo;
        setLayout(new GridLayout(3, 2, 10, 10)); 
        setBorder(BorderFactory.createTitledBorder("Informacion moneda"));
        
        labelNombreMoneda = new JLabel("Nombre de la moneda");
        textFieldNombreMoneda = new JTextField("USD/COP");
        
        add(labelNombreMoneda);
        add(textFieldNombreMoneda);

        labelTipoCambio = new JLabel("Tipo de cambio");
        textFieldTipoCambio = new JTextField();
        textFieldTipoCambio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!((c >= '0' && c <= '9') || c == '.' || c == KeyEvent.VK_BACK_SPACE)) {
                    evt.consume();
                }
            }
        });

        add(labelTipoCambio);
        add(textFieldTipoCambio);

        agregar = new JButton("Agregar");
        agregar.addActionListener( this );
        agregar.setActionCommand( AGREGAR );
        add(agregar);
    }
    
	//-------------------------
	// Metodos
	//-------------------------
    
    /**
     * Manejo de eventos de los botones
     * @param e Evento que generó la acción - e != null.
     */
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand( );
    	if( comando.equals( AGREGAR ) ) {
    		int agregado=0;
    		try {
	    		agregado=dialogoAgregarMoneda.agregarMoneda(textFieldNombreMoneda.getText(), textFieldTipoCambio.getText());
	    		if (agregado == 1) {
	    			JOptionPane.showMessageDialog( this, "Valores agregados", "Agregar Monedas", JOptionPane.INFORMATION_MESSAGE );
	    		}
    		}catch(Exception e1) {
    			JOptionPane.showMessageDialog( this, "Todos los campos son obligatorios.", "Error", JOptionPane.WARNING_MESSAGE );
    		}
    	}
	}

}
