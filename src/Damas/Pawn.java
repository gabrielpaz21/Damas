package Damas;

import java.util.ArrayList;
import java.util.Objects;

public class Pawn extends Piece {


    public Pawn(int i, int j, String color, String player) {
        super(i, j, color, player);
    }

    public void showFeatures() {
        System.out.println("(He is a Pawn) it is in the position [" + i + "];" + "[" + j + "] , the color is " + color + " and the player is " + player);
    }

    public void move(int x, int y) {
        Pawn pawn = new Pawn(x, y, color, player);
        Board.adjust(pawn);
        Board.cancel(Board.getPieza(i, j));
    }

    public boolean canMove() {
        boolean aux = false;

        if (j == 0 && Objects.equals(color, "roja") && Board.getPieza(i + 1, j + 1) == null) {
            aux = true;
        } else if (j == 0 && Objects.equals(color, "negra") && Board.getPieza(i - 1, j + 1) == null) {
            aux = true;
        } else if (j == 7 && Objects.equals(color, "roja") && Board.getPieza(i + 1, j - 1) == null) {
            aux = true;
        } else if (j == 7 && Objects.equals(color, "negra") && Board.getPieza(i - 1, j - 1) == null) {
            aux = true;
        } else if (j != 0 && j != 7 && Objects.equals(color, "roja")
                && (Board.getPieza(i + 1, j - 1) == null || Board.getPieza(i + 1, j + 1) == null)) {
            aux = true;
        } else if (j != 0 && j != 7 && Objects.equals(color, "negra")
                && (Board.getPieza(i - 1, j - 1) == null || Board.getPieza(i - 1, j + 1) == null)) {
            aux = true;
        }
        return aux;
    }

