package asientos;

import javax.swing.JPanel;

import model.Asiento;
import view.BtAsiento;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;

public class GrupoAsientos extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Es una matriz de Botones de asientos
	private int filas = 1;
	private int columnas = 1;
	public static final String OCUPADOS = "ocupados";
	public static final String LIBRES = "libres";
	public static final String NODISPONIBLES = "nodisponibles";
	
	/**
	 * @wbp.parser.constructor
	 */
	
	
	public GrupoAsientos(Dimension dimension) {
		if (dimension!=null) {
			this.columnas = (int) (dimension.getWidth()/16);
			this.columnas = this.columnas<=0?1:this.columnas;
			this.filas = (int) (dimension.getHeight()/16);
			this.filas = this.filas<=0?1:this.filas;
			inicia();
			creaGrupo();
		}
	}
	
	
	public GrupoAsientos(ArrayList<Asiento> list, int columns, int rows) {
		if (list!=null && rows>=0 && columns>=0) {
			this.filas = rows;
			this.columnas = columns;
			inicia();
			ponerGrupo(list);
		}
	}

	public GrupoAsientos(int columns, int rows) {
		if (rows>=0 && columns>=0) {
			this.columnas = columns;
			this.filas = rows;
			inicia();
			creaGrupo();
		}
	}
	
	/**Obtiene 3 listas de Asientos: ocupados, libres y no disponibles.
	 * @return Devuelve un HashMap de 3 ArrayLists de Asientos: "ocupados", "libres" y "nodisponibles".
	 */
	public HashMap<String, ArrayList<Asiento>> obtenerPorGrupos() {
		HashMap<String, ArrayList<Asiento>> salida = new HashMap<String,ArrayList<Asiento>>();
		ArrayList<Asiento> ocupados = new ArrayList<Asiento>();
		ArrayList<Asiento> libres = new ArrayList<Asiento>();
		ArrayList<Asiento> nodisponibles = new ArrayList<Asiento>();
		Component[] cs = getComponents();
		for (Component c : cs) {
			if ( c.getClass() == BtAsiento.class ) {
				Asiento a = ((BtAsiento)c).getAsiento();
				if (a.getTipo()>0) {
					if (a.isOcupado()) {
						ocupados.add(a);
					} else {
						libres.add(a);
					}
				} else {
					nodisponibles.add(a);
				}
			}
		}
		salida.put(OCUPADOS, ocupados);
		salida.put(LIBRES, libres);
		salida.put(NODISPONIBLES, nodisponibles);
		return salida;
	}
	
	/**Obtiene una lista de los Asientos, excluyendo los de tipo 0, (NODISPONIBLES).
	 * @return Devuelve una ArrayList de Asientos.
	 */
	public ArrayList<Asiento> obtenerTodo() {
		ArrayList<Asiento> butacas = new ArrayList<Asiento>();
		Component[] cs = getComponents();
		for (Component c : cs) {
			if ( c.getClass() == BtAsiento.class ) {
				Asiento a = ((BtAsiento)c).getAsiento();
				if (a.getTipo()>0) {
					if (a.isOcupado()) {
						butacas.add(a);
					}
				}
			}
		}
		return butacas;
	}
	
	private void ponerGrupo(ArrayList<Asiento> lista) {
		for (Asiento asientoDato : lista) {
			BtAsiento asientoBtn = new BtAsiento(asientoDato);
			add(asientoBtn);
		}
	}
	
	private void creaGrupo() {
		int contador = 1;
		for (int i = 1; i < filas+1; i++) {
			for (int j = 1; j < columnas+1; j++) {
				BtAsiento asiento = new BtAsiento(contador, "F" + i + "C" + j, 1, false);
				add(asiento);
				contador++;
			}
		}
	}

	private void inicia(){
		setLayout(new GridLayout(filas, columnas, 0, 0));
		setSize(columnas*16, filas*16);
	}

	public int getFilas() {
		return filas;
	}


	public void setFilas(int filas) {
		this.filas = filas;
	}


	public int getColumnas() {
		return columnas;
	}


	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}

}
