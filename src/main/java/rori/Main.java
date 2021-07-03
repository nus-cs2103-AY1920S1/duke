import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;

/**
 * A GUI for Rori using FXML.
 */
public class Main extends Application {
    private Rori rori;

    public Main() throws Exception {
        rori = new Rori();
    }

    /**
     * Initializes the stage for the program, Rori.
     * 
     * @param stage The stage holding the nodes.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Rori");
            stage.getIcons().add(new Image("/images/RoriIcon.png"));
            fxmlLoader.<MainWindow>getController().setRori(rori);
            stage.show();
            beginRoriMessages(fxmlLoader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes Rori by printing welcome message and tutorial if first time.
     * 
     * @param fxmlLoader For the MainWindow.fxml
     */
    private void beginRoriMessages(FXMLLoader fxmlLoader) {
        fxmlLoader.<MainWindow>getController().welcomeMessage();
        if (this.rori.getFirstTime()) {
            fxmlLoader.<MainWindow>getController().tutorialMessage();
        }
    }
}