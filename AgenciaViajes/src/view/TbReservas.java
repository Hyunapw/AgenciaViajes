	package view;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.Reservas;
import model.Reserva;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TbReservas extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -371381706871909469L;
	private String filtro;
	
	public TbReservas(String fil) {
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
						Reserva vu;
						valorFinal = tm.getValueAt(fila,columna);
						tm.removeTableModelListener(this);
						vu = (Reserva) tm.getValueAt(getEditingRow(), 0);
						switch (columna) {
						case 1:
							vu.setAsiento(((Integer) valorFinal));
							break;
						default:
							break;
						}
						ponerReserva(getEditingRow(), vu);
						Integer respuesta = new Reservas().grabar(vu);
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
				"Reserva", "Asiento"
			}
		) {
			Class[] columnTypes = new Class[] {
				Reserva.class, Double.class
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
		ArrayList<Vector<Object>> tabla = new Reservas().recuperaTablaReservas(filtro);
		if (tabla!=null) {
			DefaultTableModel dtm = (DefaultTableModel) getModel();
			dtm.setRowCount(0);
			for (Vector<Object> fila : tabla) {
				dtm.addRow(fila);
			}
		}
	}
	
	private void ponerReserva(int row, Reserva vu) {
		Vector<Object> filaData = new Vector<>();
		filaData.add(vu);
		filaData.add(vu.getAsiento());
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
