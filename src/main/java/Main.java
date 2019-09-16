import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            MainWindow mainWindow = new MainWindow();
            fxmlLoader.setRoot(mainWindow);
            mainWindow = fxmlLoader.load();
            Scene scene = new Scene(mainWindow);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();

            // set up hello message on start
            String hello = duke.getHello();
            VBox dialogContainer = (VBox) scene.lookup("#dialogContainer");
            DialogBox helloDialog = DialogBox.getDukeDialog(
                    hello,
                    new Image(this.getClass().getResourceAsStream("/images/shocked_cat_square.jpg"))
            );
            dialogContainer.getChildren().addAll(helloDialog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
