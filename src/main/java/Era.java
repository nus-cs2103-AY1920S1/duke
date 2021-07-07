import command.Command;
import task.TaskList;
import util.Parser;
import util.Storage;

/**
 * Original Duke class to get response.
 */
public class Era {

    private Storage storage;
    private TaskList tasks;

    public Era(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Get response by executing the command and display on GUI.
     *
     * @param input inputCommand
     * @return the corresponding message
     */
    public String getResponse(String input) {
        Command command = Parser.parse(input);
        return command.executeCommand(tasks, storage);
    }
}