import javafx.application.Application;
import javafx.stage.Stage;

public class Duke extends Application {
    //private static Ui uiManager = new Ui();
    private static GUI gui = new GUI();

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components
        gui.init(stage);

    }
    //public Duke() {}

    public static void main(String[] args) {
        launch(args);
    }


}
