package duke.command;

import duke.exception.IllegalDescriptionException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A class representing a delete command.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private ArrayList<Integer> indices;

    /**
     * Constructor specifying the index of the task to be deleted.
     * @param index the index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.indices = new ArrayList<>();
        indices.add(index);
    }

    /**
     * Constructor specifying indices of tasks to be deleted.
     * @param indices an array of indices of tasks to be deleted.
     */
    public DeleteCommand(ArrayList<Integer> indices) {
        this.indices = new ArrayList<>(indices);
        Collections.sort(indices);
    }

    /**
     * Returns the result of executing the command.
     * @param tasks a list task to work on.
     * @return the result of executing the command.
     */
    @Override
    public CommandResult execute(TaskList tasks) throws IllegalDescriptionException {
        ArrayList<Task> deletedTasks = new ArrayList<>();
        for(int i = indices.size() - 1; i >= 0; i--) {
            try {
                Task task = tasks.removeTaskAtIndex(indices.get(i));
                deletedTasks.add(task);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
            }
        }
        if (deletedTasks.isEmpty()) {
            throw new IllegalDescriptionException("Please provide at least 1 valid index.");
        }
        return new CommandResult(CommandType.Delete, deletedTasks);
    }
}