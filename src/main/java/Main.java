import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private static Stage stage;
    private Duke duke = new Duke();
    private MainWindow mainWindow;

    @Override
    public void start(Stage stage) {
        try {
            this.stage = stage;
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().showWelcome(duke.getUi().showWelcome());
            fxmlLoader.<MainWindow>getController().showWelcome(duke.getUi().helpcommand());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *Runs duke by checking for input and displaying errors.
     * @throws DukeException error exception
     */
    public void run() throws DukeException {
        duke = new Duke();
        mainWindow.init(duke);
        mainWindow.showWelcome("TEST");
    }

    /**
     * Exit app after 3 seconds.
     */
    public static void exitApp() {
        PauseTransition exitDelay = new PauseTransition(Duration.seconds(3));
        exitDelay.setOnFinished(event -> stage.close());
        exitDelay.play();
    }
}