package Damas;

import java.util.ArrayList;

public class Dama extends Pieza {

	
	public Dama(int i, int j, String color, String jugador) {
		super(i, j, color, jugador);
	}

	public void mostrarCaracteristicas() {
		System.out.println("(Es una Dama) esta en la posicion [" + i + "];" + "[" + j + "] , el color es " + color + " y el jugador es " + jugador);
	}

	public void mover(int x, int y) {
		Dama dama = new Dama(x, y, color, jugador);
		Tablero.ajustar(dama);
		Tablero.anular(Tablero.getPieza(i, j));
	}

	public boolean puedeMover() {
		boolean aux = false;
		if (i == 0 && j == 7 && Tablero.getPieza(1, 6) == null) {
			aux = true;
		} else if (i == 7 && j == 0 && Tablero.getPieza(6, 1) == null) {
			aux = true;
		} else if (i == 0 && j != 7
				&& (Tablero.getPieza(i + 1, j + 1) == null || Tablero.getPieza(i + 1, j - 1) == null)) {
			aux = true;
		} else if (i == 7 && j != 0
				&& (Tablero.getPieza(i - 1, j + 1) == null || Tablero.getPieza(i - 1, j - 1) == null)) {
			aux = true;
		} else if (j == 0 && i != 7
				&& (Tablero.getPieza(i - 1, j + 1) == null || Tablero.getPieza(i + 1, j + 1) == null)) {
			aux = true;
		} else if (j == 7 && i != 0
				&& (Tablero.getPieza(i - 1, j - 1) == null || Tablero.getPieza(i + 1, j - 1) == null)) {
			aux = true;
		} else if (i != 0 && i != 7 && j != 0 && j != 7
				&& (Tablero.getPieza(i - 1, j - 1) == null || Tablero.getPieza(i - 1, j + 1) == null
						|| Tablero.getPieza(i + 1, j - 1) == null || Tablero.getPieza(i + 1, j + 1) == null)) {
			aux = true;
		}
		return aux;
	}

	public boolean puedeComer() {
		boolean aux = false;
		int i = getI();
		int j = getJ();
		if (i == 0 && j == 7) {
			while ((i != 7 || j != 0) && aux == false) {
				if (Tablero.getPieza(i + 1, j - 1) != null) {

					aux = true;
				}
				i++;
				j++;
			}
		} else if (getI() == 7 && getJ() == 0 && Tablero.getPieza(6, 1) == null) {
			aux = true;
		} else if (getI() == 0 && getJ() != 7 && (Tablero.getPieza(getI() + 1, getJ() + 1) == null
				|| Tablero.getPieza(getI() + 1, getJ() - 1) == null)) {
			aux = true;
		} else if (getI() == 7 && getJ() != 0 && (Tablero.getPieza(getI() - 1, getJ() + 1) == null
				|| Tablero.getPieza(getI() - 1, getJ() - 1) == null)) {
			aux = true;
		} else if (getJ() == 0 && getI() != 7 && (Tablero.getPieza(getI() - 1, getJ() + 1) == null
				|| Tablero.getPieza(getI() + 1, getJ() + 1) == null)) {
			aux = true;
		} else if (getJ() == 7 && getI() != 0 && (Tablero.getPieza(getI() - 1, getJ() - 1) == null
				|| Tablero.getPieza(getI() + 1, getJ() - 1) == null)) {
			aux = true;
		} else if (getI() != 0 && getI() != 7 && getJ() != 0 && getJ() != 7
				&& (Tablero.getPieza(getI() - 1, getJ() - 1) == null || Tablero.getPieza(getI() - 1, getJ() + 1) == null
						|| Tablero.getPieza(getI() + 1, getJ() - 1) == null
						|| Tablero.getPieza(getI() + 1, getJ() + 1) == null)) {
			aux = true;
		}
		return aux;
	}

	@Override
	public ArrayList<Integer> pocicionesParaMover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Integer> pocicionesParaComer() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<Integer> piezasComidas() {
		
		return null;
	}
	
}
