package view;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;

import controller.Vuelos;
import model.Vuelo;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;
import java.awt.Dimension;
import javax.swing.SpinnerNumberModel;

public class IfrmVuelos extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TbDestinos tbDestinos;
	private JTextField txtFiltro;
	private JSpinner numPrecio;
	
	public IfrmVuelos() {
		setTitle("Editor de Vuelos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setClosable(true);
		setResizable(true);
		setBounds(0, 0, 795, 300);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblAvion = new JLabel("Avion");
		panel.add(lblAvion);
		
		CbAviones cbAviones = new CbAviones();
		cbAviones.setPreferredSize(new Dimension(120, 20));
		panel.add(cbAviones);
		
		JButton button = new JButton("+");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Vuelo cli = new Vuelo(null, av_id, des_id, fvuelo, precio);
				Vuelo cli = new Vuelo();
				int resultado = new Vuelos().grabar(cli);
				System.out.println(resultado);
//				Vector<Object> filaData = new Vector<>();
//				filaData.add(cli);
//				filaData.add(cli.getNombre());
//				DefaultTableModel datos = (DefaultTableModel) tbclientes.getModel();
//				datos.addRow(filaData);
				tbDestinos.actualizarTabla(txtFiltro.getText());
			}
		});
		
		JLabel lblDestino = new JLabel("Destino");
		panel.add(lblDestino);
		
		CbDestinos cbDestinos = new CbDestinos();
		cbDestinos.setPreferredSize(new Dimension(120, 20));
		panel.add(cbDestinos);
		
		JLabel lblFecha = new JLabel("Fecha");
		panel.add(lblFecha);
		
		JDateChooser cbFecha = new JDateChooser();
		panel.add(cbFecha);
		
		JLabel lblPrecio = new JLabel("Precio");
		panel.add(lblPrecio);
		
		numPrecio = new JSpinner();
		numPrecio.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		numPrecio.setPreferredSize(new Dimension(70, 20));
		panel.add(numPrecio);
		panel.add(button);
		
		JSeparator separator = new JSeparator();
		panel.add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(tbDestinos);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		txtFiltro = new JTextField();
		panel_2.add(txtFiltro);
		txtFiltro.setColumns(30);
		
		JButton btnNewButton = new JButton("");
		panel_2.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tbDestinos.actualizarTabla(txtFiltro.getText());
			}
		});
		btnNewButton.setIcon(new ImageIcon(IfrmVuelos.class.getResource("/icons/filter16.png")));
		
		tbDestinos = new TbDestinos(txtFiltro.getText());
		mostrar();
	}
	
	public void mostrar() {
		setVisible(true);
	}

}
