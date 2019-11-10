package duke.ui;

import javafx.stage.Stage;

/**
 * Ui class which generates feedback to the user.
 */
public class UiManager {

    /** Mainwindow for views to be presented on. **/
    private MainWindow mainWindow;

    /**
     * Starts the application and shows the primary stage.
     * @param primaryStage Primary stage where the views will be shown.
     */
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("How about, no.");
            primaryStage.setResizable(false);
            mainWindow = new MainWindow(primaryStage);
            mainWindow.show(); //This should be called before creating other UI parts
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
