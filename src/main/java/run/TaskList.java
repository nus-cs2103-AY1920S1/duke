package run;

import task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasksArrayList() {
        return this.tasks;
    }

    public void list() {
        if(this.tasks.size() == 0) {
            Ui.showMessage("No tasks!");
            return;
        }
        Ui.printList(this.tasks);
    }

    public void add(Task task) {
        this.tasks.add(task);
        Ui.printAdd(task, this.tasks.size());
    }

    public void done(int task_num) {
        if(task_num > this.tasks.size()) {
            Ui.showError("No task at that number! (Marking as done unsuccessful)");
            return;
        }
        Task curr_task = this.tasks.get(task_num - 1);
        curr_task.setDone();
        Ui.printDone(curr_task);
    }

    public void delete(int task_num) {
        if(task_num > this.tasks.size()) {
            Ui.showError("No task at that number! (Deletion unsuccessful)");
            return;
        }
        Task curr_task = this.tasks.get(task_num - 1);
        this.tasks.remove(task_num - 1);
        Ui.printDelete(curr_task, this.tasks.size());
    }
}