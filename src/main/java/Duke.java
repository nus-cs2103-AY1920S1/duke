import java.io.IOException;

/**
 * Driver class for the Duke iP.
 * Duke manages a task list that allows users to add, track and delete various types of tasks.
 */
class Duke {
    private Storage storage;
    private TaskList tasks;

    String fetchTaskList() {
        storage = new Storage("data/tasks.tmp");
        try {
            tasks = storage.load();
            tasks.createReminders();
            return "Task list loaded.";
        } catch (IOException | ClassNotFoundException e) {
            tasks = new TaskList();
            return "Unable to access existing task list; initialising new task list instead.";
        }
    }

    String getResponse(String input) {
        String response;
        try {
            Command c = Parser.parse(input);
            response = c.execute(tasks, storage);
        } catch (DukeException | IOException e) {
            response = e.getMessage();
        }
        return response;
    }

    String showWelcome() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    String showReminders() {
        return tasks.getReminders();
    }

}