import java.util.ArrayList;

/**
 * Represents the list of tasks that Duke holds.
 */
public class TaskList {

    private ArrayList<Task> list;

    /**
     * Default Constructor to generate an empty ArrayList of Task type.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Constructor to take in a populated ArrayList of Task type (i.e. from text file).
     *
     * @param list ArrayList of Task Type
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a Task object into the list.
     *
     * @param task A Task object
     * @return confirmation message of task being added
     */
    public String addTask(Task task) {
        list.add(task);
        String message = "Got it. I've added this task:\n" + task
                + "\nNow you have " + list.size() + " tasks in the list.";
        return message;
    }

    /**
     * Deletes a Task from the list.
     *
     * @param deleteIndex Index of selected Task Object, 1-based index
     * @return confirmation message of task being deleted
     */
    public String deleteTask(int deleteIndex) {
        assert deleteIndex < list.size() : "deleteIndex should not be larger than amount of tasks in list";
        Task temp = list.remove(deleteIndex - 1);
        String message = "Noted. I've removed this task:\n" + temp
                + "\nNow you have " + list.size() + " tasks in the list.";
        return message;
    }

    /**
     * Returns selected Task object from list.
     *
     * @param taskIndex Index of selected Task object, 1-based index
     * @return Task object
     */
    public Task getTask(int taskIndex) {
        return list.get(taskIndex - 1);
    }

    /**
     * Prints out the list of tasks that Duke holds.
     *
     * @return list of tasks
     */
    public String showTaskList() {
        String message = "Here are the tasks in your list:";
        for (int i = 0; i < list.size(); i++) {
            message += "\n" + (i + 1) + "." + list.get(i);
        }
        return message;
    }

    /**
     * Prints out the Tasks whose description matches the search term.
     *
     * @param searchTerm Keyword by user for search
     * @return list of tasks with matching search term
     */
    public String searchTaskList(String searchTerm) {
        String message = "Here are the matching tasks in your list:";
        int noOfSearches = 1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDescription().contains(searchTerm)) {
                message += "\n" + noOfSearches + "." + list.get(i);
                noOfSearches += 1;
            }
        }
        return message;
    }

    /**
     * Sets boolean variable isDone of selected Task object to true.
     *
     * @param doneIndex Index of selected Task object, 1-based index
     * @return confirmation message of task being done
     */
    public String setDoneTask(int doneIndex) {
        /*
        // Code for testing assertion
        doneIndex = 20;
         */
        assert doneIndex < list.size() : "doneIndex should not be larger than amount of tasks in list";
        list.get(doneIndex - 1).markAsDone();
        String message = "Nice! I've marked this task as done:\n"
                + list.get(doneIndex - 1);
        return message;
    }

    /**
     * Returns the list of tasks that Duke holds as an object.
     *
     * @return ArrayList of Task type
     */
    public ArrayList<Task> getTaskList() {
        return list;
    }
}
