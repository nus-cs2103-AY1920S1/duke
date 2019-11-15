import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a list of the user's tasks.
 * Provides methods for adding, deleting, and marking as done each task.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for task list. Initialises with an existing list of tasks.
     *
     * @param tasks List of existing tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor for empty task list. Assumes no existing tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns size of task list.
     *
     * @return Number of tasks in list.
     */
    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Displays all the tasks in the list.
     *
     * @return String of all the tasks in the list.
     */
    public String displayAllTasks() {
        Iterator<Task> iter = tasks.iterator();
        int i = 1;
        String response = "";
        while (iter.hasNext()) {
            Task task = iter.next();
            response += i + ". " + task.toString() + "\n";
            i++;
        }
        return response;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added.
     * @return String of added task.
     */
    public String addTask(Task task) {
        tasks.add(task);
        return Ui.outputTaskAdded(task, this);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index Index position of task to be deleted.
     * @return String of removed task.
     */
    public String deleteTask(int index) {
        Task removedTask = tasks.remove(index - 1);
        return Ui.outputTaskRemoved(removedTask, this);
    }

    /**
     * Marks a task in the list as done.
     *
     * @param index Index position of task to be marked as done.
     * @return String of task marked as done.
     */
    public String markTaskAsDone(int index) {
        Task task = tasks.get(index - 1);
        task.markAsDone();
        return Ui.outputTaskDone(task);
    }

    /**
     * Finds existing tasks that contain a provided keyword.
     *
     * @param keyword The keyword to search for.
     * @return String of tasks that match keyword.
     */
    public String findTasks(String keyword) {
        String exactMatches = "";
        String similarMatches = "";
        for (Task task : tasks) {
            if (containsKeyword(task.getDescription(), keyword)) {
                exactMatches += task.toString() + "\n";
            } else if (containsSimilar(task.getDescription(), keyword)) {
                similarMatches += task.toString() + "\n";
            }
        }
        if (exactMatches.equals("") && similarMatches.equals("")) {
            return "No matching tasks found.";
        }

        String response = "";
        if (!exactMatches.equals("")) {
            response += "The following matching tasks were found:\n" + exactMatches;
        }
        if (!similarMatches.equals("")) {
            response += "The following similar tasks were found:\n" + similarMatches;
        }
        return response;
    }

    /**
     * Checks if a keyword is found within a target string, ignoring case.
     *
     * @param target String to search in.
     * @param keyword String to search for.
     * @return Whether the string is found.
     */
    private boolean containsKeyword(String target, String keyword) {
        return target.toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Checks if a string similar to a keyword is found within a target string
     *
     * @param target String to search in.
     * @param keyword String to search for.
     * @return Whether the string is found.
     */
    private boolean containsSimilar(String target, String keyword) {
        String lowerCaseTarget = target.toLowerCase();
        String lowerCaseKeyword = keyword.toLowerCase();
        String keywordSingular = lowerCaseKeyword.replaceFirst("s$", "");
        String keywordWithoutContinuousTense = lowerCaseKeyword.replaceFirst("ing$", "");
        String keywordWithoutPastTense = lowerCaseKeyword.replaceFirst("ed$", "");
        return (keyword.length() > 2 && lowerCaseTarget.contains(keywordSingular)
                || keyword.length() > 5 && lowerCaseTarget.contains(keywordWithoutContinuousTense)
                || keyword.length() > 4 && lowerCaseTarget.contains(keywordWithoutPastTense));
    }
}
