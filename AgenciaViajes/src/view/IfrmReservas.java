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

import controller.Reservas;
import model.Reserva;
import model.Vuelo;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IfrmReservas extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TbReservas tbReservas;
	private JTextField txtFiltro;
	
	public IfrmReservas() {
		setTitle("Editor de Reservas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setClosable(true);
		setResizable(true);
		setBounds(0, 0, 650, 300);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblVuelo = new JLabel("Vuelo");
		panel_1.add(lblVuelo);
		
		CbVuelos cbVuelos = new CbVuelos();
		cbVuelos.setPreferredSize(new Dimension(120, 20));
		panel_1.add(cbVuelos);
		
		JLabel lblCliente = new JLabel("Cliente");
		panel_1.add(lblCliente);
		
		CbClientes cbClientes = new CbClientes();
		cbClientes.setPreferredSize(new Dimension(120, 20));
		panel_1.add(cbClientes);
		
		JLabel lblPlaza = new JLabel("Plaza");
		panel_1.add(lblPlaza);
		
		JSpinner numPrecio = new JSpinner();
		numPrecio.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		numPrecio.setPreferredSize(new Dimension(70, 20));
		panel_1.add(numPrecio);
		
		JButton btnAdd = new JButton("+");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Reserva cli = new Reserva(null, cbVuelos.obtenerVueloIdSeleccionado(), cbClientes.obtenerClienteIdSeleccionado(), (int) numPrecio.getValue());
				int resultado = new Reservas().grabar(cli);
				System.out.println(resultado);
				tbReservas.actualizarTabla(txtFiltro.getText());
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
				tbReservas.actualizarTabla(txtFiltro.getText());
			}
		});
		btnNewButton.setIcon(new ImageIcon(IfrmReservas.class.getResource("/icons/filter16.png")));
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		tbReservas = new TbReservas(txtFiltro.getText());
		tbReservas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				System.out.println(me);
				
				if (me.getClickCount()>=2 && me.getButton()==MouseEvent.BUTTON1) {
					//System.out.println("Doble click con el Boton 1");
					TbReservas table = (TbReservas) me.getComponent();
					Reserva res = null;
					try {
						res = (Reserva) table.getValueAt(table.getSelectedRow(), 0);
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					Reserva rRetorno = new DlgReserva(res).mostrar();
				}
				
			}
		});
		scrollPane.setViewportView(tbReservas);
		mostrar();
	}
	
	public void mostrar() {
		setVisible(true);
	}

}
