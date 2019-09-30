package duke;

import duke.util.gui.GuiDuke;
import duke.util.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    /** Image to be used as application icon for Duke.*/
    private Image dukeIcon = new Image(getClass().getResourceAsStream("/images/coffee.png"));

    /** Underlying instance of Duke created when application runs.*/
    private GuiDuke duke = new GuiDuke();

    @Override
    public void start(Stage stage) {
        try {
            // create new scene using MainWindow fxml
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            // set the scene to display on the stage
            stage.setScene(scene);

            // set underlying instance of Duke
            fxmlLoader.<MainWindow>getController().setDuke(duke);

            // set title and icon for the window
            stage.setTitle("Duke");
            stage.getIcons().add(dukeIcon);

            // display the window
            stage.show();

            // initialize Duke
            duke.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}