package seedu.duke.stubs;

import seedu.duke.tasks.Task;
import seedu.duke.tasks.TaskManager;
import seedu.duke.tasks.ToDoTask;
import seedu.duke.tasks.exceptions.DuplicateTaskException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Simplified version of {@link seedu.duke.tasks.TaskManager}. {@code TaskManagerStub} is just used to simulate
 * some basic functionality of {@code TaskManager} in test environment.
 */
public class TaskManagerStub extends TaskManager {
    private int numberOfTasks;
    private List<Task> tasks;

    /**
     * Constructor for <code>TaskManagerStub</code>, which handles execution involving tasks that are
     * stored by Duke chat bot.
     *
     * @param filePath File location to save tasks data that are stored by Duke chat bot.
     * @throws Exception If Duke chat bot fails to load initial data.
     */
    public TaskManagerStub(String filePath) throws Exception {
        super(filePath);
        numberOfTasks = 0;
        tasks = new ArrayList<>();
    }

    @Override
    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    @Override
    public Task removeTask(long taskID) {
        numberOfTasks -= 1;
        return new ToDoTask("dummy todo task");
    }

    @Override
    public void addTask(Task task) throws DuplicateTaskException, IOException {
        numberOfTasks += 1;
    }

    @Override
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