    public boolean canEat() {
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

    public ArrayList<Integer> potionsToMove() {
        ArrayList<Integer> positions = new ArrayList<>();

        if (j == 0 && Objects.equals(color, "roja") && Board.getPieza(i + 1, j + 1) == null) {
            positions.add(i + 1);
            positions.add(j + 1);
        }

        if (j == 0 && Objects.equals(color, "negra") && Board.getPieza(i - 1, j + 1) == null) {
            positions.add(i - 1);
            positions.add(j + 1);
        }

        if (j == 7 && Objects.equals(color, "roja") && Board.getPieza(i + 1, j - 1) == null) {
            positions.add(i + 1);
            positions.add(j - 1);
        }

        if (j == 7 && Objects.equals(color, "negra") && Board.getPieza(i - 1, j - 1) == null) {
            positions.add(i - 1);
            positions.add(j - 1);
        }

        if (j != 0 && j != 7 && Objects.equals(color, "roja") && Board.getPieza(i + 1, j - 1) == null) { //TODO resolver bug
            positions.add(i + 1);
            positions.add(j - 1);
        }

        if (j != 0 && j != 7 && Objects.equals(color, "roja") && Board.getPieza(i + 1, j + 1) == null) {
            positions.add(i + 1);
            positions.add(j + 1);
        }

        if (j != 0 && j != 7 && Objects.equals(color, "negra") && Board.getPieza(i - 1, j - 1) == null) {
            positions.add(i - 1);
            positions.add(j - 1);
        }

        if (j != 0 && j != 7 && Objects.equals(color, "negra") && Board.getPieza(i - 1, j + 1) == null) {
            positions.add(i - 1);
            positions.add(j + 1);
        }
        return positions;
    }

    public ArrayList<Integer> PotionsToEat() {
        ArrayList<Integer> potionsToEat = new ArrayList<>();

        if ((j == 0 || j == 1) && i != 6 && Objects.equals(color, "roja") && Board.getPieza(i + 1, j + 1) != null
                && Objects.equals(Board.getPieza(i + 1, j + 1).color, "negra") && Board.getPieza(i + 2, j + 2) == null) {
            potionsToEat.add(i + 2);
            potionsToEat.add(j + 2);
        }

        if (j != 0 && j != 1 && j != 6 && j != 7 && i != 6 && Objects.equals(color, "roja") && Board.getPieza(i + 1, j - 1) != null
                && Objects.equals(Board.getPieza(i + 1, j - 1).color, "negra") && Board.getPieza(i + 2, j - 2) == null) {
            potionsToEat.add(i + 2);
            potionsToEat.add(j - 2);
        }

        if (j != 0 && j != 1 && j != 6 && j != 7 && i != 6 && Objects.equals(color, "roja") && Board.getPieza(i + 1, j + 1) != null
                && Objects.equals(Board.getPieza(i + 1, j + 1).color, "negra") && Board.getPieza(i + 2, j + 2) == null) {
            potionsToEat.add(i + 2);
            potionsToEat.add(j + 2);
        }

        if ((j == 6 || j == 7) && i != 6 && Objects.equals(color, "roja") && Board.getPieza(i + 1, j - 1) != null
                && Objects.equals(Board.getPieza(i + 1, j - 1).color, "negra") && Board.getPieza(i + 2, j - 2) == null) {
            potionsToEat.add(i + 2);
            potionsToEat.add(j - 2);
        }

        if ((j == 0 || j == 1) && i != 1 && Objects.equals(color, "negra") && Board.getPieza(i - 1, j + 1) != null
                && Objects.equals(Board.getPieza(i - 1, j + 1).color, "roja") && Board.getPieza(i - 2, j + 2) == null) {
            potionsToEat.add(i - 2);
            potionsToEat.add(j + 2);
        }

        if (j != 0 && j != 1 && j != 6 && j != 7 && i != 1 && Objects.equals(color, "negra") && Board.getPieza(i - 1, j - 1) != null
                && Objects.equals(Board.getPieza(i - 1, j - 1).color, "roja") && Board.getPieza(i - 2, j - 2) == null) {
            potionsToEat.add(i - 2);
            potionsToEat.add(j - 2);

        }

        if (j != 0 && j != 1 && j != 6 && j != 7 && i != 1 && Objects.equals(color, "negra") && Board.getPieza(i - 1, j + 1) != null
                && Objects.equals(Board.getPieza(i - 1, j + 1).color, "roja") && Board.getPieza(i - 2, j + 2) == null) {
            potionsToEat.add(i - 2);
            potionsToEat.add(j + 2);
        }

        if ((j == 6 || j == 7) && i != 1 && Objects.equals(color, "negra") && Board.getPieza(i - 1, j - 1) != null
                && Objects.equals(Board.getPieza(i - 1, j - 1).color, "roja") && Board.getPieza(i - 2, j - 2) == null) {
            potionsToEat.add(i - 2);
            potionsToEat.add(j - 2);
        }

        return potionsToEat;
    }

    public ArrayList<Integer> piecesMeals() {
        ArrayList<Integer> potionsMeals = new ArrayList<>();

        if ((j == 0 || j == 1) && i != 6 && Objects.equals(color, "roja") && Board.getPieza(i + 1, j + 1) != null
                && Objects.equals(Board.getPieza(i + 1, j + 1).color, "negra") && Board.getPieza(i + 2, j + 2) == null) {
            potionsMeals.add(i + 1);
            potionsMeals.add(j + 1);
        }

        if (j != 0 && j != 1 && j != 6 && j != 7 && i != 6 && Objects.equals(color, "roja") && Board.getPieza(i + 1, j - 1) != null
                && Objects.equals(Board.getPieza(i + 1, j - 1).color, "negra") && Board.getPieza(i + 2, j - 2) == null) {
            potionsMeals.add(i + 1);
            potionsMeals.add(j - 1);
        }

        if (j != 0 && j != 1 && j != 6 && j != 7 && i != 6 && Objects.equals(color, "roja") && Board.getPieza(i + 1, j + 1) != null
                && Objects.equals(Board.getPieza(i + 1, j + 1).color, "negra") && Board.getPieza(i + 2, j + 2) == null) {
            potionsMeals.add(i + 1);
            potionsMeals.add(j + 1);
        }

        if ((j == 6 || j == 7) && i != 6 && Objects.equals(color, "roja") && Board.getPieza(i + 1, j - 1) != null
                && Objects.equals(Board.getPieza(i + 1, j - 1).color, "negra") && Board.getPieza(i + 2, j - 2) == null) {
            potionsMeals.add(i + 1);
            potionsMeals.add(j - 1);
        }

        if ((j == 0 || j == 1) && i != 1 && Objects.equals(color, "negra") && Board.getPieza(i - 1, j + 1) != null
                && Objects.equals(Board.getPieza(i - 1, j + 1).color, "roja") && Board.getPieza(i - 2, j + 2) == null) {
            potionsMeals.add(i - 1);
            potionsMeals.add(j + 1);
        }

        if (j != 0 && j != 1 && j != 6 && j != 7 && i != 1 && Objects.equals(color, "negra") && Board.getPieza(i - 1, j - 1) != null
                && Objects.equals(Board.getPieza(i - 1, j - 1).color, "roja") && Board.getPieza(i - 2, j - 2) == null) {
            potionsMeals.add(i - 1);
            potionsMeals.add(j - 1);

        }

        if (j != 0 && j != 1 && j != 6 && j != 7 && i != 1 && Objects.equals(color, "negra") && Board.getPieza(i - 1, j + 1) != null
                && Objects.equals(Board.getPieza(i - 1, j + 1).color, "roja") && Board.getPieza(i - 2, j + 2) == null) {
            potionsMeals.add(i - 1);
            potionsMeals.add(j + 1);
        }

        if ((j == 6 || j == 7) && i != 1 && Objects.equals(color, "negra") && Board.getPieza(i - 1, j - 1) != null
                && Objects.equals(Board.getPieza(i - 1, j - 1).color, "roja") && Board.getPieza(i - 2, j - 2) == null) {
            potionsMeals.add(i - 1);
            potionsMeals.add(j - 1);
        }

        return potionsMeals;
    }

    private boolean PBI() {
        return (j == 0 || j == 1) && i != 6 && Objects.equals(color, "roja") && Board.getPieza(i + 1, j + 1) != null
                && Objects.equals(Board.getPieza(i + 1, j + 1).color, "negra") && Board.getPieza(i + 2, j + 2) == null;
    }

    private boolean PBC() {
        return j != 0 && j != 1 && j != 6 && j != 7 && i != 6 && Objects.equals(color, "roja")
                && ((Board.getPieza(i + 1, j - 1) != null && Objects.equals(Board.getPieza(i + 1, j - 1).color, "negra")
                && Board.getPieza(i + 2, j - 2) == null)
                || (Board.getPieza(i + 1, j + 1) != null && Objects.equals(Board.getPieza(i + 1, j + 1).color, "negra")
                && Board.getPieza(i + 2, j + 2) == null));
    }

    private boolean PBD() {
        return (j == 6 || j == 7) && i != 6 && Objects.equals(color, "roja") && Board.getPieza(i + 1, j - 1) != null
                && Objects.equals(Board.getPieza(i + 1, j - 1).color, "negra") && Board.getPieza(i + 2, j - 2) == null;
    }

    private boolean PNI() {
        return (j == 0 || j == 1) && i != 1 && Objects.equals(color, "negra") && Board.getPieza(i - 1, j + 1) != null
                && Objects.equals(Board.getPieza(i - 1, j + 1).color, "roja") && Board.getPieza(i - 2, j + 2) == null;
    }

    private boolean PNC() {
        return j != 0 && j != 1 && j != 6 && j != 7 && i != 1 && Objects.equals(color, "negra")
                && ((Board.getPieza(i - 1, j - 1) != null && Objects.equals(Board.getPieza(i - 1, j - 1).color, "roja")
                && Board.getPieza(i - 2, j - 2) == null)
                || (Board.getPieza(i - 1, j + 1) != null && Objects.equals(Board.getPieza(i - 1, j + 1).color, "roja")
                && Board.getPieza(i - 2, j + 2) == null));
    }

    private boolean PND() {
        return (j == 6 || j == 7) && i != 1 && Objects.equals(color, "negra") && Board.getPieza(i - 1, j - 1) != null
                && Objects.equals(Board.getPieza(i - 1, j - 1).color, "roja") && Board.getPieza(i - 2, j - 2) == null;
    }

}
