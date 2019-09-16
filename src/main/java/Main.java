import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Duke duke;

    private static final double windowWidth = 550;
    private static final double windowHeight = 625;

    /**
     * Creates a new instance of Main.
     * 
     * @throws Exception @see Duke();
     */
    public Main() throws Exception {
        duke = new Duke();
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            
            AnchorPane ap = fxmlLoader.load();
            ap.setBackground(Background.EMPTY);

            Scene scene = new Scene(ap, windowWidth, windowHeight);
            scene.setFill(Color.rgb(25, 25, 25, 1.0));
            stage.setScene(scene);

            stage.setTitle("Duke");
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/DukeIcon.png")));

            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
