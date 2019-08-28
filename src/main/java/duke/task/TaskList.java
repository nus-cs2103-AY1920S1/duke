package duke.task;

import java.util.ArrayList;

/**
 * Class represents a List of Tasks that the user has to complete.
 */
public class TaskList {
    private ArrayList<Task> tasksList;

    /**
     * Class constructor that initializes an empty list of tasks.
     */
    public TaskList(){
        this.tasksList = new ArrayList<>();
    }

    /**
     * Class constructor that specifies the list of tasks this TaskList represents.
     * @param tasksList ArrayList of tasks to be represented.
     */
    public TaskList(ArrayList<Task> tasksList){
        this.tasksList = tasksList;
    }

    /**
     * Adds the task t to the list of tasks.
     *
     * @param t Task to add.
     */
    public void addTask(Task t){
        tasksList.add(t);
    }

    /**
     * Deletes a task at an specific index of the TaskList.
     *
     * @param index Index to delete at.
     */
    public void deleteTask(int index){
        tasksList.remove(index);
        Task.totalTasks--;
    }

    /**
     * Returns a Task at a specific index of the TaskList.
     * @param index Index of task to get.
     * @return Task at specific index.
     */
    public Task get(int index){
        return tasksList.get(index);
    }

    /**
     * Returns the current size of the TaskList.
     *
     * @return Size of TaskList.
     */
    public int size(){
        return tasksList.size();
    }

    /**
     * Returns a String representation of the entire TaskList.
     *
     * @return String representation for every task in the list.
     */
    @Override
    public String toString(){
        String toReturn = "";
        int size = tasksList.size();
        for (int i = 0; i < size; i++) {
            Task curr = tasksList.get(i);
            toReturn = toReturn + (i + 1) + "." + curr.toString() + "\n";
        }
        return toReturn.trim();
    }
}
