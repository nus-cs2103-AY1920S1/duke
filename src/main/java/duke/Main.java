package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        MainWindow ap = new MainWindow();
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        ap.setDuke(duke);
        ap.showWelcome();
        stage.setTitle("Duke Trump");
        stage.show();
    }
}
