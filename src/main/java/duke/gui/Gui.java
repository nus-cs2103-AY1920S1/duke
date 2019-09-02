package duke.gui;

import duke.ui.Duke;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * A GUI for DUKE using FXML/JavaFX.
 */
public class Gui extends Application {

    private Duke duke = new Duke("main/resources/save/DukeSave01.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane mainWindow = fxmlLoader.load();
            Scene scene = new Scene(mainWindow);
            stage.setScene(scene);

            //set the current working Duke
            fxmlLoader.<MainWindow>getController().setDuke(duke);

            stage.show();

            // print greeting message after the stage is shown
            fxmlLoader.<MainWindow>getController().displayGreetingMessage();

            // show the goodbye message as a popup that needs to be clicked to close duke
            fxmlLoader.<MainWindow>getController().activityStatus.addListener((observable, oldValue, newValue) -> {
                if (!newValue && oldValue) {
                    DialogBox box = DialogBox.getDukeNormalDialog(
                            "GoodBye! Hope to see you again!\n>>CLICK TO EXIT<<",
                            new Image(Gui.class.getResourceAsStream("/images/fatCat.png")));

                    Scene sc = new Scene(box);
                    sc.setFill(Color.TRANSPARENT);
                    Stage goodbyePopup = new Stage();
                    goodbyePopup.setScene(sc);
                    goodbyePopup.initOwner(stage);
                    goodbyePopup.initModality(Modality.APPLICATION_MODAL);
                    goodbyePopup.initStyle(StageStyle.TRANSPARENT);
                    box.setOnMouseClicked((event) -> Platform.exit());

                    stage.close();
                    goodbyePopup.show();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}