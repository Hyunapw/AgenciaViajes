package view;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.Vuelos;
import model.Vuelo;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TbVuelos extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -371381706871909469L;
	private String filtro;
	
	public TbVuelos(String fil) {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				System.out.println();
			}
		});
		this.filtro = fil;	
		addContainerListener(new ContainerAdapter() {
			//Object valorInicial = "";
			Object valorFinal = "";
			@Override
			public void componentAdded(ContainerEvent ce) {
				TableModel tm = ((JTable) ce.getComponent()).getModel();
				tm.addTableModelListener(new TableModelListener() {
					@Override
					public void tableChanged(TableModelEvent tme) {
						int fila = getEditingRow();
						int columna = getEditingColumn();
						Vuelo vu;
						valorFinal = tm.getValueAt(fila,columna);
						tm.removeTableModelListener(this);
						vu = (Vuelo) tm.getValueAt(getEditingRow(), 0);
						switch (columna) {
						case 1:
							vu.setPrecio(((Double) valorFinal));
							break;
						default:
							break;
						}
						ponerVuelo(getEditingRow(), vu);
						Integer respuesta = new Vuelos().grabar(vu);
						if (respuesta==null) {
							actualizarTabla(filtro);
						}
					}
				});
			}
		});
		setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Destino", "Precio"
			}
		) {
			Class[] columnTypes = new Class[] {
				Vuelo.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		setAutoCreateRowSorter(true);
		actualizarTabla(this.filtro);
	}
	
	public void actualizarTabla(String filtro) {
		this.setFiltro(filtro);
		ArrayList<Vector<Object>> tabla = new Vuelos().recuperaTablaVuelos(filtro);
		if (tabla!=null) {
			DefaultTableModel dtm = (DefaultTableModel) getModel();
			dtm.setRowCount(0);
			for (Vector<Object> fila : tabla) {
				dtm.addRow(fila);
			}
		}
	}
	
	private void ponerVuelo(int row, Vuelo des) {
		Vector<Object> filaData = new Vector<>();
		filaData.add(des);
		filaData.add(des.getPrecio());
		DefaultTableModel dtm = (DefaultTableModel) getModel();
		dtm.removeRow(row);
		dtm.insertRow(row, filaData);
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}
