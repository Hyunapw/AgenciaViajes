package view;

import java.awt.Rectangle;

import javax.swing.JDialog;

import model.Vuelo;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

import javax.swing.JLabel;
import javax.swing.JComboBox;

import com.toedter.calendar.JDateChooser;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;

/*	CREATE TABLE vuelos (
vu_id     INTEGER        PRIMARY KEY AUTOINCREMENT,
vu_av_id  INTEGER        NOT NULL
                         REFERENCES aviones (av_id),
vu_des_id INTEGER        NOT NULL
                         REFERENCES destinos (des_id),
vu_fvuelo DATE           NOT NULL,
vu_precio DECIMAL (7, 2) NOT NULL
);*/



public class DlgVuelo extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vuelo vuelo;
	
//	Integer id;
//	int av_id;
//	int des_id;
//	Date fvuelo;
//	double precio;
	
//	Integer id;
//	Avion avion;
//	Destino destino;
//	Date fvuelo;
//	double precio;

	public DlgVuelo(Vuelo v) {
		this.vuelo = v;
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
				FormSpecs.PREF_ROWSPEC,}));
		
		JLabel lblAvion = new JLabel("Avion");
		centro.add(lblAvion, "2, 2, fill, fill");
		
		JComboBox cbAvion = new JComboBox();
		centro.add(cbAvion, "4, 2, fill, default");
		
		JLabel lblDestino = new JLabel("Destino");
		centro.add(lblDestino, "2, 4, fill, fill");
		
		JComboBox comboBox = new JComboBox();
		centro.add(comboBox, "4, 4, fill, default");
		
		JLabel lblFecha = new JLabel("Fecha");
		centro.add(lblFecha, "2, 6, fill, fill");
		
		JDateChooser dateChooser = new JDateChooser();
		centro.add(dateChooser, "4, 6, fill, fill");
		
		JLabel lblPrecioPorPlaza = new JLabel("Precio por plaza");
		centro.add(lblPrecioPorPlaza, "2, 8, fill, fill");
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		centro.add(spinner, "4, 8");
		
		JPanel sur = new JPanel();
		getContentPane().add(sur, "1, 2, fill, top");
		
		JButton btnCancelar = new JButton("Aceptar");
		sur.add(btnCancelar);
		
		JButton btnAceptar = new JButton("Cancelar");
		sur.add(btnAceptar);

	}
	
	// METODOS PUBLICOS
	public Vuelo mostrar() {
		setVisible(true);
		dispose();
		return vuelo;
	}

}
