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
    private static final String FILE_DELIMITER = "\\";
    private static final String PATH_TO_SAVEFILE = FILE_DELIMITER + "data" + FILE_DELIMITER + "icebear.txt";
    private static final String filepath = System.getProperty("user.dir") + PATH_TO_SAVEFILE;
    private IceBear iceBear = new IceBear(filepath);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setIceBear(iceBear);
            fxmlLoader.<MainWindow>getController().showWelcomeMessage();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}