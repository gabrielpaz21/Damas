package Damas;

import java.util.ArrayList;

public class Peon extends Pieza {


	public Peon(int i, int j, String color, String jugador) {
		super(i, j, color, jugador);
	}

	public void mostrarCaracteristicas() {
		System.out.println("(Es un Peon) esta en la posicion [" + i + "];" + "[" + j + "] , el color es " + color + " y el jugador es " + jugador);
	}
	
	public void mover(int x, int y) {
		Peon peon = new Peon(x, y, color, jugador);
		Tablero.ajustar(peon);
		Tablero.anular(Tablero.getPieza(i, j));
	}

	public boolean puedeMover() {
		boolean aux = false;

		if (j == 0 && color == "roja" && Tablero.getPieza(i + 1, j + 1) == null) {
			aux = true;
		} else if (j == 0 && color == "negra" && Tablero.getPieza(i - 1, j + 1) == null) {
			aux = true;
		} else if (j == 7 && color == "roja" && Tablero.getPieza(i + 1, j - 1) == null) {
			aux = true;
		} else if (j == 7 && color == "negra" && Tablero.getPieza(i - 1, j - 1) == null) {
			aux = true;
		} else if (j != 0 && j != 7 && color == "roja"
				&& (Tablero.getPieza(i + 1, j - 1) == null || Tablero.getPieza(i + 1, j + 1) == null)) {
			aux = true;
		} else if (j != 0 && j != 7 && color == "negra"
				&& (Tablero.getPieza(i - 1, j - 1) == null || Tablero.getPieza(i - 1, j + 1) == null)) {
			aux = true;
		}
		return aux;
	}
	
	public boolean puedeComer() {
		boolean aux = false;

		if (PBI()) {
			aux = true;
		} else if (PBC()) {
			aux = true;
		} else if (PBD()) {
			aux = true;
		} else if (PNI()) {
			aux = true;
		} else if (PNC()) {
			aux = true;
		} else if (PND()) {
			aux = true;
		}

		return aux;
	}

	public ArrayList<Integer> pocicionesParaMover() {
		ArrayList<Integer> pociciones = new ArrayList<Integer>();

		if (j == 0 && color == "roja" && Tablero.getPieza(i + 1, j + 1) == null) {
			pociciones.add(i + 1);
			pociciones.add(j + 1);
		}
		
		if (j == 0 && color == "negra" && Tablero.getPieza(i - 1, j + 1) == null) {
			pociciones.add(i - 1);
			pociciones.add(j + 1);
		}

		if (j == 7 && color == "roja" && Tablero.getPieza(i + 1, j - 1) == null) {
			pociciones.add(i + 1);
			pociciones.add(j - 1);
		}

		if (j == 7 && color == "negra" && Tablero.getPieza(i - 1, j - 1) == null) {
			pociciones.add(i - 1);
			pociciones.add(j - 1);
		}

		if (j != 0 && j != 7 && color == "roja" && Tablero.getPieza(i + 1, j - 1) == null) { //TODO resolver bug
			pociciones.add(i + 1);
			pociciones.add(j - 1);
		}

		if (j != 0 && j != 7 && color == "roja" && Tablero.getPieza(i + 1, j + 1) == null) {
			pociciones.add(i + 1);
			pociciones.add(j + 1);
		}
		
		if (j != 0 && j != 7 && color == "negra" && Tablero.getPieza(i - 1, j - 1) == null) {
			pociciones.add(i - 1);
			pociciones.add(j - 1);
		}
		
		if (j != 0 && j != 7 && color == "negra" && Tablero.getPieza(i - 1, j + 1) == null) {
			pociciones.add(i - 1);
			pociciones.add(j + 1);
		}
		return pociciones;
	}
	
