import java.util.ArrayList;

public class TaskList {
    
    private ArrayList<Task> list; 

    public TaskList() {
    	this.list = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> list) {
    	this.list = list;
    }

    /**
     * Returns the task stored in the list
     *
     * @param index Index of the task in the list
     */
    public Task get(int index) {
    	return this.list.get(index);
    }

    /**
     * Deletes the task stored in the list
     *
     * @param index Index of the task in the list
     */
    public void delete(int index) {
    	this.list.remove(index);
    }

    /**
     * Marks the task stored in the list as done
     *
     * @param index Index of the task in the list
     */
    public void done(int index) {
        this.list.get(index).markAsDone();
    }

    /**
     * Returns the list for read only access
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Adds the task to the list
     *
     * @param task Task object to be added
     */
    public void add(Task task) {
    	this.list.add(task);
    }

    /**
     * Returns the size of the list
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Prints all the tasks in the list
     */
    public void printList() {
    	System.out.println("     Here are the tasks in your list:");
        for (int i = 1; i <= list.size(); i++) {
            System.out.println("     " + i + "." + list.get(i - 1));
        }
    }
}
