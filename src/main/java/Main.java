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
    private Duke duke = new Duke();
    FXMLLoader fxmlLoader;

    public Main() {
        fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
    }

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Duke");
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/duke.png")));

            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().printDialog("Hello! I'm Duke");
            fxmlLoader.<MainWindow>getController().printDialog("What can I do for you?");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}