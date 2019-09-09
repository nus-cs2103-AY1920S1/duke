package ui.fx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ui.input.InputHandler;
import ui.input.InputListener;
import ui.output.OutputHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FxMain extends Application implements InputHandler, OutputHandler {
    private static boolean isStarted = false;
    private static FxMain singleton = new FxMain();

    private static List<InputListener> listeners = new ArrayList<>();
    private static DukeMainWindowController controller;

    private static final String GREETING = "Hello! I'm duke.Duke\nWhat can I do for you?";

    public static FxMain getInstance() {
        return singleton;
    }

    @Override
    public void start(Stage stage) {
        try {
            isStarted = true;

            File fxmlFile = new File("src/main/resources/view/MainWindow.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlFile.toURI().toURL());
            VBox vb = fxmlLoader.load();
            Scene scene = new Scene(vb);
            stage.setScene(scene);

            controller = fxmlLoader.getController();
            controller.configureController(this);

            stage.show();

            controller.printUserMessage(GREETING);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAllListeners(String input) {
        listeners.forEach(listener -> listener.update(input));
    }

    @Override
    public void addListener(InputListener listener) {
        listeners.add(listener);
    }

    @Override
    public void startHandler(OutputHandler output) {
        Application.launch(this.getClass());
    }

    @Override
    public void stopHandler() {
        Platform.exit();
    }

    @Override
    public void display(String message) {
        controller.printDukeMessage(message);
    }
}
