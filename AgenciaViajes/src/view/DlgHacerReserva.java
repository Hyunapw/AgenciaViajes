package view;

import java.awt.Rectangle;

import javax.swing.JDialog;
import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import model.Reserva;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.toedter.calendar.JDateChooser;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

import util.Utilidades;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.util.ArrayList;

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
		getContentPane().add(centro, "1, 1, fill, fill");
		centro.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.PREF_ROWSPEC,
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				FormSpecs.PREF_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.PREF_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel lblCliente = new JLabel("Cliente");
		centro.add(lblCliente, "2, 2, right, default");
		
		CbClientes cbCliente = new CbClientes();
		centro.add(cbCliente, "4, 2, fill, default");
		
		JLabel lblLugarDeDestino = new JLabel("Lugar de Destino");
		centro.add(lblLugarDeDestino, "2, 4, right, default");
		
		CbDestinos cbDestinos = new CbDestinos();
		centro.add(cbDestinos, "4, 4, fill, default");
		
		JLabel lblFechaPrevista = new JLabel("Fecha Prevista");
		centro.add(lblFechaPrevista, "2, 6, right, default");
		
		JPanel panel = new JPanel();
		centro.add(panel, "4, 6, left, fill");
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblEntre = new JLabel("entre");
		panel.add(lblEntre);
		
		fDesde = new JDateChooser();
		fDesde.setPreferredSize(new Dimension(120, 20));
		fDesde.setMinimumSize(new Dimension(95, 20));
		panel.add(fDesde);
		
		JLabel lblY = new JLabel("y");
		panel.add(lblY);
		
		fHasta = new JDateChooser();
		fHasta.setMinimumSize(new Dimension(95, 20));
		fHasta.setPreferredSize(new Dimension(120, 20));
		panel.add(fHasta);
		
		JButton lblVuelos = new JButton("Buscar Vuelos");
		lblVuelos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> filtros = new ArrayList<>();
				filtros.add("vuelos.vu_des_id = " + cbDestinos.obtenerDestinoSeleccionado().getId());
				String desde = Utilidades.fechaToSQL(fDesde.getDate());
				String hasta = Utilidades.fechaToSQL(fHasta.getDate());
				if (desde!=null) {
					filtros.add("vuelos.vu_fvuelo >= '" + desde + "'");
				}
				if (hasta!=null) {
					filtros.add("vuelos.vu_fvuelo <= '" + hasta + "'");
				}
				tbVuelosCompletos.actualizarTabla(filtros);
			}
		});
		lblVuelos.setIcon(new ImageIcon(DlgHacerReserva.class.getResource("/icons/plane-icon.png")));
		centro.add(lblVuelos, "2, 8, right, top");
		
		JScrollPane scrollPane = new JScrollPane();
		centro.add(scrollPane, "4, 8, fill, fill");
		
		tbVuelosCompletos = new TbVuelosCompletos(filtroVuelos);
		scrollPane.setViewportView(tbVuelosCompletos);
		
		PnAsientos pnAsientos = new PnAsientos();
		centro.add(pnAsientos, "4, 10, fill, fill");
		
		JPanel sur = new JPanel();
		getContentPane().add(sur, "1, 2, fill, top");
		
		JButton btnCancelar = new JButton("Aceptar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//getForm();
				if (reserva!=null) {
					setVisible(false);
				}
			}
		});
		sur.add(btnCancelar);
		
		JButton btnAceptar = new JButton("Cancelar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reserva = null;
				setVisible(false);
			}
		});
		sur.add(btnAceptar);

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
