package dose;

import dose.util.gui.GuiDose;
import dose.util.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Dose using FXML.
 */
public class Main extends Application {

    /** Image to be used as application icon for Dose.*/
    private Image doseIcon = new Image(getClass().getResourceAsStream("/images/coffee.png"));

    /** Underlying instance of Dose created when application runs.*/
    private GuiDose dose = new GuiDose();

    @Override
    public void start(Stage stage) {
        try {
            // create new scene using MainWindow fxml
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            // set the scene to display on the stage
            stage.setScene(scene);

            // set underlying instance of Dose
            fxmlLoader.<MainWindow>getController().setDose(dose);

            // set title and icon for the window
            stage.setTitle("Dose");
            stage.getIcons().add(doseIcon);

            // display the window
            stage.show();

            // initialize Dose
            dose.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}