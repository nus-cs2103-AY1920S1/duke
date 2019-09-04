package run;

import task.Task;

import java.util.ArrayList;

/**
 * TaskList for storing all current tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList
     * @param tasks arraylist of tasks to be stored in this TaskList
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Get the arraylist of tasks stored in this TaskList
     * @return arraylist of tasks in this TaskList
     */
    public ArrayList<Task> getTasksArrayList() {
        return this.tasks;
    }

    /**
     * Prints all tasks currently stored in this TaskList through ui
     */
    public void list() {
        if(this.tasks.size() == 0) {
            Ui.showMessage("No tasks!");
            return;
        }
        Ui.printList(this.tasks);
    }

    /**
     * Adds a task into this TaskList
     * @param task the task to be added in
     */
    public void add(Task task) {
        this.tasks.add(task);
        Ui.printAdd(task, this.tasks.size());
    }

    /**
     * Marks a task within this TaskList as done
     * @param task_num int of task number that is to be marked as done (1-indexed)
     */
    public void done(int task_num) {
        if(task_num > this.tasks.size()) {
            Ui.showError("No task at that number! (Marking as done unsuccessful)");
            return;
        }
        Task curr_task = this.tasks.get(task_num - 1);
        curr_task.setDone();
        Ui.printDone(curr_task);
    }

    /**
     * Deletes a task from within the TaskList
     * @param task_num int of task number that is to be deleted (1-indexed)
     */
    public void delete(int task_num) {
        if(task_num > this.tasks.size()) {
            Ui.showError("No task at that number! (Deletion unsuccessful)");
            return;
        }
        Task curr_task = this.tasks.get(task_num - 1);
        this.tasks.remove(task_num - 1);
        Ui.printDelete(curr_task, this.tasks.size());
    }

    public void find(String searchString) {
        ArrayList<Task> passedTasks = new ArrayList<Task>();
        for(int i = 1; i <= this.tasks.size(); i++) {
            Task curr_task = this.tasks.get(i-1);
            if(curr_task.getName().contains(searchString)) {
                passedTasks.add(curr_task);
            }
        }
        Ui.printFind(passedTasks);
    }
}