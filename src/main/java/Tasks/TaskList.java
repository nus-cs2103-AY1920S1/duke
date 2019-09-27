package Tasks;

import java.util.ArrayList;

/**
 * Acts as a list of tasks which can be manipulated through
 * create/read/delete/update operations.
 */
public class TaskList {
    ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Constructor for TaskList object
     *
     * @param taskList is the ArrayList of tasks going to be stored
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds more tasks into TaskList once at a time
     *
     * @param task to be stored
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes 1 task from TaskList
     *
     * @param itemNum is the index of the task to be deleted
     */
    public String deleteTask(int itemNum) {
        assert(itemNum>=0 && itemNum<taskList.size()):"itemNum does not fall within range";
        String toReturn = "";
        toReturn += ("Noted. I've removed this task:\n");
        toReturn += ("  " + taskList.get(itemNum) + "\n");
        taskList.remove(itemNum);
        toReturn += ("Now you have " + taskList.size() + " in the list.");
        return toReturn;
    }

    /**
     * Marks 1 task from TaskList as done
     *
     * @param itemNum is the index of the task to be marked as done
     */
    public String markTaskDone(int itemNum) {
        assert(itemNum>=0 && itemNum<taskList.size()):"itemNum does not fall within range";
        String toReturn = "";
        taskList.get(itemNum).doneJob();
        toReturn += ("Nice! I've marked this task as done:\n");
        toReturn += ("  " + taskList.get(itemNum) + "\n");
        toReturn += ("Now you have " + taskList.size() + " in the list.");
        return toReturn;
    }

    /**
     * Iterates though TaskList and provides a list of tasks stored.
     */
    public String showAllTasks() {
        String toReturn = "Here are the tasks in your list:\n";
        for(int i = 0; i < taskList.size(); i++) {
            toReturn += (i + 1) + "." + taskList.get(i);
            if(i != taskList.size()-1) toReturn += "\n";
        }
        return toReturn;
    }

    /**
     * Finds duplicate of input task description in current taskList
     *
     * @param input is the description of task
     * @param taskType is the task type
     * return true if description of input equals description of a task in tasklist and
     * if the task is an instance of current task
     */
    public boolean isDuplicate(String input, String taskType){
        boolean isDuplicated = false;
        String taskTypeToCheck = "";
        for(int i = 0; i < taskList.size(); i++) {
            if(taskList.get(i) instanceof Todo){
                taskTypeToCheck = "Todo";
            } else if(taskList.get(i) instanceof Event){
                taskTypeToCheck = "Event";
            } else if(taskList.get(i) instanceof Deadline){
                taskTypeToCheck = "Deadline";
            }
            if(input.trim().equals(taskList.get(i).getDescription().trim()) &&
            taskType.equals(taskTypeToCheck)){
                isDuplicated = true;
            }
        }
        return isDuplicated;
    }

    /**
     * Getter for ArrayList of Tasks
     *
     * @return ArrayList of Tasks
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Setter for ArrayList of Tasks
     *
     * ArrayList of Tasks stored into TaskList
     */
    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

}
