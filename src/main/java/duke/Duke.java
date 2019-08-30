package duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

/**
 * Main class for Duke app.
 */
public class Duke extends Application {
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;


    /**
     * Constructor for Duke when save file path is known.
     *
     * @param filePath file path
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFromSaveFile());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Constructor for Duke when save file path is unknown (will use the default location).
     *
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.loadFromSaveFile());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create the FXMLLoader
        FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/view/MainWindow.fxml"));

        // Create the Pane and all Details
        AnchorPane root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Duke");
        loader.<MainWindowController>getController().setDuke(this);
        this.ui.setController(loader.getController());
        primaryStage.show();
    }
}