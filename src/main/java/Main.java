import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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
    private Duke duke = new Duke();
    private FXMLLoader fxmlLoader;

    public Main() {
        URL url = Main.class.getResource("/view/MainWindow.fxml");
        fxmlLoader = new FXMLLoader(url);
    }

    @Override
    public void start(Stage stage) {
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/images/duke.png");
            Image imgIcon = new Image(inputStream);
            stage.setTitle("Duke");
            stage.getIcons().add(imgIcon);

            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            assert stage.getTitle().equals("Duke") : "Application title should be Duke";

            MainWindow mainWindow = fxmlLoader.getController();
            mainWindow.setDuke(duke);
            mainWindow.printDialog("Hello! I'm Duke");
            mainWindow.printDialog("What can I do for you?");

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}