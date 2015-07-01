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

import controller.Clientes;
import model.Cliente;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IfrmDestinos extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TbDestinos tbDestinos;
	private JTextField txtNombre;
	private JTextField txtFiltro;
	
	public IfrmDestinos() {
		setTitle("Editor de Destinos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setClosable(true);
		setResizable(true);
		setBounds(0, 0, 471, 300);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNombre = new JLabel("Nombre");
		panel.add(lblNombre);
		
		txtNombre = new JTextField();
		panel.add(txtNombre);
		txtNombre.setColumns(20);
		
		JButton button = new JButton("+");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cli = new Cliente(null, txtNombre.getText());
				int resultado = new Clientes().grabar(cli);
				System.out.println(resultado);
//				Vector<Object> filaData = new Vector<>();
//				filaData.add(cli);
//				filaData.add(cli.getNombre());
//				DefaultTableModel datos = (DefaultTableModel) tbclientes.getModel();
//				datos.addRow(filaData);
				tbDestinos.actualizarTabla(txtFiltro.getText());
			}
		});
		panel.add(button);
		
		JSeparator separator = new JSeparator();
		panel.add(separator);
		
		txtFiltro = new JTextField();
		panel.add(txtFiltro);
		txtFiltro.setColumns(10);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tbDestinos.actualizarTabla(txtFiltro.getText());
			}
		});
		btnNewButton.setIcon(new ImageIcon(IfrmDestinos.class.getResource("/icons/filter16.png")));
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		tbDestinos = new TbDestinos(txtFiltro.getText());
		scrollPane.setViewportView(tbDestinos);
		mostrar();
	}
	
	public void mostrar() {
		setVisible(true);
	}

}
