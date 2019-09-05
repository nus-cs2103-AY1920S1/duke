package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a command instructing Duke to delete a task.
 */
public class DeleteCommand extends Command {

    private String input;

    /**
     * Constructor of the command.
     * @param input User input that represents the command.
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Deletes the required task.
     * @param tasks List of tasks
     * @param ui User-Interface
     * @param storage Storage object
     * @return Duke's response to the command.
     * @throws InvalidDeleteDukeException If the task ID is invalid.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDeleteDukeException {
        String cleanedInput = input.strip().toLowerCase();
        String output = deleteTask(cleanedInput, tasks);
        return output;
    }

    private String deleteTask(String input, TaskList tasks) throws InvalidDeleteDukeException {
        String cleanedInput = input.strip().toLowerCase();
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(cleanedInput);
        ArrayList<Integer> taskIds = new ArrayList<>();
        while (m.find()) {
            taskIds.add(Integer.parseInt(m.group()));
        }
        if (taskIds.size() != 1) {
            throw new InvalidDeleteDukeException("Invalid \"delete\" command. Please enter only ONE task ID.");
        }
        return tasks.removeTaskFromList(taskIds.get(0));
    }

    /**
     * Checks if the command is an exit command.
     * @return False.
     */
    public boolean checkExit() {
        return false;
    }

}
