import java.util.ArrayList;

/**
 * Represents the list of tasks that Duke holds.
 */
public class TaskList {

    private ArrayList<Task> list;
    private Ui ui;

    /**
     * Constructor to generate an empty ArrayList of Task type and takes Ui for print functionality.
     */
    public TaskList(Ui ui) {
        list = new ArrayList<>();
        this.ui = ui;
    }

    /**
     * Constructor to take in a populated ArrayList of Task type (i.e. from text file)
     * and takes Ui for print functionality.
     *
     * @param list ArrayList of Task Type
     */
    public TaskList(ArrayList<Task> list, Ui ui) {
        this.list = list;
        this.ui = ui;
    }

    /**
     * Adds a Task object into the list.
     *
     * @param task A Task object
     * @return confirmation message of task being added
     */
    public String addTask(Task task) {
        list.add(task);
        return ui.showAdd(task, list.size());
    }

    /**
     * Deletes a Task from the list.
     *
     * @param deleteIndex Index of selected Task Object, 1-based index
     * @return confirmation message of task being deleted
     */
    public String deleteTask(int deleteIndex) {
        assert deleteIndex <= list.size() : "deleteIndex should not be larger than amount of tasks in list";
        Task temp = list.remove(deleteIndex - 1);
        return ui.showDelete(temp, list.size());
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
        String message = "Yip! Here are the tasks in your list:";
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
        String message = "Bork! Here are the matching tasks in your list:";
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
        assert doneIndex <= list.size() : "doneIndex should not be larger than amount of tasks in list";
        list.get(doneIndex - 1).markAsDone();
        return ui.showDone(list.get(doneIndex - 1));
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
