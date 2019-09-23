package duke;

import java.io.IOException;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private static final String APP_TITLE = "Conversation with Nezuko the Task Master";

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            stage.setTitle(APP_TITLE);
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            MainWindow mw = fxmlLoader.getController();
            Duke duke = new Duke(MyPaths.TASK_LIST, mw);
            mw.setDuke(duke);
            stage.show();
            mw.displayReminder();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}