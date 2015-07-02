package view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Pruebas extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TbVuelosCompletos table;

	public Pruebas() {
		setTitle("Pruebas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		CbClientes cbClientes = new CbClientes();
		cbClientes.setBounds(34, 11, 165, 29);
		getContentPane().add(cbClientes);
		
		CbAviones cbAviones = new CbAviones();
		cbAviones.setBounds(34, 51, 165, 29);
		getContentPane().add(cbAviones);
		
		CbDestinos cbDestinos = new CbDestinos();
		cbDestinos.setBounds(33, 94, 166, 29);
		getContentPane().add(cbDestinos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 146, 566, 147);
		getContentPane().add(scrollPane);
		
		table = new TbVuelosCompletos("");
		scrollPane.setViewportView(table);
		
		setVisible(true);
	}
}