	public ArrayList<Integer> pocicionesParaComer() {
		ArrayList<Integer> pocicionesComer = new ArrayList<Integer>();

		if ((j == 0 || j == 1) && i != 6 && color == "roja" && Tablero.getPieza(i + 1, j + 1) != null
				&& Tablero.getPieza(i + 1, j + 1).color == "negra" && Tablero.getPieza(i + 2, j + 2) == null) {
			pocicionesComer.add(i + 2);
			pocicionesComer.add(j + 2);
		}

		if (j != 0 && j != 1 && j != 6 && j != 7 && i != 6 && color == "roja" && Tablero.getPieza(i + 1, j - 1) != null
				&& Tablero.getPieza(i + 1, j - 1).color == "negra" && Tablero.getPieza(i + 2, j - 2) == null) {
			pocicionesComer.add(i + 2);
			pocicionesComer.add(j - 2);
		}

		if (j != 0 && j != 1 && j != 6 && j != 7 && i != 6 && color == "roja" && Tablero.getPieza(i + 1, j + 1) != null
				&& Tablero.getPieza(i + 1, j + 1).color == "negra" && Tablero.getPieza(i + 2, j + 2) == null) {
			pocicionesComer.add(i + 2);
			pocicionesComer.add(j + 2);
		}

		if ((j == 6 || j == 7) && i != 6 && color == "roja" && Tablero.getPieza(i + 1, j - 1) != null
				&& Tablero.getPieza(i + 1, j - 1).color == "negra" && Tablero.getPieza(i + 2, j - 2) == null) {
			pocicionesComer.add(i + 2);
			pocicionesComer.add(j - 2);
		}

		if ((j == 0 || j == 1) && i != 1 && color == "negra" && Tablero.getPieza(i - 1, j + 1) != null
				&& Tablero.getPieza(i - 1, j + 1).color == "roja" && Tablero.getPieza(i - 2, j + 2) == null) {
			pocicionesComer.add(i - 2);
			pocicionesComer.add(j + 2);
		}

		if (j != 0 && j != 1 && j != 6 && j != 7 && i != 1 && color == "negra" && Tablero.getPieza(i - 1, j - 1) != null
				&& Tablero.getPieza(i - 1, j - 1).color == "roja" && Tablero.getPieza(i - 2, j - 2) == null) {
			pocicionesComer.add(i - 2);
			pocicionesComer.add(j - 2);

		}

		if (j != 0 && j != 1 && j != 6 && j != 7 && i != 1 && color == "negra" && Tablero.getPieza(i - 1, j + 1) != null
				&& Tablero.getPieza(i - 1, j + 1).color == "roja" && Tablero.getPieza(i - 2, j + 2) == null) {
			pocicionesComer.add(i - 2);
			pocicionesComer.add(j + 2);
		}

		if ((j == 6 || j == 7) && i != 1 && color == "negra" && Tablero.getPieza(i - 1, j - 1) != null
				&& Tablero.getPieza(i - 1, j - 1).color == "roja" && Tablero.getPieza(i - 2, j - 2) == null) {
			pocicionesComer.add(i - 2);
			pocicionesComer.add(j - 2);
		}

		return pocicionesComer;
	}
	
	public ArrayList<Integer> piezasComidas() {
		ArrayList<Integer> pocicionesComidas = new ArrayList<Integer>();

		if ((j == 0 || j == 1) && i != 6 && color == "roja" && Tablero.getPieza(i + 1, j + 1) != null
				&& Tablero.getPieza(i + 1, j + 1).color == "negra" && Tablero.getPieza(i + 2, j + 2) == null) {
			pocicionesComidas.add(i + 1);
			pocicionesComidas.add(j + 1);
		}

		if (j != 0 && j != 1 && j != 6 && j != 7 && i != 6 && color == "roja" && Tablero.getPieza(i + 1, j - 1) != null
				&& Tablero.getPieza(i + 1, j - 1).color == "negra" && Tablero.getPieza(i + 2, j - 2) == null) {
			pocicionesComidas.add(i + 1);
			pocicionesComidas.add(j - 1);
		}

		if (j != 0 && j != 1 && j != 6 && j != 7 && i != 6 && color == "roja" && Tablero.getPieza(i + 1, j + 1) != null
				&& Tablero.getPieza(i + 1, j + 1).color == "negra" && Tablero.getPieza(i + 2, j + 2) == null) {
			pocicionesComidas.add(i + 1);
			pocicionesComidas.add(j + 1);
		}

		if ((j == 6 || j == 7) && i != 6 && color == "roja" && Tablero.getPieza(i + 1, j - 1) != null
				&& Tablero.getPieza(i + 1, j - 1).color == "negra" && Tablero.getPieza(i + 2, j - 2) == null) {
			pocicionesComidas.add(i + 1);
			pocicionesComidas.add(j - 1);
		}

		if ((j == 0 || j == 1) && i != 1 && color == "negra" && Tablero.getPieza(i - 1, j + 1) != null
				&& Tablero.getPieza(i - 1, j + 1).color == "roja" && Tablero.getPieza(i - 2, j + 2) == null) {
			pocicionesComidas.add(i - 1);
			pocicionesComidas.add(j + 1);
		}

		if (j != 0 && j != 1 && j != 6 && j != 7 && i != 1 && color == "negra" && Tablero.getPieza(i - 1, j - 1) != null
				&& Tablero.getPieza(i - 1, j - 1).color == "roja" && Tablero.getPieza(i - 2, j - 2) == null) {
			pocicionesComidas.add(i - 1);
			pocicionesComidas.add(j - 1);

		}

		if (j != 0 && j != 1 && j != 6 && j != 7 && i != 1 && color == "negra" && Tablero.getPieza(i - 1, j + 1) != null
				&& Tablero.getPieza(i - 1, j + 1).color == "roja" && Tablero.getPieza(i - 2, j + 2) == null) {
			pocicionesComidas.add(i - 1);
			pocicionesComidas.add(j + 1);
		}

		if ((j == 6 || j == 7) && i != 1 && color == "negra" && Tablero.getPieza(i - 1, j - 1) != null
				&& Tablero.getPieza(i - 1, j - 1).color == "roja" && Tablero.getPieza(i - 2, j - 2) == null) {
			pocicionesComidas.add(i - 1);
			pocicionesComidas.add(j - 1);
		}

		return pocicionesComidas;
	}

