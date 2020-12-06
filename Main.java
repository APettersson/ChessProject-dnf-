import Pieces.Knight;
import Pieces.Piece;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane root = new Pane();


		VBox vb = new VBox();
		Board b = new Board();

		vb.getChildren().add(b.createBoard());
		root.getChildren().add(vb);

		Scene scene = new Scene(root, 450, 450);
		primaryStage.setTitle("CHESS");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
