package view;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.Clientes;
import model.Cliente;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TbClientes extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -371381706871909469L;
	private String filtro;
	
	public TbClientes(String fil) {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				System.out.println();
			}
		});
		this.filtro = fil;	
		addContainerListener(new ContainerAdapter() {
			String txtInicial = "";
			String txtFinal = "";
			@Override
			public void componentAdded(ContainerEvent ce) {
				txtInicial = ((JTextField) ce.getChild()).getText();
				TableModel tm = ((JTable) ce.getComponent()).getModel();
				tm.addTableModelListener(new TableModelListener() {
					@Override
					public void tableChanged(TableModelEvent tme) {
						int fila = getEditingRow();
						int columna = getEditingColumn();
						txtFinal = (String) tm.getValueAt(fila,columna);
						System.out.println(txtInicial + " se ha cambiado por " + txtFinal);
						tm.removeTableModelListener(this);
						Cliente cli = (Cliente) tm.getValueAt(getEditingRow(), 0);
						cli.setNombre(txtFinal.trim());
						ponerCliente(getEditingRow(), cli);
						Integer respuesta = new Clientes().grabar(cli);
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
				{null, null},
			},
			new String[] {
				"Cliente", "Nombre"
			}
		) {
			Class[] columnTypes = new Class[] {
				Cliente.class, String.class
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
		ArrayList<Vector<Object>> tabla = new Clientes().recuperaTablaClientes(filtro);
		if (tabla!=null) {
			DefaultTableModel dtm = (DefaultTableModel) getModel();
			dtm.setRowCount(0);
			for (Vector<Object> fila : tabla) {
				dtm.addRow(fila);
			}
		}
	}
	
	private void ponerCliente(int row, Cliente cli) {
		Vector<Object> filaData = new Vector<>();
		filaData.add(cli);
		filaData.add(cli.getNombre());
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
