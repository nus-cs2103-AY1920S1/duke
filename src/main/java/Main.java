
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

    private Duke duke;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            stage.setScene(scene);

            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/DaDuke.png")));

            stage.setTitle("Duke");
            stage.setResizable(false);

            stage.setX(0);
            stage.setY(0);

            duke = new Duke();

            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
