import java.io.IOException;

/**
 * Driver class for the Duke iP.
 * Duke manages a task list that allows users to add, track and delete various types of tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;

    public Duke() { };

    String fetchTaskList() {
        storage = new Storage("data/tasks.tmp");
        try {
            tasks = storage.load();
            return "Task list loaded.";
        } catch (IOException | ClassNotFoundException e) {
            tasks = new TaskList();
            return "Unable to access existing task list; initialising new task list instead";
        }
    }

    String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(tasks, storage);
            return response;
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }

    public String showWelcome() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }
}