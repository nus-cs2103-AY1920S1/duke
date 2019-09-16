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
     * @param number Specified index of task in task list.
     */
    public void deleteTask(int number) {
        listOfTasks.remove(number);
    }

    /**
     * prints out the tasks in the stored task list.
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
     * Sets a task in a specified position in the task list as done.
     *
     * @param number Specified index of task in task list.
     */
    public void setTaskAsDone(int number) {
        Task temp = this.listOfTasks.get(number - 1);
        temp.setDone();
    }

    /**
     * Returns a task in a specified position in the task list.
     *
     * @param number Specified index of task in task list.
     * @return Task in specified position in task list.
     */
    public Task getTask(int number) { //what about case where it is empty?
        return this.listOfTasks.get(number);
    }

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
