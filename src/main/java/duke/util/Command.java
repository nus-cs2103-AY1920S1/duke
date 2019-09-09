package duke.util;

import java.io.IOException;

public class Command {
    private static final int BYE = 0;
    private static final int LIST = 1;
    private static final int DONE = 2;
    private static final int DELETE = 3;
    private static final int FIND = 4;
    private static final int TASK = 5;
    private static final int COMMANDS = -1;
    private static final String list =
            "1) list: view your Todo list\n" +
            "2) done {task_number}: mark a task as done\n" +
            "3) delete {task_number}: delete a task\n" +
            "4) find {query}: find tasks containing {query}\n\n" +
            "          ---Task Commands---\n\n" +
            "5) todo {task_name}\n" +
            "6) event {event_name} /at {dd/mm/yyyy} {time_in_24hr}\n" +
            "7) deadline {task_name} /by {dd/mm/yyyy} {time_in_24hr}";

    private String[] inputParts;
    private int command;

    /**
     * Constructor for Command object
     * @param inputParts user input split into words, represented as an array
     * @param command enum to determine which command it is
     */
    public Command(String[] inputParts, int command) {
        this.command = command;
        this.inputParts = inputParts;
    }

    /**
     * Executes command
     * @param storage Storage object for saving/retrieving task list
     * @param ui Ui object to return duke's responses as strings
     * @param tasks List of task
     * @return String to be displayed as message
     * @throws IOException if there is a problem when reading/writing to the history
     * @throws DukeException if there is an invalid command/action by user
     */
    public String execute(Storage storage, Ui ui, TaskList tasks) throws IOException, DukeException {
        String result = "";
        switch (command) {
        case BYE:
            storage.saveHistory(tasks.getTaskList());
            return ui.byeMessage();
        case LIST:
            return tasks.getListAsString();
        case DONE:
            result = tasks.markItemComplete(Integer.parseInt(inputParts[1]));
            storage.saveHistory(tasks.getTaskList());
            return result;
        case DELETE:
            result = tasks.deleteItem(Integer.parseInt(inputParts[1]));
            storage.saveHistory(tasks.getTaskList());
            return result;
        case FIND:
            return tasks.findItem(inputParts[1]);
        case TASK:
            result = tasks.registerNewTask(inputParts);
            storage.saveHistory(tasks.getTaskList());
            return result;
        case COMMANDS:
            return list;
        }
        assert false : "Invalid Command";
        return result;
    }
}