	private boolean PBI() {
		boolean aux = false;

		if ((j == 0 || j == 1) && i != 6 && color == "roja" && Tablero.getPieza(i + 1, j + 1) != null
				&& Tablero.getPieza(i + 1, j + 1).color == "negra" && Tablero.getPieza(i + 2, j + 2) == null) {
			aux = true;
		}
		return aux;
	}

	private boolean PBC() {
		boolean aux = false;

		if (j != 0 && j != 1 && j != 6 && j != 7 && i != 6 && color == "roja"
				&& ((Tablero.getPieza(i + 1, j - 1) != null && Tablero.getPieza(i + 1, j - 1).color == "negra"
						&& Tablero.getPieza(i + 2, j - 2) == null)
						|| (Tablero.getPieza(i + 1, j + 1) != null && Tablero.getPieza(i + 1, j + 1).color == "negra"
								&& Tablero.getPieza(i + 2, j + 2) == null))) {
			aux = true;
		}

		return aux;
	}

	private boolean PBD() {
		boolean aux = false;

		if ((j == 6 || j == 7) && i != 6 && color == "roja" && Tablero.getPieza(i + 1, j - 1) != null
				&& Tablero.getPieza(i + 1, j - 1).color == "negra" && Tablero.getPieza(i + 2, j - 2) == null) {
			aux = true;
		}
		return aux;
	}

	private boolean PNI() {
		boolean aux = false;

		if ((j == 0 || j == 1) && i != 1 && color == "negra" && Tablero.getPieza(i - 1, j + 1) != null
				&& Tablero.getPieza(i - 1, j + 1).color == "roja" && Tablero.getPieza(i - 2, j + 2) == null) {
			aux = true;
		}
		return aux;
	}

	private boolean PNC() {
		boolean aux = false;

		if (j != 0 && j != 1 && j != 6 && j != 7 && i != 1 && color == "negra"
				&& ((Tablero.getPieza(i - 1, j - 1) != null && Tablero.getPieza(i - 1, j - 1).color == "roja"
						&& Tablero.getPieza(i - 2, j - 2) == null)
						|| (Tablero.getPieza(i - 1, j + 1) != null && Tablero.getPieza(i - 1, j + 1).color == "roja"
								&& Tablero.getPieza(i - 2, j + 2) == null))) {
			aux = true;
		}
		return aux;
	}

	private boolean PND() {
		boolean aux = false;
		if ((j == 6 || j == 7) && i != 1 && color == "negra" && Tablero.getPieza(i - 1, j - 1) != null
				&& Tablero.getPieza(i - 1, j - 1).color == "roja" && Tablero.getPieza(i - 2, j - 2) == null) {
			aux = true;
		}
		return aux;
	}

}
