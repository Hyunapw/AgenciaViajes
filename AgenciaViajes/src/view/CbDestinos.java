package view;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import controller.Destinos;
import util.Utilidades;
import model.Destino;

public class CbDestinos extends JComboBox<Destino> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cbFiltro;
	
	public CbDestinos() {
		super();
		cbFiltro = null;
		setEditable(true);
		setSelectedIndex(-1);
		addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
				setSelectedIndex(-1);
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				if (cbFiltro!=null && cbFiltro.length()>0){
					setFiltro(((JTextField) getEditor().getEditorComponent()).getText());
					recargarComboFiltrado();
				} else {
					setFiltro(((JTextField) getEditor().getEditorComponent()).getText());
					recargarComboFiltrado();
				}
			}
		});
		
	}
	
	public String getFiltro() {
		return cbFiltro;
	}

	public void setFiltro(String f) {
		this.cbFiltro = f;
	}

	protected void recargarComboFiltrado() {
		removeAllItems();
		ArrayList<String> filtros = new ArrayList<>();
		filtros.add("destinos.des_lugar LIKE '%" + cbFiltro + "%'");
		String filtroString = Utilidades.creaFiltroOR(filtros);
		ArrayList<Destino> lista = new Destinos().recuperaPorFiltro(filtroString);
		if (lista!=null) {
			for (Destino c : lista) {
				addItem(c);
			}
			//setSelectedIndex(lista.size()-1);
		}
	}
	
	public Destino obtenerDestinoSeleccionado() {
		return getItemAt(getSelectedIndex());
	}
	
	public int obtenerDestinoIdSeleccionado() {
		//Devuelve el ID del Genero selecionado en el Combo
		return getItemAt(getSelectedIndex()).getId();
	}
	
	public void ponerDestinoIdSeleccionado(Integer idCliente) {
		//Devuelve el ID del Genero selecionado en el Combo
		recargarCombo(idCliente);
	}
	
	private void recargarCombo(Integer idCliente) {
		if (idCliente!=null){
			int idSel = idCliente;
			int pos = 0;
			removeAllItems();
			ArrayList<Destino> lista = new Destinos().recuperaPorFiltro("destinos.des_id =" + idCliente);
			if (lista!=null) {
				for (Destino c : lista) {
					addItem(c);
					if (c.getId() == idSel) {
						setSelectedIndex(pos);
					}
					pos++;
				}
			}
		}
	}

	/**Carga todos los Generos del Repositorio ClientesBDD, y deja seleccionado el que tiene el idCliente
	 * @param idCliente
	 */
	public void recargarCombo() {
		removeAllItems();
		ArrayList<Destino> lista = new Destinos().recuperaPorFiltro(null);
		if (lista!=null) {
			for (Destino c : lista) {
				addItem(c);
			}
			setSelectedIndex(-1);
		}
	}
	
	public String obtenerNombreCliente() {
		return ((JTextField) getEditor().getEditorComponent()).getText();
	}

}
