package seedu.duke.tasklist;

import seedu.duke.task.Task;
import java.util.ArrayList;

/**
 * TaskList class contains the list of Tasks (stored as an ArrayList) and methods to add, delete Tasks.
 */
public class TaskList {
    private ArrayList<Task> listOfTasks;

    /**
     * Constructor for TaskList class. Returns a TaskList object.
     *
     * @param taskList An ArrayList of Task objects.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.listOfTasks = taskList;
    }

    /**
     * Returns the number of Task objects currently stored in the list.
     *
     * @return Number of Task objects.
     */
    public int getSize(){
        return getArrayList().size();
    }

    /**
     * Returns the object Task at the specified int index.
     *
     * @param i Integer index.
     * @return Task object which is located at index i.
     */
    public Task getTask(int i){
        return getArrayList().get(i);
    }

    /**
     * Adds a new Task object into the list.
     *
     * @param newTask Task object that needs to be added into the list.
     */
    public void addTask(Task newTask){
        getArrayList().add(newTask);
    }

    /**
     * Deletes a Task object from a specified index.
     *
     * @param i Integer index of where the Task object (to be deleted) is in.
     */
    public void deleteTask(int i){
        getArrayList().remove(i);
    }

    /**
     * Returns the ArrayList<Task> inside the TaskList object
     *
     * @return ArrayList<Task>
     */
    public ArrayList<Task> getArrayList(){
        return this.listOfTasks;
    }

    /**
     * Returns another TaskLIst object which contains Task objects that is similar to the search string.
     *
     * @param searchTerm
     * @return
     */
    public TaskList findSimilarTasks (String searchTerm){

        ArrayList<Task> listOfTasks = this.getArrayList();
        ArrayList<Task> matchingTasks = new ArrayList<Task>();
        Task task = null; String taskDescription = "";

        for (int i = 0; i < listOfTasks.size(); i++){

            task = listOfTasks.get(i);
            taskDescription = task.getTaskName();

            if ( taskDescription.contains(searchTerm) ){
                matchingTasks.add(task);
            }
        }

        // Create a TaskList object to encapsulate the ArrayList<Task>
        return  new TaskList(matchingTasks);
    }

}
