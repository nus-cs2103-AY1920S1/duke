package duke;

import duke.command.Command;
import duke.task.TaskList;

import java.io.IOException;

/**
 * The main data of the current Duke instance. In addition to storing the current TaskList, history data is stored
 * to make undo functionality possible. Data access is controlled to keep track of changes.
 */
public class Model {
    private TaskList tasks;
    private TaskList previousTasks = null;
    private Command previousCommand = null;

    /**
     * Constructs a new Model with the specified TaskList.
     *
     * @param tasks  the TaskList to initialize this Model with
     */
    public Model(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a deep copy of the current <code>TaskList</code>. Changes to the copy will not affect the
     * TaskList stored in this Model. Mutation of the data in this Model must be performed by
     * calling {@link #update(Command, TaskList)}.
     *
     * @return  a deep copy of the current TaskList
     * @throws DukeException  if the current TaskList cannot be copied
     */
    public TaskList copyOfCurrentTasks() throws DukeException {
        try {
            return Storage.deepCopy(tasks);
        } catch (IOException | ClassNotFoundException e) {
            throw new DukeException("Couldn't copy TaskList!", e);
        }
    }

    /**
     * Updates the Model with the specified TaskList. The old TaskList is stored along with the calling
     * Command object as history metadata. {@link #undo()} is able to reverse the latest call to this method.
     *
     * @param updatingCommand  the Command requesting this update
     * @param tasks  the TaskList to set as current
     */
    public void update(Command updatingCommand, TaskList tasks) {
        this.previousTasks = this.tasks;
        this.tasks = tasks;
        this.previousCommand = updatingCommand;
    }

    /**
     * Reverses the changes caused by the last call to {@link #update(Command, TaskList)}. Cannot undo further than
     * the very previous <code>update</code>.
     *
     * @return  the Command that was undone
     * @throws IllegalStateException  if there is no more history left to undo
     */
    public Command undo() throws IllegalStateException {
        if (!hasPrevious()) {
            throw new IllegalStateException("No more undo history!");
        }
        tasks = previousTasks;
        previousTasks = null;
        Command retCommand = previousCommand;
        previousCommand = null;
        return retCommand;
    }

    /**
     * If there a previous <code>TaskList</code> stored in the history
     * (i.e. if an {@link #undo()} is currently possible).
     *
     * @return  true if there is a previous TaskList in the history
     */
    public boolean hasPrevious() {
        return previousTasks != null;
    }

    /**
     * Returns a string representation of this Model.
     *
     * @return  a string representation of this Model
     */
    @Override
    public String toString() {
        return "current tasks: " + tasks.toString()
                + "previous tasks" + previousTasks.toString();
    }
}
