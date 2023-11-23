package Damas;

import java.util.Objects;

public class Board {

	private static final Piece[][] board = new Piece[8][8];
	
	public static final int BOARD_WIDTH = board[0].length;
	
	public static final int BOARD_HEIGHT = board[1].length;

	private static Board instance = null;

	private Board() {
		int aux;

		// Entering the pawns to the board (matrix)
		for (int i = 0; i < BOARD_WIDTH; i++) {
			for (int j = 0; j < BOARD_HEIGHT; j++) {
				aux = i + j;
				if (aux % 2 != 0 && (i == 0 || i == 1 || i == 2)) {
					board[i][j] = new Pawn(i, j, "roja", "jugadorBlancas");
				}

				if (aux % 2 != 0 && (i == 5 || i == 6 || i == 7)) {
					board[i][j] = new Pawn(i, j, "negra", "jugadorNegras");
				}
			}
		}
	}

	public static Board getInstance() {
		if (instance == null) {
			instance = new Board();
		}
		return instance;
	}

	public static Piece getPiece(int positionI, int positionJ) {
		return board[positionI][positionJ];
	}

    public static boolean canEat(String ColorPiece) {

		boolean aux = false;

		for (int i = 0; i < BOARD_WIDTH && !aux; i++) {
			for (int j = 0; j < BOARD_HEIGHT && !aux; j++) {
				Piece piece = getPiece(i, j);
				if (piece != null && Objects.equals(piece.getColor(), ColorPiece) && piece.canEat()) { //TODO use break
					aux = true;
				}
			}
		}

		return aux;
	}
	
	public static boolean checkShiftAlignment(boolean turn, String color) {
		return (turn || !color.equals("roja")) && (!turn || !color.equals("negra"));
	}

	public static void adjust(Piece piece) {
		board[piece.getI()][piece.getJ()] = piece;
	}

	public static void cancel(Piece piece) {
		board[piece.getI()][piece.getJ()] = null;
	}

}
