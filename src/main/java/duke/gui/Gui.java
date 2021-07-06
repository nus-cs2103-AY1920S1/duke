package duke.gui;

import duke.ui.Duke;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.nio.file.Paths;

/**
 * A GUI for Duke using FXML/JavaFX.
 */
public class Gui extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(javafx.stage.Stage stage) {
        try {
            assert Paths.get("src", "main", "resources", MainWindow.MAIN_WINDOW_RESOURCE_PATH)
                    .toFile().exists() : "MainWindow.fxml does not exist";
            FXMLLoader fxmlLoader = new FXMLLoader();
            //FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource(MainWindow.MAIN_WINDOW_RESOURCE_PATH));
            fxmlLoader.setLocation(Gui.class.getResource(MainWindow.MAIN_WINDOW_RESOURCE_PATH));
            AnchorPane mainWindow = fxmlLoader.load();
            Scene scene = new Scene(mainWindow);
            stage.setScene(scene);
            stage.setResizable(false);

            //set the current working Duke
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setTitle("Duke");

            stage.show();

            // print greeting message after the stage is shown
            fxmlLoader.<MainWindow>getController().activateDuke();

            // tries to load task list, and display message/error
            fxmlLoader.<MainWindow>getController().loadExistingTaskList(Duke.DEFAULT_SAVE_FILE_NAME);

            // show the goodbye message as a popup that needs to be clicked to close duke
            fxmlLoader.<MainWindow>getController().dukeActivityStatus.addListener((observable, oldValue, newValue) -> {
                if (!newValue && oldValue) {
                    showGoodbyePopup(stage);
                }
            });

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void showGoodbyePopup(Stage stage) {
        assert stage != null;
        assert stage.isShowing();

        DialogBox box = DialogBox.getDukeNormalDialog(
                "GoodBye! Hope to see you again!\n\nCLICK ME or PRESS ENTER",
                new Image(Gui.class.getResourceAsStream(MainWindow.DUKE_IMAGE_RESOURCE_PATH)));

        Scene sc = new javafx.scene.Scene(box);
        sc.setFill(Color.TRANSPARENT);
        Stage goodbyePopup = new Stage();
        goodbyePopup.setScene(sc);
        goodbyePopup.initOwner(stage);
        goodbyePopup.initModality(Modality.APPLICATION_MODAL);
        goodbyePopup.initStyle(StageStyle.TRANSPARENT);
        box.setOnMouseClicked(event -> Platform.exit());
        sc.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                Platform.exit();
            }
        });

        stage.close();
        goodbyePopup.show();

    }

}