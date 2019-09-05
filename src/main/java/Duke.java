import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * The main class of the program.
 */
public class Duke extends Application {

    /**
     * The storage containing the file to be modified in the hard drive.
     */
    private Storage storage;

    /**
     * The ui to print out the messages.
     */
    private UI ui;

    /**
     * The list of tasks.
     */
    private TaskList taskList;

    /**
     * Constructor that creates the main Duke class.
     * @throws Exception Used for when there are any errors.
     */
    public Duke() throws Exception {
        String filepath = "C:\\Users\\robyt\\IdeaProjects\\duke\\src\\main\\Data\\Duke.txt";
        this.storage = new Storage(filepath);
        try {
            this.taskList = new TaskList(storage);
        } catch (FileNotFoundException error) {
            this.taskList = new TaskList();
        }
        this.ui = new UI(taskList);
    }

    /**
     * The main logic of the program that keeps taking user input until the program is exited.
     * @throws Exception Used for when there are any errors.
     */
    public void run() throws Exception {
        ui.printIntro();
        storage.loadTasks();
        boolean programRunning = true;
        while (programRunning) {
            try {
                String input = ui.readLine();
                Command inputCommand = Parser.parse(input);
                inputCommand.execute(taskList, storage, ui);
            } catch (DukeException error) {
                ui.printError(error);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Duke().run();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.show();
    }

}
