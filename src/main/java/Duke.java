import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import java.io.FileNotFoundException;
import java.util.TimerTask;

/**
 * This is the main class of the Duke application. The Duke application is an interface of a
 * to-do list that allows users to add tasks to do, deadlines and events to attend.
 * @author Shawn Lee
 * @version 1.0
 * @since 2019-08-20
 */
public class Duke {

    /**
     * Deals with loading tasks from the file and saving tasks in the file.
     */
    private Storage storage;

    /**
     * Contains the task list.
     */
    private TaskList tasks;

    /**
     * Deals with interactions with the user.
     */
    public Ui ui;

    /**
     * This is the class constructor assigning a filepath to the Duke object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("C:/Users/Shawn Lee/Desktop/UNI FILES/Y2S1/CS2103T/duke/data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException ex) {
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
