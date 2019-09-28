package duke.core;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import duke.tasks.Task;

/**
 * Represents the task list used to store the tasks. Provides methods to add a task to the list,
 * getting a List, getting a task using the index and removing
 * a task based on the index specified, getting the size of the list, 
 * setting the done status of a task and getting the ID of a task
 */
public class TaskList {

    private ArrayList<Task> taskList;
    private Storage storage;

    /**
     * Initialises the TaskList, creates an ArrayList to store the tasks,
     * and holds a reference to the main storage
     *
     * @param storage The main storage of the application.
     */
    public TaskList(Storage storage){
        this.taskList = new ArrayList<>();
        this.storage = storage;
    }

    /**
     * Initialises the TaskList, with a given ArrayList to store the tasks,
     * and holds a reference to the main storage
     *
     * @param list List to initialise with
     * @param storage The main storage of the application.
     */
    public TaskList(ArrayList<Task> list, Storage storage){
        this.taskList = list;
        this.storage = storage;
    }


    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     * @throws IOException Thrown when writing to file fails.
     */
    public void addToList(Task task) throws IOException {
        this.taskList.add(task);
        this.storage.overwriteStorage(taskList);

    }


    /**
     * Returns a ArrayList of type Task.
     *
     * @return An ArrayList of tasks.
     */
    public ArrayList<Task> getList(){
        return this.taskList;
    }


    /**
     * Returns a task at specified index.
     *
     * @param index Index of task to be retrieved.
     * @return Task of the corresponding index.
     */
    public Task getTaskAt(int index){
        return this.taskList.get(index - 1);
    }


    /**
     * Removes a task from the list using its reference
     *
     * @param task Task to be removed.
     * @throws IOException Thrown when writing to file fails.
     */
    public void removeFromList(Task task) throws IOException {
        boolean isRemoved = this.taskList.remove(task);
        if (isRemoved) {
            this.storage.overwriteStorage(taskList);
        }
    }


    /**
     * Sets task at specified index to done
     *
     * @param index Index of task to be set as done
     * @throws IOException Thrown when writing to file fails.
     */
    public void setDoneInList(int index) throws IOException {
        this.taskList.get(index - 1).setDone();
        this.storage.overwriteStorage(taskList);
    }

    /**
     * Returns the number of tasks in current list
     *
     * @return Number of tasks in current list
     */
    int getNumTasks(){
        return this.taskList.size();
    }

    /**
     * Returns the position of the specified task in current list
     *
     * @return Position of the specified task in current list
     */
    public int getTaskID(Task task) {
        return taskList.indexOf(task) + 1;
    }


    /**
     * Returns a list of tasks containing the specific keyword
     *
     * @return List of tasks containing the specific keyword
     */
    public List<Task> findTasks(String word) {
        ArrayList<Task> lst= new ArrayList<>();
        for (Task task: taskList) {
            if (task.getDescription().contains(word)) {
                lst.add(task);
            }
        }
        return lst;
    }


}

