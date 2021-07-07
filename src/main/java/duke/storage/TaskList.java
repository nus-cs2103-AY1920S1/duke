package duke.storage;

import duke.tasks.Task;

import java.util.ArrayList;
/**
 * This task represents all the tasks stored by the user.
 */
public class TaskList{

    private ArrayList <Task> taskList;
    
    public TaskList (){
        taskList = new ArrayList <Task> ();
    }

    /**
     * 
     * @return Returns an integer representing the number of tasks in the tasklist.
     */
    public int size(){
        return taskList.size();
    }

    /**
     * Adds a new task to the tasklist.
     * Called by the storage class. 
     * Differs from the add() method by not providing a print statements.
     * 
     * @param newTask 
     */
    public void fileAdd(Task newTask){
        taskList.add(newTask);
    }

    /**
     * Adds a new task to the tasklist.
     * 
     * @param newTask
     * @return Returns a string detailing the process.
     */
    public String add(Task newTask){
        taskList.add(newTask);

        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:" +"\n" );
        sb.append(newTask.toString() +"\n");
        sb.append(String.format("Now you have %d tasks in the list.", taskList.size()));
        
        return sb.toString();
    }

    /**
     * Deletes a task from the tasklist.
     * 
     * @param index This index represents the index in which the task is placed in the arraylist.
     * @return Returns a string detailing the process.
     */
    public String deleteTask(int index){
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:" +"\n");
        sb.append(taskList.get(index) +"\n");
        taskList.remove(index);
        sb.append(String.format("Now you have %d tasks in the list.", taskList.size() ));        
        return sb.toString();
    }

    /**
     * Gets the task from the arraylist of taks.
     * 
     * @param index This index represents the index in which the task is placed in the arraylist.
     * @return Returns the task from the arraylist.
     */
    public Task getTask(int index){
        return taskList.get(index);
    }

    /**
     * Marks the chosen task as done.
     * 
     * @param index This index represents the index in which the task is placed in the arraylist.
     * @return Returns a string detailing the process.
     */
    public String doneTask(int index){
        Task currentTask = taskList.get(index);
        currentTask.setStatus(true);
        String message = "Nice! I've marked this task as done:\n" + currentTask;
        return message;
    }

    /**
     * Adds a tag to a specified task. Provides funcitonality for the tagging of 
     * an event.
     * 
     * @param index
     * @param tagName
     * @return 
     */
    public String addTag(int index, String tagName){
        taskList.get(index).addTag(tagName);
        String message = "Nice! I've tagged this task as " +tagName;
        return message;
    }

    /**
     * Returns a string representation of a taglist 
     * 
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        int size = taskList.size();
        for(int i = 0; i < size; i ++){
            Integer number = i + 1;
            String message = number + ". " + taskList.get(i);
            if(i < size - 1){
                sb.append(message + "\n");
            }else{
                sb.append(message);
            }
        }
        return sb.toString();
    }
} 