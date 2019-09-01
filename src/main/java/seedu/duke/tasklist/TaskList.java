package seedu.duke.tasklist;

import seedu.duke.storage.Storage;
import seedu.duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listOfTasks;

    public TaskList(ArrayList<Task> taskList) {
        this.listOfTasks = taskList;
    }

    public int getSize(){
        return listOfTasks.size();
    }

    public Task getTask(int i){
        return listOfTasks.get(i);
    }

    public void addTask(Task newTask){
        this.listOfTasks.add(newTask);
    }

    public void deleteTask(int i){
        this.listOfTasks.remove(i);
    }

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
        TaskList a = new TaskList(matchingTasks);

        return a;

    }
}
