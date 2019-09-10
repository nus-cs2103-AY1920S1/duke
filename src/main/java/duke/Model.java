package duke;

import duke.command.Command;
import duke.task.TaskList;

import java.io.IOException;

public class Model {
    private TaskList tasks;
    private TaskList previousTasks = null;
    private Command previousCommand = null;

    public Model(TaskList tasks) {
        this.tasks = tasks;
    }

    public TaskList copyOfCurrentTasks() throws DukeException {
        try {
            return Storage.deepCopy(tasks);
        } catch (IOException | ClassNotFoundException e) {
            throw new DukeException("Couldn't copy TaskList!", e);
        }
    }

    public void update(Command updatingCommand, TaskList tasks) {
        this.previousTasks = this.tasks;
        this.tasks = tasks;
        this.previousCommand = updatingCommand;
    }

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

    public boolean hasPrevious() {
        return previousTasks != null;
    }

    @Override
    public String toString() {
        return "current tasks: " + tasks.toString()
                + "previous tasks" + previousTasks.toString();
    }
}
