package Damas;

import java.util.ArrayList;

public abstract class Piece {

	protected int i, j;
	
	protected String color, player;

	public Piece(int i, int j, String color, String player) {

		this.i = i;
		this.j = j;
		this.color = color;
		this.player = player;
	}

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;

	}

	public String getColor() {
		return color;
	}

	public abstract void showFeatures();

	public abstract void move(int i, int j);

	public abstract boolean canMove();
	
	public abstract boolean canEat();

	public abstract ArrayList<Integer> potionsToMove();

	public abstract ArrayList<Integer> PotionsToEat();
	
	public abstract ArrayList<Integer> piecesMeals();



}