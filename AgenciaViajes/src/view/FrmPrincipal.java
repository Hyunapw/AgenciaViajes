package view;

import java.awt.SystemColor;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JDesktopPane escritorio;

	public FrmPrincipal() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//getContentPane().setLayout(null);
		setSize(800,600);
		setResizable(true);
		setLocationRelativeTo(null);
		
		// Se construye el JDesktopPane
		JDesktopPane escritorio = new JDesktopPane();
		escritorio.setBackground(SystemColor.controlShadow);
		getContentPane().add(escritorio);
		
		// Se construye el MENU
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);
		JMenuItem mntmCliManager = new JMenuItem("Editor de Clientes");
		mntmCliManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				escritorio.add(new IfrmClientes());
			}
		});
		mnClientes.add(mntmCliManager);
		JMenuItem mntmCliListado = new JMenuItem("Listado");
		mnClientes.add(mntmCliListado);
		JMenu mnAviones = new JMenu("Aviones");
		menuBar.add(mnAviones);
		
		JMenuItem mntmEditorDeAviones = new JMenuItem("Editor de Aviones");
		mntmEditorDeAviones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				escritorio.add(new IfrmAviones());
			}
		});
		mnAviones.add(mntmEditorDeAviones);
		
		JMenuItem mntmListado = new JMenuItem("Listado");
		mnAviones.add(mntmListado);
		JMenu mnDestinos = new JMenu("Destinos");
		menuBar.add(mnDestinos);
		
		JMenuItem mntmEditorDeDestinos = new JMenuItem("Editor de Destinos");
		mntmEditorDeDestinos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escritorio.add(new IfrmDestinos());
			}
		});
		mnDestinos.add(mntmEditorDeDestinos);
		
		JMenuItem mntmListado_1 = new JMenuItem("Listado");
		mnDestinos.add(mntmListado_1);
		JMenu mnVuelos = new JMenu("Vuelos");
		menuBar.add(mnVuelos);
		
		JMenuItem mntmEditorDeVuelos = new JMenuItem("Editor de Vuelos");
		mntmEditorDeVuelos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escritorio.add(new IfrmVuelos());
			}
		});
		mnVuelos.add(mntmEditorDeVuelos);
		JMenu mnReservas = new JMenu("Reservas");
		menuBar.add(mnReservas);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
}
