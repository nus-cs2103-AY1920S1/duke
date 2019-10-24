package ui;

import duke.Duke;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//@@author jaesimin-reused
//Reused from https://github.com/nus-cs2103-AY1920S1/duke/blob/master/tutorials/javaFxTutorialPart1.md with minor modifications
//Reused from https://github.com/nus-cs2103-AY1920S1/duke/blob/master/tutorials/javaFxTutorialPart2.md with minor modifications
//Reused from https://github.com/nus-cs2103-AY1920S1/duke/blob/master/tutorials/javaFxTutorialPart3.md with minor modifications
//Reused from https://github.com/nus-cs2103-AY1920S1/duke/blob/master/tutorials/javaFxTutorialPart4.md with minor modifications

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    Duke duke = new Duke("data/dukeData.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            String loadDukeMsg = duke.getFileFoundResponse("loading duke");
            if (loadDukeMsg.contains("Success")) {
                fxmlLoader.<MainWindow>getController().setDuke(duke);
                stage.show();
                fxmlLoader.<MainWindow>getController().showLoadDukeStatus();
                fxmlLoader.<MainWindow>getController().showLoadDataStatus();
                fxmlLoader.<MainWindow>getController().showWelcome();
            } else {
                //ERROR
                assert !loadDukeMsg.contains("Success") : "Workflow error";
                System.err.println(loadDukeMsg);
                System.exit(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

//@@author