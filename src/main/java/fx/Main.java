package fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    private DukeFx duke = new DukeFx();

    @Override
    public void start(Stage stage) {
        try {
            File fxmlFile = new File("src/main/resources/view/MainWindow.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlFile.toURI().toURL());
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
