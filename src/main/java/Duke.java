import com.core.savedata.SaveFile;
import java.util.Scanner;
import java.util.NoSuchElementException;

import com.util.Printer;
import com.core.State;
import com.core.Response;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke extends Application {

    /**
     * Main for program.
     * @param args  command line arguments
     */
    public static void main(String[] args) {
        Printer.printString("Hello! I'm Duke\nWhat can I do for you?");
        // greet

        Scanner scanner = new Scanner(System.in);

        State state = new State(SaveFile.loadTasks());
        try {
            while (!state.toExit) {
                String input = scanner.nextLine();
                for (Response r : Response.values()) {
                    if (r.call(input, state)) {
                        break;
                    }
                }
            }
        } catch (NoSuchElementException ignored) {
            System.out.print("");
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);
        stage.setScene(scene);
        stage.show();
    }
}