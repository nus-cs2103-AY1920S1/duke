
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke("C:/Users/User/Documents/GitHub/duke/src/main/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            //fxmlLoader.<MainWindow>getController().initialize();
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
            System.out.println("\u2713");
            System.out.println("\u2718");
            System.out.println("☹ OOPS!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}