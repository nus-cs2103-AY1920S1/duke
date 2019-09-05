import java.io.IOException;

/**
 * This is the main class for Project Duke.
 * @author Choong Yong Xin
 */

public class Duke {
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for Duke.
     *
     * @throws IOException if there is an error loading tasks from hard disk.
     */
    public Duke() throws IOException {
        String filePath = "/data/tasks.txt";
        storage = new Storage(filePath);
        //Load tasks from hard disk if file exists
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response to user input.
     *
     * @param input the command input by user.
     * @return string response to the command.
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }
}
