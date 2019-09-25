package duke.core;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DukeApplication extends Application {

    public static final String APPLICATION_TITLE = "Duke";

    @Override
    public void start(Stage stage) throws IOException {
        DukeModel model = new DukeModel();
        DukeController controller = DukeController.createInstance(model);
        Scene scene = controller.getScene();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(DukeApplication.APPLICATION_TITLE);
        stage.show();
    }

}