package task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> task;
    private int counter;

    public TaskList() {
        this.task = new ArrayList<>();
        this.counter = 0;
    }

    public TaskList(ArrayList<Task> task) {
        this.task = task;
        this.counter = task.size();
    }

    public TaskList(ArrayList<Task> task, int counter) {
        this.task = task;
        this.counter = counter;
    }

    /**
     * Adds task into the current list of tasks.
     * 
     * @param t Task to be added.
     * @return New TaskList that contains an ArrayList of tasks and number of tasks.
     */
    public TaskList addTask(Task t) {
        task.add(t);
        Ui.printAddedTask(t, counter);
        counter++;
        return new TaskList(task, counter);
    }

    /**
     * Deletes task into the current list of tasks.
     * 
     * @param index Index of task to be deleted.
     * @return New TaskList that contains an ArrayList of tasks and number of tasks.
     */
    public TaskList deleteTask(int index) {
        Task deletedTask = task.remove(index);
        counter--;
        Ui.printDeleteTask(deletedTask, counter);
        return new TaskList(task, counter);
    }

    /**
     * Mark the task that is in the list as done.
     * 
     * @param index Index of task to be marked as done.
     * @return New TaskList that contains an ArrayList of tasks and number of tasks.
     */
    public TaskList doneTask(int index) {
        Task markAsDoneTask = task.get(index);
        markAsDoneTask.markAsDone();
        Ui.printDoneTask(markAsDoneTask);
        return new TaskList(task, counter);
    }

    public void findTask(String keyWord) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task t : this.task) {
            if (t.getDescription().contains(keyWord)) {
                foundTasks.add(t);
            }
        }
        Ui.printFoundTask(new TaskList(foundTasks));
    }

    public ArrayList<Task> getTasks() {
        return this.task;
    }

    public int getCounter() {
        return this.counter;
    }
}