import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
	private static Stage stage;
	private Duke duke = new Duke();

	@Override
	public void start(Stage stage) {
		try {
			this.stage = stage;
			this.stage.getIcons().add(new Image("/images/mush.png"));
			this.stage.setTitle("Duke");
			FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
			AnchorPane ap = fxmlLoader.load();

			Scene scene = new Scene(ap);
			this.stage.setScene(scene);
			fxmlLoader.<MainWindow>getController().setDuke(duke);
			fxmlLoader.<MainWindow>getController().printWelcome(Ui.INTRO);
			this.stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//@@author ChangUo79-reused
	//Reused from https://stackoverflow.com/questions/27334455 with minor modifications
	public static void exitApp() {
		PauseTransition exitDelay = new PauseTransition(Duration.seconds(5));
		exitDelay.setOnFinished(event -> stage.close());
		exitDelay.play();
	}
}
