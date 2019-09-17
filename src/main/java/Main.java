import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke("/Users/liuzechu/Desktop/CS2103/project_duke/duke/data/duke.txt");
    private static ByteArrayOutputStream baos;
    @FXML
    private MainWindow mainWindow;

    @Override
    public void start(Stage stage) {
        // Redirect output of System.out.print into a stream so as to capture it as a String
        baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        // Tell Java to use the special stream
        System.setOut(ps);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Duke.exitDuke();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Duke.exitDuke();

        System.exit(0);
    }

    public static ByteArrayOutputStream getBaos() {
        return baos;
    }
}