import static jermi.misc.Constant.MAIN_WINDOW_FXML_PATH;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jermi.component.Jermi;

/**
 * A GUI for Duke using FXML.
 */
public class Gui extends Application {
    /**
     * Starts the Jermi application.
     *
     * @param stage Stage.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource(MAIN_WINDOW_FXML_PATH));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setup(new Jermi());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}