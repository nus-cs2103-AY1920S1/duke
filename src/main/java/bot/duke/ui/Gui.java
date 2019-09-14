package bot.duke.ui;

import java.io.IOException;

import bot.duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for duke using FXML.
 */
public class Gui extends Application {

    private Duke duke = new Duke("data/duke.txt");

    private static final String WINDOW_TITLE = "Duke, The Handsome Bot ~ by Krusagiz ~";
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/GuiWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle(WINDOW_TITLE);
            fxmlLoader.<GuiWindow>getController().setDuke(duke);
            stage.show();

            Ui.printWelcomeMsg();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
