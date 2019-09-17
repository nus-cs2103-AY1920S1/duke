package ui;

import duke.Duke;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            Duke duke = new Duke("data/dukeData.txt");
            String loadDukeMsg = duke.getFileFoundResponse("loading duke");
            if(loadDukeMsg.contains("Success")) {
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
}