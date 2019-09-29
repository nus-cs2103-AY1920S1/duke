package czkay.duke.model;

import czkay.duke.exception.DukeException;
import czkay.duke.logic.command.Command;
import czkay.duke.logic.parser.Parser;
import czkay.duke.storage.Storage;

import java.io.IOException;

/**
 * Driver class for the Duke iP.
 * Duke manages a task list that allows users to add, track and delete various types of tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;

    /**
     * Loads the task list from the hard disk. If the task list cannot be found, create a new task list instead.
     *
     * @return A message to tell the user whether a task list has been loaded or a new one has been created instead.
     */
    public String fetchTaskList() {
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

    /**
     * Gets a response from Duke based on the user input.
     *
     * @param input The raw user input.
     * @return A message to the user based on what they inputted.
     */
    public String getResponse(String input) {
        String response;
        try {
            Command c = Parser.parse(input);
            response = c.execute(tasks, storage);
        } catch (DukeException | IOException e) {
            response = e.getMessage();
        }
        return response;
    }

    /**
     * Gets the welcome message for when Duke first launches.
     *
     * @return The welcome message.
     */
    public String showWelcome() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Gets the list of reminders for the user.
     *
     * @return The reminders for the user.
     */
    public String showReminders() {
        return tasks.getReminders();
    }

}