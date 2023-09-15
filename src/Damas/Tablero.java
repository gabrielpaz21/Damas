package Damas;

public class Tablero {

	private static Pieza[][] tablero = new Pieza[8][8];
	
	public static final int ANCHO_TABLERO = tablero[0].length;
	
	public static final int ALTO_TABLERO = tablero[1].length;

	public Tablero() {
		int aux;
		
		// Ingresando los peones a el tablero (matriz)
		for (int i = 0; i < ANCHO_TABLERO; i++) {
			for (int j = 0; j < ALTO_TABLERO; j++) {
				aux = i + j;
				if (aux % 2 != 0 && (i == 0 || i == 1 || i == 2)) {
					tablero[i][j] = new Peon(i, j, "roja", "jugadorBlancas");
				}

				if (aux % 2 != 0 && (i == 5 || i == 6 || i == 7)) {
					tablero[i][j] = new Peon(i, j, "negra", "jugadorNegras");
				}
			}
		}

	}

	public static Pieza getPieza(int posicionI, int posicionJ) {

		return tablero[posicionI][posicionJ];
	}

	public static boolean puedenMover(String piezaColor) {

		boolean aux = false;

		for (int i = 0; i < ANCHO_TABLERO && aux == false; i++) {
			for (int j = 0; j < ALTO_TABLERO && aux == false; j++) {
				Pieza p1 = getPieza(i, j);
				if (p1 != null && p1.getColor() == piezaColor && p1.puedeMover() == true) { //TODO utilizar break
					aux = true;
				}
			}
		}

		return aux;
	}
	
	public static boolean puedenComer(String piezaColor) {

		boolean aux = false;

		for (int i = 0; i < ANCHO_TABLERO && aux == false; i++) {
			for (int j = 0; j < ALTO_TABLERO && aux == false; j++) {
				Pieza pieza = getPieza(i, j);
				if (pieza != null && pieza.getColor() == piezaColor && pieza.puedeComer() == true) { //TODO utilizar break
					aux = true;
				}
			}
		}

		return aux;
	}
	
	public static boolean comprobarAlineacionDeTurnos(boolean turno, String color) {
		
		return turno!=true && color.equals("roja") || turno==true && color.equals("negra");
	}

	public static void ajustar(Pieza pieza) {
		
		tablero[pieza.getI()][pieza.getJ()] = pieza;
	}

	public static void anular(Pieza muestra) {
		
		tablero[muestra.getI()][muestra.getJ()] = null;
	}

}
