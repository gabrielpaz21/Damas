package Damas;

import java.util.ArrayList;

public class Queen extends Piece {

	
	public Queen(int i, int j, String color, String player) {
		super(i, j, color, player);
	}

	/**
	public void showFeatures() {
		System.out.println("(It's a Queen) it's in the position [" + i + "];" + "[" + j + "] , the color is " + color + " and the player is " + player);
	}*/

	public void move(int x, int y) {
		Queen queen = new Queen(x, y, color, player);
		Board.adjust(queen);
		Board.cancel(Board.getPiece(i, j));
	}

	public boolean canMove() {
		boolean aux = false;
		if (i == 0 && j == 7 && Board.getPiece(1, 6) == null) {
			aux = true;
		} else if (i == 7 && j == 0 && Board.getPiece(6, 1) == null) {
			aux = true;
		} else if (i == 0 && j != 7
				&& (Board.getPiece(i + 1, j + 1) == null || Board.getPiece(i + 1, j - 1) == null)) {
			aux = true;
		} else if (i == 7 && j != 0
				&& (Board.getPiece(i - 1, j + 1) == null || Board.getPiece(i - 1, j - 1) == null)) {
			aux = true;
		} else if (j == 0 && i != 7
				&& (Board.getPiece(i - 1, j + 1) == null || Board.getPiece(i + 1, j + 1) == null)) {
			aux = true;
		} else if (j == 7 && i != 0
				&& (Board.getPiece(i - 1, j - 1) == null || Board.getPiece(i + 1, j - 1) == null)) {
			aux = true;
		} else if (i != 0 && i != 7 && j != 0 && j != 7
				&& (Board.getPiece(i - 1, j - 1) == null || Board.getPiece(i - 1, j + 1) == null
						|| Board.getPiece(i + 1, j - 1) == null || Board.getPiece(i + 1, j + 1) == null)) {
			aux = true;
		}
		return aux;
	}

	public boolean canEat() {
		boolean aux = false;
		int i = getI();
		int j = getJ();
		if (i == 0 && j == 7) {
			while ((i != 7 || j != 0) && !aux) {
				if (Board.getPiece(i + 1, j - 1) != null) {

					aux = true;
				}
				i++;
				j++;
			}
		} else if (getI() == 7 && getJ() == 0 && Board.getPiece(6, 1) == null) {
			aux = true;
		} else if (getI() == 0 && getJ() != 7 && (Board.getPiece(getI() + 1, getJ() + 1) == null
				|| Board.getPiece(getI() + 1, getJ() - 1) == null)) {
			aux = true;
		} else if (getI() == 7 && getJ() != 0 && (Board.getPiece(getI() - 1, getJ() + 1) == null
				|| Board.getPiece(getI() - 1, getJ() - 1) == null)) {
			aux = true;
		} else if (getJ() == 0 && getI() != 7 && (Board.getPiece(getI() - 1, getJ() + 1) == null
				|| Board.getPiece(getI() + 1, getJ() + 1) == null)) {
			aux = true;
		} else if (getJ() == 7 && getI() != 0 && (Board.getPiece(getI() - 1, getJ() - 1) == null
				|| Board.getPiece(getI() + 1, getJ() - 1) == null)) {
			aux = true;
		} else if (getI() != 0 && getI() != 7 && getJ() != 0 && getJ() != 7
				&& (Board.getPiece(getI() - 1, getJ() - 1) == null || Board.getPiece(getI() - 1, getJ() + 1) == null
						|| Board.getPiece(getI() + 1, getJ() - 1) == null
						|| Board.getPiece(getI() + 1, getJ() + 1) == null)) {
			aux = true;
		}
		return aux;
	}

	@Override
	public ArrayList<Integer> potionsToMove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Integer> PotionsToEat() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<Integer> piecesMeals() {
		
		return null;
	}
	
}
