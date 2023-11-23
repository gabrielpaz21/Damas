package Damas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

class Panel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int BOARD_WIDTH = Board.BOARD_WIDTH;
    private static final int BOARD_HEIGHT = Board.BOARD_HEIGHT;
    private Button buttonSelected;
    private boolean turn = false;
    private ArrayList<Integer> positionsToMove = new ArrayList<>(),
            potionsToEat = new ArrayList<>(),
            potionsMeals = new ArrayList<>();

    private final static String rutaBase = "static";

    private final ImageIcon whiteSpaceImage = getImage(rutaBase + "/whiteSpace.png");

    private final ImageIcon blackSpaceImage = getImage(rutaBase + "/blackSpace.png");

    private final ImageIcon greenSpaceImage = getImage(rutaBase + "/greenSpace.png");

    private final ImageIcon redPawnImage = getImage(rutaBase + "/redPawn.png");

    private final ImageIcon redPawnImageSelected = getImage(rutaBase + "/redPawnSelected.png");

    private final ImageIcon blackPawnImage = getImage(rutaBase + "/blackPawn.png");

    private final ImageIcon blackPawnImageSelected = getImage(rutaBase + "/blackPawnSelected.png");


    public Panel() {
        setLayout(new GridBagLayout());
        addButtons();
        Board.getInstance();
    }

    public GridBagConstraints attributeButton(int i, int j) {

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = j;
        gbc.gridy = i;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        return gbc;
    }

    private Button getButton(int i, int j) {

        int aux = 0;
        Button button = null;
        boolean pass = true;

        for (int x = 0; x < BOARD_WIDTH && pass; x++) {
            for (int y = 0; y < BOARD_HEIGHT && pass; y++) {

                if (x == i && y == j) {
                    button = (Button) getComponent(aux);
                    pass = false;
                }
                aux += 1;
            }
        } //TODO Use the break operator

        return button;
    }

    public static ImageIcon getImage(String nameImage) {
        return new ImageIcon(nameImage);
    }

    public void addButtons() {
        int aux;
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {

                GridBagConstraints gbc = attributeButton(i, j);
                aux = i + j;

                if (aux % 2 == 0) {

                    add(new Button(gbc, whiteSpaceImage), gbc);

                }

                if (aux % 2 != 0 && (i == 0 || i == 1 || i == 2)) {

                    add(new Button(gbc, redPawnImage), gbc);

                }

                if (aux % 2 != 0 && (i == 5 || i == 6 || i == 7)) {

                    add(new Button(gbc, blackPawnImage), gbc);

                }

                if (aux % 2 != 0 && (i == 3 || i == 4)) {

                    add(new Button(gbc, blackSpaceImage), gbc);

                }
            }
        }
    }

    public boolean validateButton(Button button) {
        boolean validation = true;
        int coordinatesAdded = button.getI() + button.getJ();
        final boolean isEven = coordinatesAdded % 2 == 0;
        final boolean isNull = Board.getPiece(button.getI(), button.getJ()) == null;


        if (isEven) {
            validation = false;
        }

        if (!isEven && isNull) {
            validation = false;
        }

        return !validation;
    }

    public void removeListeners() {

        for (int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++) {
                Button button = getButton(x, y);
                button.removeActionListener(button);
            }
        }
    }

    public void changeImages(ImageIcon image, ArrayList<Integer> positions) {

        for (int x = 0; x < positions.size(); x += 2) {
            Button button = getButton(positions.get(x), positions.get(x + 1));
            button.setIcon(new ImageIcon(image.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));
        }

    }


    private class Button extends JButton implements FocusListener, ActionListener {

        private static final long serialVersionUID = 1L;

        private final int i;
        private final int j;

        public Button(GridBagConstraints gbc, ImageIcon image) {
            this.i = gbc.gridy;
            this.j = gbc.gridx;
            setIcon(new ImageIcon(image.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));
            addFocusListener(this);
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        public void updateButton(Button button, ImageIcon image, Button playedButton) {
            if (button != playedButton) {
                button.setIcon(new ImageIcon(image.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));
                button.removeActionListener(button);
            } else {
                removeActionListener(playedButton);
                addFocusListener(playedButton);
            }
        }

        public void updateButtons(ArrayList<Integer> positions, ImageIcon image, Button playedButton) {
            for (int x = 0; x < positions.size(); x += 2) {
                Button button = getButton(positions.get(x), positions.get(x + 1));
                updateButton(button, image, playedButton);
            }
        }

        public void updateButtons(ArrayList<Integer> positions, ImageIcon image) {
            for (int x = 0; x < positions.size(); x += 2) {
                Button button = getButton(positions.get(x), positions.get(x + 1));
                button.setIcon(new ImageIcon(image.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));
                button.removeFocusListener(button);
                button.addActionListener(button);
            }
        }

        @Override
        public void focusGained(FocusEvent e) {

            removeListeners();

            buttonSelected = (Button) e.getSource();

            if (validateButton(buttonSelected)) {
                return;
            }

            Piece pieceSelected = Board.getPiece(buttonSelected.getI(), buttonSelected.getJ());

            if (Board.checkShiftAlignment(turn, pieceSelected.getColor())) {
                return;
            }

            if (!turn && Board.canEat("roja") && !pieceSelected.canEat() || turn && Board.canEat("negra") && !pieceSelected.canEat()) {
                return;
            }


            if (turn) {
                buttonSelected.setIcon(new ImageIcon(blackPawnImageSelected.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));
            } else {
                buttonSelected.setIcon(new ImageIcon(redPawnImageSelected.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));
            }

            potionsMeals = pieceSelected.piecesMeals();
            positionsToMove = pieceSelected.potionsToMove();
            potionsToEat = pieceSelected.PotionsToEat();

            if (pieceSelected.canEat()) {

                for (int x = 0; x < potionsToEat.size(); x += 2) {
                    Button button = getButton(potionsToEat.get(x), potionsToEat.get(x + 1));
                    button.setIcon(new ImageIcon(greenSpaceImage.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));
                    button.removeFocusListener(button);
                    button.addActionListener(button);

                }

            } else if (pieceSelected.canMove()) {

                for (int x = 0; x < positionsToMove.size(); x += 2) {
                    Button button = getButton(positionsToMove.get(x), positionsToMove.get(x + 1));
                    button.setIcon(new ImageIcon(greenSpaceImage.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));
                    button.removeFocusListener(button);
                    button.addActionListener(button);

                }
            }

        }

        @Override
        public void focusLost(FocusEvent e) {

            buttonSelected = (Button) e.getSource();

            if (validateButton(buttonSelected)) {
                return;
            }

            Piece pieceDeselected = Board.getPiece(buttonSelected.getI(), buttonSelected.getJ());

            if (Board.checkShiftAlignment(turn, pieceDeselected.getColor())) {

                return;
            }

            if (turn) {
                buttonSelected.setIcon(new ImageIcon(blackPawnImage.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));
            } else {
                buttonSelected.setIcon(new ImageIcon(redPawnImage.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));
            }

            positionsToMove = pieceDeselected.potionsToMove();

            changeImages(blackSpaceImage, positionsToMove);

            potionsToEat = pieceDeselected.PotionsToEat();

            changeImages(blackSpaceImage, potionsToEat);

        }

        @Override
        public void actionPerformed(ActionEvent e) {

            Button playedButton = (Button) e.getSource();
            Piece pieceSelected = Board.getPiece(buttonSelected.getI(), buttonSelected.getJ());
            positionsToMove = pieceSelected.potionsToMove();
            potionsToEat = pieceSelected.PotionsToEat();

            pieceSelected.move(playedButton.getI(), playedButton.getJ());

            if (pieceSelected.getColor().equals("roja")) {
                turn = true;
            } else if (pieceSelected.getColor().equals("negra")) {
                turn = false;
            }


            ImageIcon imageButtonSelected = (ImageIcon) buttonSelected.getIcon();

            playedButton.setIcon(new ImageIcon(imageButtonSelected.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));

            buttonSelected.setIcon(new ImageIcon(blackSpaceImage.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));

            updateButtons(positionsToMove, blackSpaceImage, playedButton);

            updateButtons(potionsToEat, blackSpaceImage, playedButton);

            for (int x = 0; x < potionsMeals.size(); x += 2) {
                Button button = getButton(potionsMeals.get(x), potionsMeals.get(x + 1));
                button.setIcon(new ImageIcon(blackSpaceImage.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));
                button.removeFocusListener(button);
                Board.cancel(Board.getPiece(potionsMeals.get(x), potionsMeals.get(x + 1)));
            }

        }

    }

}
