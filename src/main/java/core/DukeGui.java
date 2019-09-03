package core;
import java.io.IOException;

import com.sun.tools.javadoc.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.Gui;
import ui.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class DukeGui extends Application {
    private Duke duke;

    public DukeGui() {}

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DukeGui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Duke");
            MainWindow mw = fxmlLoader.<MainWindow>getController();
            duke = new Duke(System.getProperty("user.dir") + "/data/duke.txt", new Gui(mw));
            mw.setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}