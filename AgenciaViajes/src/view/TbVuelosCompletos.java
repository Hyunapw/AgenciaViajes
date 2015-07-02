	package view;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.VuelosCompletos;
import model.VueloCompleto;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TbVuelosCompletos extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -371381706871909469L;
	private String filtro;
	
	public TbVuelosCompletos(String fil) {
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
						VueloCompleto vuc;
						valorFinal = tm.getValueAt(fila,columna);
						tm.removeTableModelListener(this);
						vuc = (VueloCompleto) tm.getValueAt(getEditingRow(), 0);
						switch (columna) {
						case 2:
							vuc.setPrecio(((Double) valorFinal));
							break;
						default:
							break;
						}
						ponerVuelo(getEditingRow(), vuc);
						Integer respuesta = new VuelosCompletos().grabar(vuc);
						if (respuesta==null) {
							actualizarTabla(filtro);
						}
					}
				});
			}
		});
		setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"Vuelo", "Capacidad Total", "Precio por Plaza"
			}
		) {
			Class[] columnTypes = new Class[] {
				VueloCompleto.class, Integer.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		setAutoCreateRowSorter(true);
		actualizarTabla(this.filtro);
	}
	
	public void actualizarTabla(String filtro) {
		this.setFiltro(filtro);
		ArrayList<Vector<Object>> tabla = new VuelosCompletos().recuperaTablaVuelos(filtro);
		if (tabla!=null) {
			DefaultTableModel dtm = (DefaultTableModel) getModel();
			dtm.setRowCount(0);
			for (Vector<Object> fila : tabla) {
				dtm.addRow(fila);
			}
		}
	}
	
	private void ponerVuelo(int row, VueloCompleto vuc) {
		Vector<Object> filaData = new Vector<>();
		filaData.add(vuc);
		filaData.add(vuc.getCapacidad());
		filaData.add(vuc.getPrecio());
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
