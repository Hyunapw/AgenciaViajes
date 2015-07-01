package view;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.Destinos;
import model.Destino;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TbDestinos extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -371381706871909469L;
	private String filtro;
	
	public TbDestinos(String fil) {
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
						Destino des;
						valorFinal = tm.getValueAt(fila,columna);
						tm.removeTableModelListener(this);
						des = (Destino) tm.getValueAt(getEditingRow(), 0);
						switch (columna) {
						case 1:
							des.setLugar(((String) valorFinal).trim());
							break;
						default:
							break;
						}
						ponerDestino(getEditingRow(), des);
						Integer respuesta = new Destinos().grabar(des);
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
				"Destino", "Lugar"
			}
		) {
			Class[] columnTypes = new Class[] {
				Destino.class, String.class
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
		ArrayList<Vector<Object>> tabla = new Destinos().recuperaTablaDestinos(filtro);
		if (tabla!=null) {
			DefaultTableModel dtm = (DefaultTableModel) getModel();
			dtm.setRowCount(0);
			for (Vector<Object> fila : tabla) {
				dtm.addRow(fila);
			}
		}
	}
	
	private void ponerDestino(int row, Destino des) {
		Vector<Object> filaData = new Vector<>();
		filaData.add(des);
		filaData.add(des.getLugar());
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
