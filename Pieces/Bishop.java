package Pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Bishop implements Piece {
	private Integer[] position;
	private boolean white;
	private static Image image_w = new Image("Pieces/Pictures/Bishop_W.png");
	private static Image image_b = new Image("Pieces/Pictures/Bishop_B.png");

	public Bishop(int x, int y, boolean white){
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
		for(int i = 1; i <= 8; i++){
			boolean xp = x + i <= 7;
			boolean xn = x - i >= 0;

			boolean yp = y + i <= 7;
			boolean yn = y - i >= 0;

			if(xp){
				if(yp){
					moves.add(new Integer[]{x+i, y+i});
				}
				if(yn){
					moves.add(new Integer[]{x+i, y-i});
				}
			}
			if(xn){
				if(yp){
					moves.add(new Integer[]{x-i, y+i});
				}
				if(yn){
					moves.add(new Integer[]{x-i, y-i});
				}
			}
		}


		return moves;
	}

	@Override
	public String getName() {
		return "Bishop";
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
