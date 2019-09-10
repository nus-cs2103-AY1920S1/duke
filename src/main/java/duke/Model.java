package duke;

import duke.task.TaskList;

import java.io.IOException;

public class Model {
    private TaskList tasks;
    private TaskList previousTasks = null;

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

    public void update(TaskList tasks) {
        this.previousTasks = this.tasks;
        this.tasks = tasks;
    }

    public TaskList undo() throws IllegalStateException {
        if (!hasPrevious()) {
            throw new IllegalStateException("No more undo history!");
        }
        tasks = previousTasks;
        previousTasks = null;
        return tasks;
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
