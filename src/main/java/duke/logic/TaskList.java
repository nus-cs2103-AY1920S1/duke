package duke.logic;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Contains a task list and methods able to perform features on the tasks in the taskList.
 */
public class TaskList {
    private ArrayList<Task> listOfTasks;

    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }
    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    /**
     * Returns the stored task list.
     *
     * @return stored task list.
     */
    public ArrayList<Task> getListOfTasks() {
        return listOfTasks;
    }

    /**
     * Adds a new task to the end of the stored task list.
     *
     * @param newTask
     */
    public void addTask(Task newTask) {
        listOfTasks.add(newTask);
    }

    /**
     * Deletes a task from the stored task list given by the index called.
     * @param index Specified index of task in task list.
     */
    public void deleteTask(int index) {
        assert index >= 0 : "index passed to delete a task in taskList should be greater than or equals to zero";
        listOfTasks.remove(index);
    }

    /**
     * Returns a task in a specified position in the task list.
     *
     * @param index Specified index of task in task list.
     * @return Task in specified position in task list.
     */
    public Task getTask(int index) {
        assert index >= 0 : "index should be greater than or equals to zero";
        return this.listOfTasks.get(index);
    }

    /**
     * Sets a task in a specified position in the task list as done.
     *
     * @param index Specified index of task in task list.
     */
    public void setTaskAsDone(int index) {
        Task newlyDoneTask = this.listOfTasks.get(index);
        newlyDoneTask.setDone();
    }

    /**
     * Prints out the tasks in the stored task list.
     */
    public String printList() {
        StringBuilder temp = new StringBuilder();
        int counter = 1;
        for (Task task : this.listOfTasks) {
            temp.append("     ");
            temp.append(counter);
            temp.append(".");
            temp.append(task);
            temp.append("\n");
            counter++;
        }
        return temp.toString();
    }

    /**
     * Finds and returns tasks in the stored task list with a particular keyword.
     * @param keyword keyword passed in by user to search for tasks
     * @return tasks in stored task list with particular keyword
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> temp = new ArrayList<>();
        for(Task task : this.listOfTasks) {
            if(task.getDescription().contains(keyword)) {
                temp.add(task);
            }
        }
        return temp;
    }
}
