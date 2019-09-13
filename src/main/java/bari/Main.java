package bari;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Bari using FXML.
 */
public class Main extends Application {
    private Bari bari = new Bari();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Bari");
            fxmlLoader.<MainWindow>getController().setBari(bari);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
