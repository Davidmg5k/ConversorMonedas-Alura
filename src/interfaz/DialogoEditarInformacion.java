package interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mundo.ExcepcionMonedas;

public class DialogoEditarInformacion extends JDialog implements ActionListener {
	//-------------------------
	// Constantes
	//-------------------------
    private static String EDITAR = "Editar";
	//-------------------------
	// Atributos
	//-------------------------
    /**
     * una referencia de dialogo editar moneda
     */
    private DialogoEditarMoneda editarMoneda;
	//-------------------------
	// Atributos de la interfaz
	//-------------------------
    /**
     * la moneda por la cual se opera
     */
    private String moneda;
    /**
     * el panel que contiene la informacion de la moneda
     */
    private JPanel panelInformacion;
    /**
     * el pane donde se puede editar la moneda
     */
    private JPanel panelEdicion;
    /**
     * el nombre de la moneda
     */
    private JLabel nombreMoneda;
    /**
     * el tipo de cambio de la moneda
     */
    private JLabel tipoCambio;
    /**
     * el nombre de la moneda
     */
    private JLabel valorNombre;
    /**
     * el valor de la moneda
     */
    private JLabel valorTipoCambio;
    /**
     * el valor que se ingresa del nombre
     */
    private JTextField nombre;
    /**
     * el valor que sei ingresa del tipo de cambio
     */
    private JTextField tipo;

    private JButton editar;
	//-------------------------
	// Constructor
	//-------------------------
    /**
     * construye el dialogo editar informacion
     * @param pVentana la ventana del dialogo editar moneda
     * @param pMoneda el nombre de la moneda
     * @param pValor el valor de la moneda
     */
    public DialogoEditarInformacion(DialogoEditarMoneda pVentana, String pMoneda, String pValor) {
        super(pVentana, true);
        setResizable(false);
        setTitle("Editar Monedas");

        moneda = pMoneda;
        editarMoneda = pVentana;

        panelInformacion = new JPanel(new GridBagLayout());
        panelInformacion.setBorder(BorderFactory.createTitledBorder("Información de la moneda"));

        panelEdicion = new JPanel(new GridBagLayout());
        panelEdicion.setBorder(BorderFactory.createTitledBorder("Editar moneda"));

        nombreMoneda = new JLabel("Nombre de la moneda");
        valorNombre = new JLabel(pMoneda);
        tipoCambio = new JLabel("Tipo de cambio");
        valorTipoCambio = new JLabel(pValor);

        nombre = new JTextField(pMoneda);
        tipo = new JTextField(10);
        tipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!((c >= '0' && c <= '9') || c == '.' || c == KeyEvent.VK_BACK_SPACE)) {
                    evt.consume();
                }
            }
        });

        editar = new JButton("Editar");
        editar.addActionListener(this);
        editar.setActionCommand(EDITAR);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelInformacion.add(nombreMoneda, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panelInformacion.add(valorNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelInformacion.add(tipoCambio, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panelInformacion.add(valorTipoCambio, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelEdicion.add(nombreMoneda, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panelEdicion.add(nombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelEdicion.add(tipoCambio, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panelEdicion.add(tipo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panelEdicion.add(editar, gbc);

        setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(panelInformacion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(panelEdicion, gbc);

        pack();
    }
	//-------------------------
	// Metodos
	//-------------------------
    /**
     * Manejo de eventos de los botones
     * @param e Evento que generó la acción - e != null.
     */
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (comando.equals(EDITAR)) {
            double valor = Double.parseDouble(tipo.getText());
            String nuevoNombre = nombre.getText();
            try {
            	editarMoneda.validarExcepcion(nuevoNombre);
            	editarMoneda.editarNombre(moneda, nuevoNombre);
                editarMoneda.editarTipoCambio(moneda, valor);
                JOptionPane.showMessageDialog( this, String.format("La moneda %s cambio sus valores por:\n Nombre: %s\nTipo de cambio: %s",moneda,nuevoNombre,valor), "Editar Monedas", JOptionPane.INFORMATION_MESSAGE );
            }catch(ExcepcionMonedas e1) {
            	JOptionPane.showMessageDialog( this, "No se pudo editar la moneda:\n" + e1.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
    }
}
