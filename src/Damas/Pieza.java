package Damas;

import java.util.ArrayList;

public abstract class Pieza {

	protected int i, j;
	
	protected String color, jugador;

	public Pieza(int i, int j, String color, String jugador) {

		this.i = i;
		this.j = j;
		this.color = color;
		this.jugador = jugador;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;

	}

	public void setJ(int j) {
		this.j = j;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getJugador() {
		return jugador;
	}

	public void setJugador(String jugador) {
		this.jugador = jugador;
	}
	
	public abstract void mostrarCaracteristicas();

	public abstract void mover(int i, int j);

	public abstract boolean puedeMover();
	
	public abstract boolean puedeComer();

	public abstract ArrayList<Integer> pocicionesParaMover();

	public abstract ArrayList<Integer> pocicionesParaComer();
	
	public abstract ArrayList<Integer> piezasComidas();



}