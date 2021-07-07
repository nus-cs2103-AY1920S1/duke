import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class Duke extends Application {
    //private static Ui uiManager = new Ui();
    private static GUI gui = new GUI();

    @Override
    public void start(Stage stage) throws IOException {
        //Step 1. Setting up required components
        gui.init(stage);

    }

}
