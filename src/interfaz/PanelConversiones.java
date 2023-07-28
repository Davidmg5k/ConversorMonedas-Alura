package interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import interfaz.InterfazConversor;
import interfaz.RoundBorder;

public class PanelConversiones extends JPanel implements ActionListener{
	//-------------------------
		// Constantes
		//-------------------------
		public static String TEMPERATURA="Temperatura";
		
		public static String MONEDA="Moneda";
		
		//-------------------------
		// Atributos
		//-------------------------
		/**
		 * la referencia a la ventana principal
		 */
		private InterfazConversor interfaz;
		
		//-------------------------
		// Atributos de la interfaz
		//-------------------------
		
		/**
		 * el botoon de la temperatura
		 */
		private JButton botonTemperatura;
		
		/**
		 * el borton de las monedas
		 */
		private JButton botonMoneda;
		
		//-------------------------
		// Constructor
		//-------------------------
		/**
		 * el constructor del panel de conversion
		 * @param pVentana la ventana principal 
		 */

	    public PanelConversiones(InterfazConversor pVentana) {
	    	interfaz=pVentana;
	        setLayout(new GridBagLayout());
	        setBorder(BorderFactory.createTitledBorder("Tipos de Conversiones"));

	        botonTemperatura = new JButton("Temperatura", new ImageIcon("./data/imgs/temperatura.png"));
	        botonTemperatura.setHorizontalTextPosition(SwingConstants.CENTER);
	        botonTemperatura.setVerticalTextPosition(SwingConstants.BOTTOM);
	        botonTemperatura.setBorder(new RoundBorder(20));    
	        botonTemperatura.addActionListener( this );
	        botonTemperatura.setActionCommand(TEMPERATURA);

	        botonMoneda = new JButton("Moneda", new ImageIcon("./data/imgs/dolar.png"));
	        botonMoneda.setHorizontalTextPosition(SwingConstants.CENTER);
	        botonMoneda.setVerticalTextPosition(SwingConstants.BOTTOM);
	        botonMoneda.setBorder(new RoundBorder(20));
	        botonMoneda.addActionListener( this );
	        botonMoneda.setActionCommand(MONEDA);

	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.fill = GridBagConstraints.BOTH;
	        gbc.insets = new Insets(10, 10, 10, 10);

	        gbc.gridx = 0;
	        gbc.gridy = 0;
	        gbc.weightx = 1.0;
	        gbc.weighty = 1.0;
	        add(botonTemperatura, gbc);

	        gbc.gridx = 1;
	        add(botonMoneda, gbc);
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
	    	if( comando.equals( TEMPERATURA ) ) {
	            interfaz.dialogoConversorTemperatura();    		
	        }
	    	else if( comando.equals( MONEDA ) ) {
	    		interfaz.frameConversorMonedas();
	    	}
		}
	}

