import java.util.ArrayList;

/*
 * Represents the list of tasks that Duke holds
 */
public class TaskList {

    private ArrayList<Task> list;

    /*
     * Default Constructor to generate an empty ArrayList of Task type
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /*
     * Constructor to take in a populated ArrayList of Task type (i.e. from text file)
     * @param list ArrayList of Task Type
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /*
     * Adds a Task object into the list
     * @param task A Task object
     */
    public void addTask(Task task) {
        list.add(task);
        System.out.println("Got it. I've added this task:\n" + task
                + "\nNow you have " + list.size() + " tasks in the list.");
    }

    /*
     * Deletes a Task from the list
     * @param deleteIndex Index of selected Task Object, 1-based index
     */
    public void deleteTask(int deleteIndex) {
        Task temp = list.remove(deleteIndex - 1);
        System.out.println("Noted. I've removed this task:\n" + temp
                + "\nNow you have " + list.size() + " tasks in the list.");
    }

    /*
     * Returns selected Task object from list
     * @param taskIndex Index of selected Task object, 1-based index
     * @return Task object
     */
    public Task getTask(int taskIndex) {
        return list.get(taskIndex - 1);
    }

    /*
     * Prints out the list of tasks that Duke holds
     */
    public void showTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
    }

    /*
     * Sets boolean variable isDone of selected Task object to true
     * @param doneIndex Index of selected Task object, 1-based index
     */
    public void setDoneTask(int doneIndex) {
        list.get(doneIndex - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n"
                + list.get(doneIndex - 1));
    }

    /*
     * Returns the list of tasks that Duke holds as an object
     * @return ArrayList of Task type
     */
    public ArrayList<Task> getTaskList() {
        return list;
    }
}
