import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * This is the main class of the Trackr application. The Trackr application is an interface of a
 * to-do list that helps users manage a variety of tasks. This class creates the GUI for Trackr using FXML.
 * @author Shawn Lee
 * @version 1.0
 * @since 2019-08-20
 */
public class Main extends Application {

    private Trackr trackr = new Trackr();

    /**
     * Initialises the GUI of the application.
     * @param stage Window to be shown
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTrackr(trackr);
            stage.show();
            stage.setTitle("Ask Spongebob!");
            fxmlLoader.<MainWindow>getController().showWelcome();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}