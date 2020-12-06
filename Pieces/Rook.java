package Pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Rook implements Piece {
	private Integer[] position;
	private boolean moved;
	private boolean white;
	private static Image image_w = new Image("Pieces/Pictures/Rook_W.png");
	private static Image image_b = new Image("Pieces/Pictures/Rook_B.png");

	public Rook(int x, int y, boolean white){
		position = new Integer[2];
		position[0] = x;
		position[1] = y;
		this.white = white;
		moved = false;
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


		if(white){
			if(!moved){
				moves.add(new Integer[]{x-2, y});
			}
			moves.add(new Integer[]{x-1, y});
		} else {
			if(!moved){
				moves.add(new Integer[]{x+2, y});
			}
			moves.add(new Integer[]{x+1, y});

		}
		return moves;
	}

	@Override
	public String getName() {
		return "Rook";
	}

	@Override
	public void move(int x, int y) {
		moved = true;
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
