package q.main;

import q.user.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Q using FXML.
 */
public class Main extends Application {

    private Q defaultQ = new Q("data/tasks.txt", "data/archive.txt");

    /**
     * Starts the GUI.
     *
     * @param stage The stage for the GUI.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Q");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
            fxmlLoader.<MainWindow>getController().setBindedQ(defaultQ);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}