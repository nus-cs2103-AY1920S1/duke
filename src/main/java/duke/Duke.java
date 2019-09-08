package duke;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.parser.Parser;
import duke.exception.DukeException;
import java.io.FileNotFoundException;

public class Duke {

    private Storage storage = new Storage("src/main/data/duke.txt");
    private TaskList tasks;

    /**
     * Creates an instance of Duke with a path to task list file.
     * Loads data from the file and the tasks are stored into a TaskList.
     * If file is not found or empty, exception is reported.
     */
    public Duke() {
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Creates new instance of Parser and run parse method to read user input.
     * Catches and reports DukeExceptions.
     *
     * @return Response to be displayed.
     */
    public String getResponse(String text) {
        try {
            Command c = Parser.parse(text);
            return c.execute(storage, tasks);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
