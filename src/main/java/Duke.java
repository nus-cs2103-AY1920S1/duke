import javafx.application.Application;
import javafx.stage.Stage;

public class Duke extends Application {

    private static Ui uiManager = new Ui();
    private static GUI gui = new GUI();

    /**
     * manages user inputs.
     *
     *
     */
    //public void run() {
      //  uiManager.takeUserCommand();
    //}

    /**
     * runs Duke.
     *
     */
    //public static void main(String[] args) {
      //  Duke duke = new Duke();
        //duke.run();
    //}

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components
        gui.init(stage);

    }

}
