package myduke;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import myduke.ui.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class ApplicationLauncher extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    ApplicationLauncher.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = new AnchorPane();
            fxmlLoader.setRoot(ap);
            fxmlLoader.load();

            Scene scene = new Scene(ap);
            stage.setTitle("Duke");
            //stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            MainWindow controller = fxmlLoader.getController();
            stage.setOnCloseRequest(we -> {
                we.consume();
                controller.shutdown();
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}