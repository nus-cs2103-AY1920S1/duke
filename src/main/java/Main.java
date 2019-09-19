import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
    //private Duke dukeBot = new Duke();

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Soviet Comrade");
            //fxmlLoader.<MainWindow>getController().setDuke(dukeBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}