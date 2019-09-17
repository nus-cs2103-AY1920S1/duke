import java.util.ArrayList;
import java.lang.StringBuilder;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Overloaded constructor to initialise empty arraylist
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * To get the number of tasks
     * @return the size of the arraylist in an integer form
     */
    public int getSize(){
        return this.tasks.size();
    }

    /**
     * To add a task into the tasklist
     * @param task to be added
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * To delete a certain task
     * @param task to be deleted
     */
    public void delete(Task task) {
        this.tasks.remove(task);
    }

    /**
     * To get a task in the tasklist
     * @param index of the task the user wishes to retrieve
     * @return the specified task
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Prints a list of the tasklist
     */
    public String showList() {
        // prints a list of all tasks stored in this tasklist
        StringBuilder wholeList = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            wholeList.append(i + 1 + ". " + tasks.get(i) + "\n");
        }
        return wholeList.toString();
    }



    /**
     * Gets the whole tasklist
     * @return an arraylist of tasks
     */
    public ArrayList<Task> getWholeList() {
        return this.tasks;
    }
}
