import java.util.ArrayList;

/**
 * TaskList class represents the list of Tasks Todo, Deadline and Event.
 */
public class TaskList {

    private ArrayList<Task> list;

    /**
     * Constructor TaskList.
     * @param list List of Task objects.
     */
    public TaskList (ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Constructor TaskList that creates a new ArrayList of Task.
     */
    public TaskList () {
        this.list = new ArrayList<>();
    }

    /**
     * Prints the ArrayList when user enters list command.
     */
    public void printForList() {
        for(int i = 1; i <= list.size(); i++) {
            System.out.println(i + "." + list.get(i-1));
        }
    }

    /**
     * Marks the task as done.
     * @param x task number.
     */
    public void taskDone(int x) {
        list.get(x).markAsDone();
    }

    /**
     * Prints the specific task.
     * @param x task number.
     * @return Task
     */
    public Task taskPrint(int x) {
        return list.get(x);
    }

    /**
     * Prints the last added Task.
     * @return Task last added to TaskList.
     */
    public Task printLatest() {
        return list.get(list.size() - 1);
    }

    /**
     * Returns the size of ArrayList.
     * @return the size of ArrayList.
     */
    public int size() {
        return list.size();
    }

    /**
     * Removes the Task from ArrayList.
     * @param x index of Task to remove.
     */
    public void remove(int x) {
         list.remove(x);
    }

    /**
     * Adds new Task to the ArrayList.
     * @param newTask newTask to add.
     */
    public void add(Task newTask) {
        list.add(newTask);
    }

    /**
     * Returns the last Task from ArrayList.
     * @return last Task.
     */
    public Task getLast() {
        return list.get(list.size() -1);
    }

    /**
     * Returns the ArrayList.
     * @return ArrayList of Task.
     */
    public ArrayList<Task> getList() {
        return list;
    }

}
