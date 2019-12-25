package run;

import task.Deadline;
import task.Event;
import task.Task;

import java.util.ArrayList;

/**
 * TaskList for storing all current tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     * @param tasks arraylist of tasks to be stored in this TaskList
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Get the arraylist of tasks stored in this TaskList.
     * @return arraylist of tasks in this TaskList
     */
    public ArrayList<Task> getTasksArrayList() {
        return this.tasks;
    }

    /**
     * Prints all tasks currently stored in this TaskList through ui.
     * @return String formatted print of all tasks in this TaskList or "No tasks!" if empty
     */
    public String list() {
        if (this.tasks.size() == 0) {
            return Ui.NO_TASKS;
        }
        return Ui.printList(this.tasks);
    }

    /**
     * Adds a task into this TaskList.
     * @param task the task to be added in
     * @return String output to user after adding the task
     */
    public String add(Task task) {
        this.tasks.add(task);
        return Ui.printAdd(task, this.tasks.size());
    }

    /**
     * Marks a task within this TaskList as done.
     * @param taskNum int of task number that is to be marked as done (1-indexed)
     * @return String output to user after marking task as done
     */
    public String done(int taskNum) {
        if (taskNum > this.tasks.size()) {
            return Ui.NO_TASK_AT_DONE_PARAMETER;
        }
        Task currTask = this.tasks.get(taskNum - 1);
        currTask.setDone();
        return Ui.printDone(currTask);
    }

    /**
     * Deletes a task from within the TaskList.
     * @param taskNum int of task number that is to be deleted (1-indexed)
     * @return String output to user after deleting task
     */
    public String delete(int taskNum) {
        if (taskNum > this.tasks.size()) {
            return Ui.NO_TASK_AT_DELETE_PARAMETER;
        }
        Task currTask = this.tasks.get(taskNum - 1);
        this.tasks.remove(taskNum - 1);
        return Ui.printDelete(currTask, this.tasks.size());
    }

    /**
     * Searches through tasks in this TaskList to find matching tasks.
     * @param searchString String to be searched for within task names
     * @return String of formatted tasks that match search parameter
     */
    public String find(String searchString) {
        ArrayList<Task> passedTasks = new ArrayList<Task>();
        for (int i = 1; i <= this.tasks.size(); i++) {
            Task currTask = this.tasks.get(i - 1);
            if (currTask.getName().contains(searchString)) {
                passedTasks.add(currTask);
            }
        }
        return Ui.printFind(passedTasks);
    }

    /**
     * Updates the DateTime of a Event or Deadline task in the tasklist.
     * @param taskNum int of task number that is to be deleted (1-indexed)
     * @return String output after changing task's DateTime
     */
    public String reschedule(int taskNum, String newDateTime) {
        if (taskNum > this.tasks.size()) {
            return Ui.NO_TASK_AT_RESCHEDULE_PARAMETER;
        }
        Task currTask = this.tasks.get(taskNum - 1);
        if (currTask instanceof task.Event) {
            ((Event) currTask).setLocalDateTime(newDateTime);
            return Ui.printReschedule(currTask);
        } else if (currTask instanceof task.Deadline) {
            ((Deadline) currTask).setLocalDateTime(newDateTime);
            return Ui.printReschedule(currTask);
        } else {
            return Ui.WRONG_RESCHEDULE_TASK_TYPE;
        }
    }
}