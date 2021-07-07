package seedu.duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import seedu.duke.model.Duke;
import seedu.duke.ui.MainWindow;

/**
 * A GUI for seedu.duke.model.Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    /**
     * Start method called to start the Duke program by building the necessary JavaFx components.
     * @param stage A Stage taken in where the GUI of Duke program is run.
     */
    @Override
    public void start(Stage stage) {

        Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            assert fxmlLoader != null : "fxmlLoader for Main class is null";
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            // Font by Andreas Larsen via: https://github.com/larsenwork/Gidole
            // Credits to Ong Bing Jue (https://github.com/bjhoohaha) for helping to spot and resolve bug that
            // caused font compatibility issues across platforms.
            String fontName = Main.class.getResource("/fonts/Gidole-Regular.ttf").toExternalForm();
            Font font = Font.loadFont(fontName, 15);

            scene.getStylesheets().addAll(this.getClass().getResource("/view/style.css").toExternalForm());
            stage.getIcons().add(dukeImage);
            stage.setTitle("Duke V2.5");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}