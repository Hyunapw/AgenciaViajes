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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;

import model.Reserva;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgReserva extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Reserva reserva;
	
	private CbVuelos cbVuelos;
	private CbClientes cbClientes;
	private JSpinner numAsiento;

	public DlgReserva(Reserva r) {
		this.reserva = r;
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
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.PREF_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.PREF_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel lblVuelo = new JLabel("Vuelo");
		centro.add(lblVuelo, "2, 2, fill, fill");
		
		cbVuelos = new CbVuelos();
		centro.add(cbVuelos, "4, 2, fill, default");
		
		JLabel lblCliente = new JLabel("Cliente");
		centro.add(lblCliente, "2, 4, fill, fill");
		
		cbClientes = new CbClientes();
		centro.add(cbClientes, "4, 4, fill, default");
		
		JLabel lblPrecioPorPlaza = new JLabel("Asiento");
		centro.add(lblPrecioPorPlaza, "2, 6, fill, fill");
		
		numAsiento = new JSpinner();
		numAsiento.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		centro.add(numAsiento, "4, 6");
		
		JPanel pnAsientos = new JPanel();
		centro.add(pnAsientos, "4, 8, fill, fill");
		
		JPanel sur = new JPanel();
		getContentPane().add(sur, "1, 2, fill, top");
		
		JButton btnCancelar = new JButton("Aceptar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getForm();
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

		setForm();
	}
	
	private void setForm() {
		if (reserva!=null) {
			cbClientes.ponerClienteIdSeleccionado(reserva.getCli_id());
			cbVuelos.ponerVueloIdSeleccionado(reserva.getVu_id());
			numAsiento.setValue((Integer) reserva.getAsiento());
		} else {
			cbClientes.setSelectedIndex(reserva.getCli_id());
			cbVuelos.setSelectedIndex(reserva.getVu_id());
			numAsiento.setValue(0.0);
		}
	}
	/**
	 * Recoge el formulario y crea una instancia de Producto
	 * @return Retorna una instacia de Producto. Null si el formulario esta incorrecto.
	 */
	private void getForm() {
		Reserva r = null;
		try {
			r = new Reserva(
					reserva.getResId(), 
					cbVuelos.obtenerVueloIdSeleccionado(), 
					cbClientes.obtenerClienteIdSeleccionado(), 
					(int) numAsiento.getValue()
					);
			reserva.setResId(r.getResId());
			reserva.setCli_id(r.getCli_id());
			reserva.setVu_id(r.getVu_id());
			reserva.setAsiento(r.getAsiento());
		} catch (Exception e) {
			mostrarMensaje("Error de formulario");
		}
	}
	
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
