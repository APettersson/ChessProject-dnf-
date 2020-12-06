package Pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class King implements Piece{
	private Integer[] position;
	private boolean white;
	private static Image image_w = new Image("Pieces/Pictures/King_W.png");
	private static Image image_b = new Image("Pieces/Pictures/King_B.png");

	public King(int x, int y, boolean white){
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

		boolean xp = x + 1 <= 7;
		boolean xn = x - 1 >= 0;
		boolean yp = y + 1 <= 7;
		boolean yn = y - 1 >= 0;

		if(xp) moves.add(new Integer[]{x+1, y});
		if(xn) moves.add(new Integer[]{x-1, y});
		if(yp) moves.add(new Integer[]{x, y+1});
		if(yn) moves.add(new Integer[]{x, y-1});

		if(xp && yp) moves.add(new Integer[]{x+1, y+1});
		if(xn && yp) moves.add(new Integer[]{x-1, y+1});
		if(xp && yn) moves.add(new Integer[]{x+1, y-1});
		if(xn && yn) moves.add(new Integer[]{x-1, y-1});

		return moves;
	}

	@Override
	public String getName() {
		return "King";
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
