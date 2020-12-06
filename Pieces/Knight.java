package Pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Knight implements Piece {
	private Integer[] position;
	private boolean white;
	private static Image image_w = new Image("Pieces/Pictures/Knight_W.png");
	private static Image image_b = new Image("Pieces/Pictures/Knight_B.png");



	public Knight(int x, int y, boolean white){
		position = new Integer[2];
		position[0] = x;
		position[1] = y;
		this.white = white;
	}

	@Override
	public Integer[] getPosition() {
		return position;
	}

	@Override
	public ArrayList<Integer[]> legalMoves() {
		ArrayList<Integer[]> moves = new ArrayList<>();
		int x = position[0];
		int y = position[1];

		// Bishop-like moves
		boolean xp1 = x + 1 <= 7;
		boolean xp2 = x + 2 <= 7;

		boolean xn1 = x - 1 >= 0;
		boolean xn2 = x - 2 >= 0;

		boolean yp1 = y + 1 <= 7;
		boolean yp2 = y + 2 <= 7;

		boolean yn1 = y - 1 >= 0;
		boolean yn2 = y - 2 >= 0;


		if (xn1 && yp2) moves.add(new Integer[]{x-1, y+2});
		if (xp1 && yp2) moves.add(new Integer[]{x+1, y+2});

		if (xp2 && yp1) moves.add(new Integer[]{x+2, y+1});
		if (xp2 && yn1) moves.add(new Integer[]{x+2, y-1});

		if (xp1 && yn2) moves.add(new Integer[]{x+1, y-2});
		if (xn1 && yn2) moves.add(new Integer[]{x-1, y-2});

		if (xn2 && yn1) moves.add(new Integer[]{x-2, y-1});
		if (xn2 && yp1) moves.add(new Integer[]{x-2, y+1});


		return moves;
	}

	@Override
	public String getName() {
		return "Knight";
	}

	@Override
	public void move(int x, int y) {
		this.position[0] = x;
		this.position[1] = y;
	}

	@Override
	public boolean isWhite() {
		return white;
	}

	@Override
	public ImageView getImage() {
		if(white) return new ImageView(image_w);
		return new ImageView(image_b);
	}

}
