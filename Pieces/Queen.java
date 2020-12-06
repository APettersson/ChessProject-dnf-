package Pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Queen implements Piece {
	private Integer[] position;
	private boolean white;
	private static Image image_w = new Image("Pieces/Pictures/Queen_W.png");
	private static Image image_b = new Image("Pieces/Pictures/Queen_B.png");

	public Queen(int x, int y, boolean white){
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
		for(int i = 1; i < 7; i++){
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

		// Tower-like moves
		for(int i = 1; i < 8; i++){
			if(x - i >= 0){
				moves.add(new Integer[]{x - i, y});
			}
			if(x + i <= 7){
				moves.add(new Integer[]{x + i, y});
			}
			if(y - i >= 0){
				moves.add(new Integer[]{x, y - i});
			}
			if(y + i <= 7){
				moves.add(new Integer[]{x, y + i});
			}
		}

		return moves;

	}

	@Override
	public String getName() {
		return "Queen";
	}

	@Override
	public void move(int x, int y) {
		System.out.println(x + " " + y);
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
