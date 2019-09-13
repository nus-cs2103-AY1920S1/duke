import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Rori using FXML.
 */
public class Main extends Application {
    private Rori rori;
    private RoriManager roriManager;

    public Main() throws Exception {
        rori = new Rori();
        roriManager = rori.getRoriManager();
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRori(rori);
            stage.show();
            beginRoriMessages(fxmlLoader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void beginRoriMessages(FXMLLoader fxmlLoader) {
        fxmlLoader.<MainWindow>getController().welcomeMessage();
        if (this.roriManager.getFirstTime()) {
            fxmlLoader.<MainWindow>getController().tutorialMessage();
        }
    }
}