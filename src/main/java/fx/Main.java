package fx;

import command.GreetCommand;
import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.DukeOutput;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            File fxmlFile = new File("src/main/resources/view/MainWindow.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlFile.toURI().toURL());
            VBox vb = fxmlLoader.load();
            Scene scene = new Scene(vb);
            stage.setScene(scene);

            Duke duke = new Duke();
            duke.initialize();

            DukeMainWindowController controller = fxmlLoader.getController();
            controller.configureMainWindowController(duke.getTaskListController());
            DukeOutput.setUpDukeOutput(controller);

            stage.show();

            new GreetCommand().execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
