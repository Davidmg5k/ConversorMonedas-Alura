package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import mundo.Conversor.Temperaturas;

public class ConversorTemperaturasPanel extends JPanel{
	
	//-------------------------
	// Atributos 
	//-------------------------
	/**
	 * una referencia el dialogo de conversion de temperatura
	 */
	private DialogoConversorTemperatura temperaturas;
	
	//-------------------------
	// Atributos de la interfaz
	//-------------------------
	/**
	 * el label de la descripcion
	 */
	private JLabel labelDescripcion;
	/**
	 * el label de la informacion
	 */
	private JLabel labelInfo;
	/**
	 * el campo de texto del valora a evaluar
	 */
	private JTextField campoValorEvaluar;
	/**
	 * la contenedora de las temperaturas
	 */
	private Temperaturas[] temperaturaContenedora;
	/**
	 * la lista de las temperaturas que se muestra 
	 */
	private JList<String> jListTemperaturas;
	
	//-------------------------
	// Constructor
	//-------------------------
	
	/**
	 * El constructor del panel del conversor de temperaturas
	 * @param pVentana el dialogo del conversor
	 */
	public ConversorTemperaturasPanel(DialogoConversorTemperatura pVentana) {
    	temperaturas=pVentana;
        setLayout(new BorderLayout());
        
        
        labelDescripcion = new JLabel("\t Seleccione el tipo de temperatura a la que desea convertir el valor ingresado. ");
        
        JPanel panelListaTemperaturas = new JPanel(new BorderLayout());        
        JPanel panelConversion = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        panelListaTemperaturas.setBorder(BorderFactory.createTitledBorder("Lista de Temperaturas"));
        temperaturaContenedora = Temperaturas.values();
        
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Temperaturas temperatura : temperaturaContenedora) {        	
            listModel.addElement(temperatura(temperatura));
        }
        jListTemperaturas = new JList<String>(listModel);     
        jListTemperaturas.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String nombreTemperaturaSeleccionada = jListTemperaturas.getSelectedValue();
                Temperaturas temperaturaSelccionada = temperatura(nombreTemperaturaSeleccionada);
                conversion(temperaturaSelccionada, nombreTemperaturaSeleccionada);
            }
        });
        panelListaTemperaturas.add(new JScrollPane(jListTemperaturas), BorderLayout.CENTER);
	
        panelConversion.setLayout(new FlowLayout());
        panelConversion.setVisible(true);

        labelInfo = new JLabel("Ingrese el valor que desea convertir: ");
        panelConversion.add(labelInfo);
        
        campoValorEvaluar = new JTextField(10);
        campoValorEvaluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!((c >= '0' && c <= '9') || c == '.' || c == '-' || c == KeyEvent.VK_BACK_SPACE)) {
                    evt.consume();
                }
            }
        });
        panelConversion.add(campoValorEvaluar);
        
        add(labelDescripcion,BorderLayout.NORTH);
        add(panelListaTemperaturas,BorderLayout.CENTER);
        add(panelConversion,BorderLayout.SOUTH);
	}
	//-------------------------
	// Metodos
	//-------------------------
	
	/**
	 * determinar la temperatura
	 * @param pTemperatura la temperatura
	 * @return la temperatura
	 */
	public Temperaturas temperatura(String pTemperatura) {
		Temperaturas laTemperatura = null;
		for (Temperaturas temperatura : temperaturaContenedora) {
			if (pTemperatura.equals(temperatura(temperatura))) {				
				laTemperatura=temperatura;
			}
		}
		return laTemperatura;
	}
	
	/**
	 * convertir a string cada uno de los items de la enumeracion
	 * @param pTemperatura la temperatura
	 * @return el nombre de la temperatura
	 */
	public String temperatura(Temperaturas pTemperatura) {
		String[] palabras = pTemperatura.name().split("_");
    	StringBuilder sb = new StringBuilder();
        for (String palabra : palabras) {
            sb.append(palabra.charAt(0)).append(palabra.substring(1).toLowerCase()).append(" ");
        }
        return sb.toString().trim();
	}
	
	/**
	 * convierte el valor del campoValorEvaluar al de la temperatura capturada
	 * @param pTemperatura la temperatura
	 * @param pNTemperatura el nombre de la temperatura
	 */
	public void conversion(Temperaturas pTemperatura, String pNTemperatura) {
		try {
			temperaturas.conversorTemperatura(pNTemperatura, pTemperatura, campoValorEvaluar.getText());
		}catch(Exception e) {
			JOptionPane.showMessageDialog( this, "Ingresar el valor a convertir.", "Error", JOptionPane.WARNING_MESSAGE );
		}		
	}
}
