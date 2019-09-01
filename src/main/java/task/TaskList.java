package task;

import exception.DukeException;
import filewriter.Storage;


import java.util.ArrayList;

/**
 * Object containing data structure to store Tasks.
 */
public class TaskList {
    ArrayList <Task> schedule = new ArrayList<> ();
    public int task_Num;
    public boolean isFirst ;

    /**
     * Constructor for Task List.
     * Called when generating Task List from txt file.
     * @param storage contains ArrayList of Task specified from txt file.
     */
    public TaskList(Storage storage){
        try {
            this.schedule = storage.getSchedule();
            task_Num = schedule.size();
            if (task_Num == 0){
                isFirst = true;
            } else {
                isFirst = false;
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Constructor for Task List.
     */
    public TaskList(){
        this.schedule = new ArrayList<Task>();
        task_Num = schedule.size();
        if (task_Num == 0){
            isFirst = true;
        } else {
            isFirst = false;
        }
    }

    /**
     * Constructor for Task List.
     * @param schedule ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> schedule){
        this.schedule = schedule;
        task_Num = schedule.size();
        if(task_Num == 0){
            isFirst = true;
        } else {
            isFirst = false;
        }
    }

    /**
     * Gets Task corresponding to specified index.
     * @param index indicates which task in the task list to return.
     * @return Task as specified by index.
     */
    public Task getTask(int index){
        return schedule.get(index);
    }

    /**
     * Marks a Task in task list as done.
     * @param index Specifies which task to mark as done.
     * @return completed Task.
     * @throws NullPointerException
     * @throws IndexOutOfBoundsException
     * @throws NumberFormatException
     * @throws DukeException
     */
    public Task complete(int index)
            throws NullPointerException, IndexOutOfBoundsException, NumberFormatException, DukeException{
        Task completedTask = schedule.get(index);
        completedTask.markAsDone();
        return completedTask;
    }

    /**
     * Adds new Task to TaskList
     * @param task
     * @throws DukeException
     */
    public void addTask(Task task) throws DukeException {
        schedule.add(task);
        task_Num++;
    }

    /**
     * Deletes Task from TaskList.
     * @param index
     * @return deleted Task.
     * @throws NullPointerException
     * @throws IndexOutOfBoundsException
     * @throws NumberFormatException
     * @throws DukeException
     */
    public Task remove(int index)
            throws NullPointerException, IndexOutOfBoundsException, NumberFormatException, DukeException{
        Task removed_Task = schedule.get(index);
        schedule.remove(index);
        task_Num --;
        return removed_Task;
    }

    /**
     * Used to get ArrayList<Task> schedule from TaskList.
     * Called when execute method of FindCommand is called.
     * @return ArrayList<Task>
     */
    public ArrayList<Task> getList(){
        return schedule;
    }

    /**
     * toString method of TaskList
     * @return contents of TaskList. i.e. tasks.
     */
    public String toString(){
        String output = "";
        if (task_Num != 0){
            for (int index = 0; index < task_Num; index ++){
                Task task = schedule.get(index);
                output += ("     " + (index + 1) + "." + task.toString() + "\n");
            }
            return output.substring(0, output.length() - 1);
        } else {
            return output;
        }
    }
}
