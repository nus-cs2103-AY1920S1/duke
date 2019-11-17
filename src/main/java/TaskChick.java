import taskchick.command.Command;
import taskchick.exception.TaskChickException;
import taskchick.parser.Parser;
import taskchick.storage.Storage;
import taskchick.tasklist.TaskList;
import java.io.FileNotFoundException;

/**
 * Task Chick is a Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class TaskChick {

    private Storage storage;
    private TaskList tasks;

    /**
     * Takes in user input and gets the corresponding response from Task Chick.
     *
     * @param input User input.
     * @return Task Chick's response.
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (TaskChickException e) {
            return e.getMessage();
        }
    }

    /**
     * Initialises a session for Duke and loads tasks, if any, from a previous session.
     */
    public TaskChick() {
        storage = new Storage("taskchick.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

}
