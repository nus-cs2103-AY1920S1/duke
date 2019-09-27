package duke.tasklist;

import duke.exception.InvalidArgumentException;
import duke.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents and contains the tasks list and operations surrounding the tasks list.
 */
public class TaskList {

    private static ArrayList<Task> tasks;
    private Ui ui;

    /**
     * An empty constructor to create the TaskList object.
     */
    public TaskList() {
        this.ui = new Ui();
    }

    /**
     * Creates the TaskList object with the specified taskList.
     * @param taskList The specified ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        TaskList.tasks = taskList;
        this.ui = new Ui();
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public void setTaskList(ArrayList<Task> tasks) {
        TaskList.tasks = tasks;
    }

    /**
     * Adds the specified task to the tasks list and prints the necessary output.
     * @param task The specified task to be added.
     * @return the appropriate response to the user after the task is added.
     */
    public String getResponseToAddTask(Task task) {
        tasks.add(task);
        return ui.getAddTaskResponse(task);
    }

    /**
     * Marks the task with the specified task number as done and prints the necessary output.
     * @param taskNumber The specified number of the task that is to be marked as done.
     * @return the appropriate response to the user after the task is marked as done.
     */
    public String getResponseToMarkAsDone(int taskNumber) {
        Task taskDone;
        if (taskNumber - 1 > tasks.size()) {
            taskDone = null;
        }
        taskDone = tasks.get(taskNumber - 1);
        taskDone.markAsDone();
        return ui.getDoneTaskResponse(taskDone);
    }

    /**
     * Deletes the task with the specified task number from the tasks list and prints the necessary output.
     * @param taskNumber The specified number of the task to be deleted from the tasks list.
     * @return the appropriate response to the user after the task is deleted from the task list.
     */
    public String getResponseToDeleteTask(int taskNumber) throws InvalidArgumentException {
        Task task;
        if (taskNumber - 1 > tasks.size()) {
            task = null;
        }
        task = tasks.remove(taskNumber - 1);
        return ui.getDeleteTaskResponse(task);
    }

    /**
     * Find the matching tasks in the task list with the specified keyword.
     * @param keyword The specified keyword that the matching tasks will contain.
     * @return the appropriate response to the user with the matching tasks.
     */
    public String getResponseToFindTask(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : TaskList.tasks) {
            String desc = task.getDesc();
            if (desc.contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        return ui.getFindTaskResponse(matchingTasks);
    }

}
