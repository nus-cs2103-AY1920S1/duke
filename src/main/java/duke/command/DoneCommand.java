package duke.command;

import duke.exception.IllegalDescriptionException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

/**
 * A class representing a done command.
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    private ArrayList<Integer> indices;

    /**
     * Constructor specifying the index of the task which is done.
     * @param index the index of the task which is done.
     */
    public DoneCommand(int index) {
        this.indices = new ArrayList<>();
        indices.add(index);
    }

    /**
     * Constructor specifying indices of tasks to be set as done.
     * @param indices an array of indices of tasks to be set as done.
     */
    public DoneCommand(ArrayList<Integer> indices) {
        this.indices = new ArrayList<>(indices);
    }

    /**
     * Returns the result of executing the command.
     * @param tasks a list task to work on.
     * @return the result of executing the command.
     */
    @Override
    public CommandResult execute(TaskList tasks) throws IllegalDescriptionException {
        ArrayList<Task> doneTasks = new ArrayList<>();
        for(int index: indices) {
            try {
                Task task = tasks.setTaskAtIndexDone(index);
                doneTasks.add(task);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                //Ignore invalid indices
            }
        }
        if (doneTasks.isEmpty()) {
            throw new IllegalDescriptionException("Please provide at least 1 valid index.");
        }
        return new CommandResult(CommandType.Done, doneTasks);
    }
}