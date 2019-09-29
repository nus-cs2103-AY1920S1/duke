package duke;

import duke.ui.Ui;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Duke duke = new Duke("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = new AnchorPane();
            fxmlLoader.setRoot(ap);
            fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Doke - The surreal chatbot");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.getIcons().add(new Image(
                    Main.class.getResourceAsStream("/images/doke.png")
            ));

            MainWindow controller = fxmlLoader.<MainWindow>getController();
            controller.setDuke(duke);
            if (Ui.hasNewMessage()) {
                Ui.clearMessageQueue();
                controller.showError();
            }
            controller.showWelcome();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}