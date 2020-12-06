import Pieces.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.EventListener;

public class Board {
	private ArrayList<Piece> PIECES;
	private ArrayList<Piece> LOST_PIECES;
	private StackPane[][] positions;
	private boolean[][] tiles;
	private int rectSize = 35;

	public Board(){
		tiles = new boolean[8][8];
		boolean swapper = true;
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				tiles[i][j] = swapper;
				swapper = !swapper;
			}
			swapper = !swapper;
		}
		PIECES = new ArrayList<>();
		LOST_PIECES = new ArrayList<>();
		populate();
		positions = new StackPane[8][8];
	}

	public void setSize(int val){
		rectSize = val;
		refresh();
	}

	public VBox createBoard(){
		createTiles();
		VBox board = new VBox();
		for (int i = 0; i < 8; i++) {
			HBox hbox = new HBox();
			hbox.setSpacing(2);
			hbox.setPadding(new Insets(1, 1, 1, 1));
			for (int j = 0; j < 8; j++) {
				hbox.getChildren().add(positions[i][j]);
			}
			board.getChildren().add(hbox);
		}
		return board;
	}


	public void createTiles(){
		for(int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				StackPane pos = new StackPane();
				Rectangle r = new Rectangle(rectSize, rectSize);
				positions[i][j] = pos;
				if (tiles[i][j]) {
					r.setFill(Color.WHEAT);
				} else {
					r.setFill(Color.SADDLEBROWN);
				}
				r.setStroke(Color.BLACK);

				final Integer[] xy = {i, j};

				pos.setOnMouseClicked(event -> {
					refresh();

					Rectangle t = new Rectangle(rectSize, rectSize);
					t.setFill(Color.AZURE);
					t.setStroke(Color.BLACK);

					pos.getChildren().clear();
					pos.getChildren().add(t);




					for (Piece p : PIECES) {
						if(xy[0] == p.getPosition()[0] && xy[1] == p.getPosition()[1]){
							pos.getChildren().add(p.getImage());
							detectCollision(p);
							System.out.println("Selected: " + p.getName() + " " + p.getPosition()[0] + p.getPosition()[1] +  ". WHITE: " + p.isWhite());


							for (Integer[] i12 : p.legalMoves()) {
								StackPane tpos = positions[i12[0]][i12[1]];

								Rectangle s = new Rectangle(rectSize, rectSize);
								s.setStroke(Color.BLACK);
								tpos.getChildren().clear();
								s.setFill(Color.GREEN);
								tpos.getChildren().addAll(s);
								s.setOnMouseClicked(e -> {
									p.move(i12[0], i12[1]);
									refresh();
								});





								PIECES.forEach(piece -> {
									if (piece.getPosition()[0] == i12[0] && piece.getPosition()[1] == i12[1]) {
										tpos.getChildren().add(piece.getImage());

										System.out.println("SOMETHING HAPPENED");

										if(p.isWhite() != piece.isWhite()) {
											//if(xy[0] == p.getPosition()[0]) System.out.println("Distance: " + (p.getPosition()[1] - xy[1]));
											tpos.getChildren().clear();
											StackPane child = new StackPane();
											System.out.println("SOMETHING MORE HAPPENED");
											s.setFill(Color.RED);
											child.getChildren().addAll(s, piece.getImage());
											tpos.getChildren().add(child);
											child.setOnMouseClicked(e -> {
												p.move(i12[0], i12[1]);
												System.out.println("AN ATTACK!");
												LOST_PIECES.add(piece);
												PIECES.remove(piece);
												//positions[i12[0]][i12[1]] = pos;
												refresh();
											});
										}
									}
								});
							}
						}
					}
				});
					pos.getChildren().add(r);
				}
			}



			for (Piece p : PIECES) {
				System.out.println(p.getPosition()[0] + " " + p.getPosition()[1]);
				positions[p.getPosition()[0]][p.getPosition()[1]].getChildren().add(p.getImage());
		}
	}

	private void refresh(){
		System.out.println("refreshing");
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++) {
				StackPane pos = positions[i][j];
				pos.getChildren().clear();

				Rectangle r = new Rectangle(rectSize, rectSize);
				r.setStroke(Color.BLACK);

				if (tiles[i][j]) {
					r.setFill(Color.WHEAT);
				} else {
					r.setFill(Color.SADDLEBROWN);
				}
				positions[i][j].getChildren().add(r);

				for(Piece p : PIECES){
					positions[p.getPosition()[0]][p.getPosition()[1]].getChildren().add(p.getImage());
				}
			}
		}
	}


	private void detectCollision(Piece piece){
		ArrayList<Integer[]> moveSet = piece.legalMoves();
		Integer[] maxX = {piece.getPosition()[1], 7};
		Integer[] minX = {piece.getPosition()[1], 0};

		Integer[] maxY = {7, piece.getPosition()[0]};
		Integer[] minY = {0, piece.getPosition()[0]};



		for(Integer[] move : moveSet){
			if(move[1] == piece.getPosition()[1] && maxX[0] < move[0]){
				maxX = move;
			}
			if(move[1] == piece.getPosition()[1] && maxX[0] > move[0]){
				minX = move;
			}

			if(move[0] == piece.getPosition()[0] && maxY[1] < move[1]){
				maxY = move;
			}
			if(move[0] == piece.getPosition()[0] && maxY[1] > move[1]){
				minY = move;
			}

		}
		System.out.println("maxY: " + maxY[1]);

		ArrayList<String> meldinger = new ArrayList<>();

		for(Piece p : PIECES){
			if(p.getPosition()[1] == piece.getPosition()[1] && piece.getPosition()[0] < p.getPosition()[0] && p.getPosition()[1] < maxX[0]) meldinger.add("COLLISION DETECTED (maxX): "+ p.getPosition()[0] + " " + p.getPosition()[1]);
			if(p.getPosition()[1] == piece.getPosition()[1] && piece.getPosition()[0] > p.getPosition()[0] && p.getPosition()[1] > minX[0]) meldinger.add("COLLISION DETECTED (minX): "+ p.getPosition()[0] + " " + p.getPosition()[1]);

			if(p.getPosition()[0] == piece.getPosition()[0] && piece.getPosition()[1] < p.getPosition()[1] && p.getPosition()[1] < maxY[1]) meldinger.add("COLLISION DETECTED (maxY): "+ p.getPosition()[0] + " " + p.getPosition()[1]);
			if(p.getPosition()[0] == piece.getPosition()[0] && piece.getPosition()[1] > p.getPosition()[1] && p.getPosition()[1] > minY[1]) meldinger.add("COLLISION DETECTED (minY): "+ p.getPosition()[0] + " " + p.getPosition()[1]);
		}

		meldinger.sort((o1, o2) -> o1.compareTo(o2));
		meldinger.forEach(m -> System.out.println(m));

	}

	private void populate(){
		// White pieces
		for(int i = 0; i < 8; i++){
			PIECES.add(new Rook(6, i, true));
		}

		PIECES.add(new Tower(7, 0, true));
		PIECES.add(new Tower(7, 7, true));

		PIECES.add(new Knight(7, 1, true));
		PIECES.add(new Knight(7, 6, true));

		PIECES.add(new Bishop(7, 2, true));
		PIECES.add(new Bishop(7, 5, true));

		PIECES.add(new Queen(7, 3, true));
		PIECES.add(new King(7, 4, true));

		// Black pieces
		for(int i = 0; i < 8; i++){
			PIECES.add(new Rook(1, i, false));
		}
		PIECES.add(new Tower(0, 0, false));
		PIECES.add(new Tower(0, 7, false));

		PIECES.add(new Knight(0, 1, false));
		PIECES.add(new Knight(0, 6, false));

		PIECES.add(new Bishop(0, 2, false));
		PIECES.add(new Bishop(0, 5, false));

		PIECES.add(new Queen(0, 3, false));
		PIECES.add(new King(0, 4, false));

	}


}
