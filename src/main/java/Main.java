import java.io.IOException;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

import slave.exception.KappaException;

/**
 * A GUI for Kappa using FXML.
 */
public class Main extends Application {

    private static Stage stage;
    private Kappa duke = new Kappa();

    public Main() throws KappaException {
    }

    /**
     * Specifies how the application should be started.
     *
     * @param stage Stage to set the GUI.
     */
    @Override
    public void start(Stage stage) {
        try {
            Main.stage = stage;
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Kappa");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            Main.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}