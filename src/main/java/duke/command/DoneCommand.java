package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.InvalidTaskDukeException;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a command instructing Duke to mark a task as done.
 */
public class DoneCommand extends Command {
    private String input;

    /**
     * Constructor for done commands.
     * @param input User-input representing a done command.
     */
    public DoneCommand(String input) {
        this.input = input;
    }

    /**
     * Marks the input task(s) as done.
     * @param tasks List of tasks
     * @param ui User-Interface
     * @param storage Storage object
     * @return Duke's response to the done command.
     * @throws DukeException If the done command is invalid.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String cleanedInput = input.strip().toLowerCase();
        String output = markAsDone(cleanedInput, tasks);
        return output;
    }

    private String markAsDone(String input, TaskList tasks) throws DukeException {
        try {
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(input);
            ArrayList<Integer> listOfTaskIds = new ArrayList<>();
            while (m.find()) {
                int taskId = Integer.parseInt(m.group());
                listOfTaskIds.add(taskId);
            }
            if (listOfTaskIds.isEmpty()) {
                throw new DukeException("Invalid \"done\" command!"
                        + " Please enter one or more integer IDs after \"done\".");
            }
            return tasks.getDoneUpdates(listOfTaskIds);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskDukeException("Task not found! Please enter a valid task ID.");
        }
    }

    /**
     * Checks if the command is an exit command.
     * @return False.
     */
    public boolean checkExit() {
        return false;
    }
}
