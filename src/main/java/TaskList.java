import javafx.scene.layout.Priority;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Handles operations to the task list and contains the task list.
 */
public class TaskList {
    /**
     * Enumerate relevant task type options.
     */
    private static final String ADD_TYPE_DEADLINE = "deadline";
    private static final String ADD_TYPE_EVENT = "event";

    /**
     * ArrayList for tracking Tasks on the list.
     */
    private ArrayList<Task> list;
    /**
     * String for printing Tasks on the list.
     */
    private String listString;
    /**
     * Constant indentation after start of line (formatting).
     */
    private static String INDENT = "    ";

    /**
     * Constructor for an empty TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
        this.listString = "";
    }

    /**
     * Constructor for loading a prior ArrayList of tasks.
     * @param tasklist ArrayList of tasks
     */
    public TaskList(ArrayList<Task> tasklist) {
        this.list = new ArrayList<>();
        this.listString = "";
        for (Task task : tasklist) {
            addTask(task);
        }
    }

    /**
     * Return ArrayList of tasks.
     * @return ArrayList of Tasks
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Return string with all tasks.
     * @return String with all tasks
     */
    public String getListString() {
        return listString;
    }

    /**
     * Add new todo task to task list.
     * @param newTask Details of incoming task
     */
    public void addTask(Task newTask) {
        list.add(newTask);
        updateTodoString();
    }

    /**
     * Add new deadline/event task to task list.
     * @param details Details of incoming task
     * @param dateTime Details on relevant date to task
     * @param taskType Type of task to be added
     */
    public void addDateTask(String details, String dateTime, String taskType) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTimeByDateTime = LocalDateTime.parse(dateTime, formatter);
        assert taskType.equals(ADD_TYPE_EVENT) || taskType.equals(ADD_TYPE_DEADLINE) : "Wrong task type";
        addTask(new Task(details, taskType, dateTimeByDateTime, false));
    }

    /**
     * Delete task from task list based on index on list.
     * @param index Index of task to be removed
     */
    public void deleteTask(int index) {
        list.remove(index);
        updateTodoString();
    }

    /**
     * Mark task as complete within the task list.
     * @param index Index of task to be marked complete
     */
    public void markTaskAsDone(int index) {
        list.get(index).markAsDone();
        updateTodoString();
    }

    /**
     * Helper function for findMatchingTasksString returning ArrayList of matching tasks.
     * @param keyword Search keyword for finding matching tasks
     * @return ArrayList of matching tasks
     */
    public ArrayList<Task> findMatchingTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<Task>();
        for (Task task : list) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Returns formatted list of matching tasks to be printed.
     * @param keyword Search keyword for finding matching tasks
     * @return String list of matching tasks
     */
    public String findMatchingTasksString(String keyword) {
        ArrayList<Task> matchingTasksList = findMatchingTasks(keyword);
        int counter = 1;
        String tempListString = "" + INDENT + " ";
        for (Task task : matchingTasksList) {
            tempListString += counter + ". " + task + '\n' + INDENT + " ";
            counter++;
        }
        return tempListString;
    }

    /**
     * Update tasks in String of tasks.
     */
    public void updateTodoString() {
        listString = "" + INDENT + " ";
        for (int i = 0; i < list.size(); i++) {
            listString += (i + 1) + ". " + list.get(i);
            if (i != (list.size() - 1)) {
                listString += '\n' + INDENT + ' ';
            }
        }
    }

    /**
     * Prints a sorted list, either by earliest to latest or completion status.
     * @param keyword Indicates sorting type, either "completion" or "datetime"
     * @return String of sorted list, by completion or by datetime
     */
    public String returnSortedTasksString(String keyword) {
        ArrayList<Task> taskListCopy = new ArrayList<Task>();
        for (Task task : list) {
            taskListCopy.add(task);
        }
        if (keyword.equals("datetime")) {
            Collections.sort(taskListCopy, new SortByEarliestDeadline());
        } else {
            Collections.sort(taskListCopy, new SortByCompleted());
        }
        int counter = 1;
        String tempListString = "" + INDENT + " ";
        for (Task task : taskListCopy) {
            tempListString += counter + ". " + task + '\n' + INDENT + " ";
            counter++;
        }
        return tempListString;
    }
}
