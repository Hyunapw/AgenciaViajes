package view;

import java.awt.Rectangle;

import javax.swing.JDialog;
import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import model.Avion;
import model.Reserva;
import model.VueloCompleto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.toedter.calendar.JDateChooser;

import controller.Reservas;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

import util.Utilidades;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.border.TitledBorder;

public class DlgHacerReserva extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Reserva reserva;
	private TbVuelosCompletos tbVuelosCompletos;
	private String filtroVuelos;
	private JDateChooser fDesde;
	private JDateChooser fHasta;


	public DlgHacerReserva() {
		setBounds(new Rectangle(0, 0, 800, 600));
		setModal(true);
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("min:grow"),},
			new RowSpec[] {
				RowSpec.decode("fill:min:grow"),
				RowSpec.decode("33px"),}));
		
		JPanel centro = new JPanel();
		//centro.setBounds(new Rectangle(0, 0, 600, 600));
		getContentPane().add(centro, "1, 1, fill, fill");
		centro.setLayout(null);
		
		JPanel pnIzquierdo = new JPanel();
		pnIzquierdo.setBounds(0, 0, 452, 362);
		centro.add(pnIzquierdo);
		pnIzquierdo.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("452px"),},
			new RowSpec[] {
				RowSpec.decode("30px"),
				RowSpec.decode("30px"),
				RowSpec.decode("default:grow"),}));
		
		JPanel pnDestino = new JPanel();
		pnIzquierdo.add(pnDestino, "1, 1, fill, fill");
		pnDestino.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblLugarDeDestino = new JLabel("Lugar de Destino");
		pnDestino.add(lblLugarDeDestino);
		
		CbDestinos cbDestinos = new CbDestinos();
		cbDestinos.setPreferredSize(new Dimension(200, 20));
		pnDestino.add(cbDestinos);
		
		JButton lblVuelos = new JButton("Buscar Vuelos");
		pnDestino.add(lblVuelos);
		lblVuelos.setIcon(new ImageIcon(DlgHacerReserva.class.getResource("/icons/plane-icon.png")));
		
		JPanel pnFechas = new JPanel();
		pnIzquierdo.add(pnFechas, "1, 2, fill, fill");
		pnFechas.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblEntre = new JLabel("Fecha Prevista entre");
		pnFechas.add(lblEntre);
		
		fDesde = new JDateChooser();
		fDesde.setPreferredSize(new Dimension(120, 20));
		fDesde.setMinimumSize(new Dimension(95, 20));
		pnFechas.add(fDesde);
		
		JLabel lblY = new JLabel("y");
		pnFechas.add(lblY);
		
		fHasta = new JDateChooser();
		fHasta.setMinimumSize(new Dimension(95, 20));
		fHasta.setPreferredSize(new Dimension(120, 20));
		pnFechas.add(fHasta);
		
		JScrollPane pnVuelos = new JScrollPane();
		pnIzquierdo.add(pnVuelos, "1, 3, fill, fill");
		
		tbVuelosCompletos = new TbVuelosCompletos(filtroVuelos);
		pnVuelos.setViewportView(tbVuelosCompletos);
		
		JPanel pnAsientos = new JPanel();
		pnAsientos.setBorder(new TitledBorder(null, "Esquema del Avión", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnAsientos.setBounds(462, 0, 281, 362);
		centro.add(pnAsientos);
		pnAsientos.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel sur = new JPanel();
		getContentPane().add(sur, "1, 2, fill, top");
		
		JPanel panel_1 = new JPanel();
		sur.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblCliente = new JLabel("Cliente");
		panel_1.add(lblCliente);
		
		CbClientes cbCliente = new CbClientes();
		panel_1.add(cbCliente);
		JButton btnCancelar = new JButton("Hacer Reserva");
		sur.add(btnCancelar);
		JButton btnAceptar = new JButton("Cancelar");
		sur.add(btnAceptar);
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//getForm();
				if (reserva!=null) {
					setVisible(false);
				}
			}
		});
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reserva = null;
				setVisible(false);
			}
		});
		
		tbVuelosCompletos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				TbVuelosCompletos table = (TbVuelosCompletos) me.getComponent();
				VueloCompleto vu = null;
				try {
					vu = (VueloCompleto) table.getValueAt(table.getSelectedRow(), 0);
					Avion avion = vu.getAvion();
					String filtro = "res_vu_id=" + vu.getVuId();
					ArrayList<Integer> listaAsientosOcupados = new ArrayList<Integer>();
					ArrayList<Reserva> listaReservasVuelo = new Reservas().recuperaPorFiltro(filtro);
					for (Reserva reserva : listaReservasVuelo) {
						listaAsientosOcupados.add(reserva.getAsiento());
					}
					PnAsientosAvion pnAsientosAvion = new PnAsientosAvion(avion, listaAsientosOcupados);
					pnAsientos.add(pnAsientosAvion);
					pnAsientos.repaint();
				} catch (Exception e) {
				}
			}
		});
		
		lblVuelos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> filtros = new ArrayList<>();
				if (cbDestinos.getSelectedIndex()!=-1) {
					filtros.add("vuelos.vu_des_id = " + cbDestinos.obtenerDestinoSeleccionado().getId());
				}
				String desde = Utilidades.fechaToSQL(fDesde.getDate());
				if (desde!=null) {
					filtros.add("vuelos.vu_fvuelo >= '" + desde + "'");
				}
				String hasta = Utilidades.fechaToSQL(fHasta.getDate());
				if (hasta!=null) {
					filtros.add("vuelos.vu_fvuelo <= '" + hasta + "'");
				}
				tbVuelosCompletos.actualizarTabla(filtros.size()==0?null:filtros);
			}
		});

		//setForm();
	}
	
//	private void setForm() {
//		if (reserva!=null) {
//			cbClientes.ponerClienteIdSeleccionado(reserva.getCli_id());
//			cbVuelos.ponerVueloIdSeleccionado(reserva.getVu_id());
//			numAsiento.setValue((Integer) reserva.getAsiento());
//		} else {
//			cbClientes.setSelectedIndex(-1);
//			cbVuelos.setSelectedIndex(-1);
//			numAsiento.setValue(0.0);
//		}
//	}
//	/**
//	 * Recoge el formulario y crea una instancia de Producto
//	 * @return Retorna una instacia de Producto. Null si el formulario esta incorrecto.
//	 */
//	private void getForm() {
//		Reserva r = null;
//		try {
//			r = new Reserva(
//					reserva.getResId(), 
//					cbVuelos.obtenerVueloIdSeleccionado(), 
//					cbClientes.obtenerClienteIdSeleccionado(), 
//					(int) numAsiento.getValue()
//					);
//			reserva.setResId(r.getResId());
//			reserva.setCli_id(r.getCli_id());
//			reserva.setVu_id(r.getVu_id());
//			reserva.setAsiento(r.getAsiento());
//		} catch (Exception e) {
//			mostrarMensaje("Error de formulario");
//		}
//	}
	
	private void mostrarMensaje(String string) {
		JOptionPane.showMessageDialog(null, string);
	}
	
	// METODOS PUBLICOS
	public Reserva mostrar() {
		setVisible(true);
		dispose();
		return reserva;
	}

}
