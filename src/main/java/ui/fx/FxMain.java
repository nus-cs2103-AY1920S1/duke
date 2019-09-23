package ui.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Main driver for JavaFx ui. It is a subclass of the JavaFx Application class. Because only one javaFx application can
 * be running at each time, there will be a DukeMainWindowController that corresponds to the main window of the
 * currently active javaFx application.
 */
public class FxMain extends Application {
    private static DukeMainWindowController mainWindowController;
    private static boolean isApplicationStarted;

    private static List<FxDukeInput> listeners = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        if (FxMain.isApplicationStarted) {
            System.out.println("An instance of this javaFx application is already running.");
            return;
        }

        try {
            System.out.println("Starting javaFx application...");

            FXMLLoader fxmlLoader = new FXMLLoader(FxMain.class.getResource("/view/MainWindow.fxml"));
            VBox vb = fxmlLoader.load();
            Scene scene = new Scene(vb);
            stage.setScene(scene);

            FxMain.mainWindowController = fxmlLoader.getController();
            FxMain.listeners.forEach(listener -> mainWindowController.addInputListener(listener));
            FxMain.isApplicationStarted = true;

            stage.show();

            mainWindowController.printDukeMessage("Hello young padawan. How may I help you today?");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void stop() throws Exception {
        System.out.println("Closing javaFx application...");
        FxMain.mainWindowController = null;
        FxMain.isApplicationStarted = false;

        super.stop();
    }

    /**
     * Method to add listeners input listeners to the javaFx application. Listeners MUST be added before the
     * application is launched or listeners as javaFX applications are blocking.
     *
     * @param listeners listeners to be added before application is launched.
     */
    static void addListeners(FxDukeInput... listeners) {
        FxMain.listeners.addAll(List.of(listeners));
    }

    /**
     * Method to remove input listeners from the javaFx application. Listeners will be removed from BOTH the FxMain
     * class and the DukeMainWindowController instance.
     *
     * @param listeners listeners to be removed.
     */
    static void removeListeners(FxDukeInput... listeners) {
        Stream.of(listeners).forEach(listener -> {
            FxMain.listeners.remove(listener);
            mainWindowController.removeInputListener(listener);
        });
    }

    /**
     * Returns true if application has already been started.
     *
     * @return boolean corresponding to if the application has already been started.
     */
    static boolean isIsApplicationStarted() {
        return isApplicationStarted;
    }

    /**
     * Returns the main window instance of the current running javaFx application.
     *
     * @return main window instance of the current javaFx application.
     */
    static DukeMainWindowController getActiveMainWindowController() {
        return FxMain.mainWindowController;
    }
}