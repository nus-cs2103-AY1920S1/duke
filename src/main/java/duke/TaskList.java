package duke;

import duke.task.Task;

import java.util.LinkedList;

/**
 * Stores list of tasks and handles changes to this list (such as addition, deletion, modification of tasks) based on
 * user input.
 */
public class TaskList {

    private LinkedList<Task> taskList;

    public TaskList() {
        this.taskList = new LinkedList<Task>();
    }

    public TaskList(LinkedList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Gets list of tasks stored in this instance of the TaskList.
     *
     * @return List of tasks.
     */
    public LinkedList<Task> getTasks() {
        return taskList;
    }

    /**
     * Marks a certain task as done in the taskList when user inputs done command.
     *
     * @param taskNum Task to be marked done.
     * @return Task marked done.
     * @throws DukeException if the taskNum passed in is not valid.
     */
    public Task markDone(int taskNum) throws DukeException {
        if (taskNum + 1 > taskList.size() || taskNum + 1 < 0) {
            throw new DukeException("\u2639 OOPS!!! This task does not exist.");
        }

        Task t = taskList.remove(taskNum);
        t.markAsDone();
        taskList.add(taskNum, t);

        assert t.getStatusIcon().equals("\u2713");

        return t;
    }

    /**
     * Deletes a certain task from the taskList when user inputs delete command.
     *
     * @param taskNum Task to be deleted.
     * @return Task deleted.
     * @throws DukeException if the taskNum passed in is not valid.
     */
    public Task deleteTask(int taskNum) throws DukeException {
        int taskListSize = taskList.size();

        if (taskNum + 1 > taskListSize || taskNum + 1 < 0) {
            throw new DukeException("\u2639 OOPS!!! This task does not exist.");
        }

        Task t = taskList.remove(taskNum);
        assert taskList.size() == taskListSize - 1;

        return t;
    }

    /**
     * Adds a certain task to the taskList when user inputs an add command (more specifically deadline, event, and todo).
     *
     * @param t Task to add.
     */
    public void addTask(Task t) {
        int taskListSize = taskList.size();

        taskList.add(t);

        assert taskList.size() == taskListSize + 1;
    }

    public LinkedList<Task> findTasks(String toFind) {
        int taskListSize = taskList.size();
        LinkedList<Task> result = new LinkedList<>();
        CharSequence cs = toFind;

        for (int k = 0; k < taskListSize; k++) {
            Task t = taskList.get(k);
            if (t.getDescription().contains(cs)) {
                result.add(t);
            } else {
                result.add(new Task("fake task"));
            }
        }

        assert taskList.size() == taskListSize;

        return result;
    }

}
