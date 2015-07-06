package view;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import model.Asiento;

import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BtAsiento extends JButton {
	
	/**
	 * 
	 */
	private Asiento butaca;
	private static final long serialVersionUID = 1L;
	
	public BtAsiento() {
		inicia();
	}
	
	public BtAsiento(int id, String nombre, int tipo, boolean ocupado) {	
		butaca = new Asiento(id, nombre, tipo, ocupado);
		inicia();
	}
	
	public BtAsiento(boolean ocupado) {
		butaca = new Asiento();
		butaca.setOcupado(ocupado);
		inicia();
	}
	
	public BtAsiento(int tipo) {
		butaca = new Asiento();
		butaca.setTipo(tipo);
		inicia();
	}
	
	public BtAsiento(Asiento butaca) {
		this.butaca = butaca;
		inicia();
	}

	private void inicia() {
		setBorderPainted(false);
		switch (butaca.getTipo()) {
		case 1:
			setPreferredSize(new Dimension(16, 16));
			setMaximumSize(new Dimension(16, 16));
			setMinimumSize(new Dimension(16, 16));
			setBounds(new Rectangle(0, 0, 16, 16));
			setIcon(new ImageIcon(BtAsiento.class.getResource("/icons/butaca.png")));
			setSelectedIcon(new ImageIcon(BtAsiento.class.getResource("/icons/butaca_verde.png")));
			addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setSelected(!isSelected());
					butaca.setOcupado(isSelected());
				}
			});
			break;
		default:
			setPreferredSize(new Dimension(16, 16));
			setMaximumSize(new Dimension(16, 16));
			setMinimumSize(new Dimension(16, 16));
			setBounds(new Rectangle(0, 0, 16, 16));
			setIcon(new ImageIcon(BtAsiento.class.getResource("/icons/butaca.png")));
			break;
		}
	}
	
	

	public Asiento getAsiento() {
		return butaca;
	}

	public void setAsiento(Asiento butaca) {
		this.butaca = butaca;
	}

	public int getId() {
		return butaca.getId();
	}

	public void setId(int id) {
		butaca.setId(id);
	}

	public String getNombre() {
		return butaca.getNombre();
	}

	public void setNombre(String nombre) {
		butaca.setNombre(nombre);
	}

	public int getTipo() {
		return butaca.getTipo();
	}

	public void setTipo(int tipo) {
		butaca.setTipo(tipo);
	}

	public boolean isOcupado() {
		butaca.setOcupado(isSelected());
		return butaca.isOcupado();
	}

	public void setOcupado(boolean ocupado) {
		setSelected(ocupado);
		butaca.setOcupado(isSelected());
	}

}
