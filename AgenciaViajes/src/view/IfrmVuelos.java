package view;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;

import controller.Vuelos;
import controller.VuelosCompletos;
import model.Vuelo;
import model.VueloCompleto;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

import com.toedter.calendar.JDateChooser;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IfrmVuelos extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TbVuelosCompletos tbVuelos;
	private JTextField txtFiltro;
	
	public IfrmVuelos() {
		setTitle("Editor de Vuelos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setClosable(true);
		setResizable(true);
		setBounds(0, 0, 650, 300);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblAvion = new JLabel("Avion");
		panel_1.add(lblAvion);
		
		CbAviones cbAviones = new CbAviones();
		cbAviones.setPreferredSize(new Dimension(120, 20));
		panel_1.add(cbAviones);
		
		JLabel lblDestino = new JLabel("Destino");
		panel_1.add(lblDestino);
		
		CbDestinos cbDestinos = new CbDestinos();
		cbDestinos.setPreferredSize(new Dimension(120, 20));
		panel_1.add(cbDestinos);
		
		JLabel lblFecha = new JLabel("Fecha");
		panel_1.add(lblFecha);
		
		JDateChooser cbFecha = new JDateChooser();
		panel_1.add(cbFecha);
		
		JLabel lblPrecio = new JLabel("Precio");
		panel_1.add(lblPrecio);
		
		JSpinner numPrecio = new JSpinner();
		numPrecio.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		numPrecio.setPreferredSize(new Dimension(70, 20));
		panel_1.add(numPrecio);
		
		JButton btnAdd = new JButton("+");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vuelo cli = new Vuelo(null, cbAviones.obtenerAvionIdSeleccionado(), cbDestinos.obtenerDestinoIdSeleccionado(), cbFecha.getDate(), (double) numPrecio.getValue());
				int resultado = new Vuelos().grabar(cli);
				System.out.println(resultado);
				tbVuelos.actualizarTabla(txtFiltro.getText());
			}
		});
		panel_1.add(btnAdd);
		
		JSeparator separator_1 = new JSeparator();
		panel_1.add(separator_1);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JSeparator separator = new JSeparator();
		panel.add(separator);
		
		txtFiltro = new JTextField();
		panel.add(txtFiltro);
		txtFiltro.setColumns(10);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tbVuelos.actualizarTabla(txtFiltro.getText());
			}
		});
		btnNewButton.setIcon(new ImageIcon(IfrmVuelos.class.getResource("/icons/filter16.png")));
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		tbVuelos = new TbVuelosCompletos(txtFiltro.getText());
		tbVuelos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				System.out.println(me);
				
				if (me.getClickCount()>=2 && me.getButton()==MouseEvent.BUTTON1) {
					System.out.println("Doble click con el Boton 1");
					TbVuelosCompletos table = (TbVuelosCompletos) me.getComponent();
					VueloCompleto vuc = null;
					try {
						vuc = (VueloCompleto) table.getValueAt(table.getSelectedRow(), 0);
					} catch (Exception e) {
						// TODO: handle exception
					}
					new DlgVuelo(vuc.toVuelo());
				}
				
			}
		});
		scrollPane.setViewportView(tbVuelos);
		mostrar();
	}
	
	public void mostrar() {
		setVisible(true);
	}

}
