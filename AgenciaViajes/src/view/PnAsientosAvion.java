package view;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Avion;

public class PnAsientosAvion extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int filas = 1;
	private int columnas = 1;
	private int capacidad;
	private Avion avion;
	private BtAsiento[] asientos;

	public PnAsientosAvion(Avion av, ArrayList<Integer> listaAsientosOcupados) {
		avion = av;
		capacidad = avion.getCapacidad();
		calculaFilasYColumnas(capacidad, 6);
		System.out.println("F" + filas + ", C" + columnas);
		inicia();
		asientos = new BtAsiento[capacidad];
		agregaAsientosBtn(capacidad);
		String modelo = avion.getModelo();
		switch (modelo) {
		default:
			ponerOcupados(listaAsientosOcupados);
			break;
		}
	}
	
	private void calculaFilasYColumnas(int t, int c) {
		filas = (int) Math.ceil(t/6.0);
		columnas = c;
	}

	private void inicia(){
		//setLayout((new FlowLayout(FlowLayout.CENTER, 0, 0)));
		//setLayout(new GridLayout(filas, columnas, 0, 0));
		setLayout(null);
		setSize((columnas)*16, filas*16);
		System.out.println(getLayout());
		System.out.println(columnas*16 + ", " + filas*16);
		System.out.println(getSize());
	}

	private void agregaAsientosBtn(int n) {
		int resto = capacidad%6;
		int contador = 0;
		boolean ocupado = false;
//		for (int i = 1; i < n+1; i++) {
//			if (contador<capacidad){
//				asientos[contador] = new AsientoBtn(contador, "A"+contador, 1, ocupado);
//				add(asientos[contador]);
//			}
//			contador++;
//		}
		int i;
		for ( i = 1; i < filas; i++) {
			for (int j = 1; j < columnas+1; j++) {
				asientos[contador] = new BtAsiento(contador+1, "F" + i + "C" + j, 1, ocupado);
				asientos[contador].setBounds((j-1)*16, (i-1)*16, 16, 16);
				System.out.println(contador + ": " +  asientos[contador].getAsiento() + asientos[contador].getLocation());
				add(asientos[contador]);
				contador++;
			}
		}
		for (int k = 1; k < resto+1; k++) {
			asientos[contador] = new BtAsiento(contador+1, "Resto F" + i + "C" + k, 1, ocupado);
			asientos[contador].setBounds((k-1)*16, (i-1)*16, 16, 16);
			System.out.println(contador + ": " +  asientos[contador].getAsiento());
			add(asientos[contador]);
			contador++;
		}
	}
	
	private void ponerOcupados(ArrayList<Integer> listaAsientosOcupados) {
		if (listaAsientosOcupados!=null) {
			for (Integer integer : listaAsientosOcupados) {
				asientos[integer].setOcupado(true);
				asientos[integer].setTipo(0);
			}
		}
	}
	
	
}
