package seedu.duke;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();
    private Image chatBotImage = new Image(this.getClass().getResourceAsStream("/images/ChatBot_Image.jpg"));

    /**
     * Loads MainWindow FXML file and create the JavaFX GUI components the file declares.
     * Sets up a stage and sets the duke.
     *
     * @param stage the stage to be set up
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().setStage(stage);
            fxmlLoader.<MainWindow>getController().setMainScene(scene);
            stage.setTitle("Duke ChatBot");
            stage.getIcons().add(chatBotImage);
            fxmlLoader.<MainWindow>getController().exitHandler = (ActionEvent event) -> stage.close();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}