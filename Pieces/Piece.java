package Pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public interface Piece {

	Integer[] getPosition();

	ArrayList<Integer[]> legalMoves();

	String getName();

	void move(int x, int y);

	boolean isWhite();

	ImageView getImage();
}
