package view;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.Aviones;
import model.Avion;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TbAviones extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -371381706871909469L;
	private String filtro;
	
	public TbAviones(String fil) {
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
						Avion av;
						valorFinal = tm.getValueAt(fila,columna);
						tm.removeTableModelListener(this);
						av = (Avion) tm.getValueAt(getEditingRow(), 0);
						switch (columna) {
						case 1:
							av.setModelo(((String) valorFinal).trim());
							break;
						case 2:
							av.setCapacidad((Integer) valorFinal);
							break;
						default:
							break;
						}
						ponerAvion(getEditingRow(), av);
						Integer respuesta = new Aviones().grabar(av);
						if (respuesta==null) {
							actualizarTabla(filtro);
						}
					}
				});
				//System.out.println(txtInicial + " se ha cambiado por " + txtFinal);
			}
		});
		setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"Avion", "Modelo", "Capacidad"
			}
		) {
			Class[] columnTypes = new Class[] {
				Avion.class, String.class, Integer.class
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
		ArrayList<Vector<Object>> tabla = new Aviones().recuperaTablaAviones(filtro);
		if (tabla!=null) {
			DefaultTableModel dtm = (DefaultTableModel) getModel();
			dtm.setRowCount(0);
			for (Vector<Object> fila : tabla) {
				dtm.addRow(fila);
			}
		}
	}
	
	private void ponerAvion(int row, Avion av) {
		Vector<Object> filaData = new Vector<>();
		filaData.add(av);
		filaData.add(av.getModelo());
		filaData.add(av.getCapacidad());
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
