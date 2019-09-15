package seedu.duke;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

/**
 * TaskList is a class that represents the list of tasks and contains operations to manipulate the list.
 */
public class TaskList {

    private static ArrayList<Task> taskList;
    static int numTaskDone = 0;
    static int numTaskNotDone = 0;
    static int numDonePastWeek = 0;
    static int numDonePastMonth = 0;
    static int numDonePastYear = 0;
    static int numTodo = 0;
    static int numEvent = 0;
    static int numDeadline = 0;

    /**
     * Constructor of the Tasklist class.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Another constructor of the Tasklist class with the list of tasks as parameter.
     *
     * @param taskList the arraylist of tasks
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds the task to the list of tasks.
     *
     * @param t the task to be added
     */
    public void addTask(Task t) {
        taskList.add(t);
    }

    /**
     * Deletes the task from the list of tasks directly.
     *
     * @param t the task to be deleted
     */
    public void deleteTask(Task t) {
        taskList.remove(t);
    }


    /**
     * Returns the task from the list of tasks.
     *
     * @param index the index of the task to be returned
     * @return the task
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Returns the size of the list of tasks.
     *
     * @return the size of the list of tasks
     */
    public static int getSize() {
        return taskList.size();
    }

    /**
     * Loops through the list of tasks to find the tasks containing the keywords.
     *
     * @param keyword the keyword that need to be matched
     * @return an arraylist tasks containing the keywords
     */
    public ArrayList<Task> findMatching(String keyword) {
        ArrayList<Task> matchingList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            String[] arr = taskList.get(i).getDescription().split(" ");
            for (int j = 0; j < arr.length; j++) {
                if (arr[j].equalsIgnoreCase(keyword)) {
                    matchingList.add(taskList.get(i));
                    break;
                }
            }
        }
        return matchingList;
    }

    /**
     * Sort the tasks according to the dateTime and the type of tasks.
     */
    public void sortTask() {
        Collections.sort(this.taskList);
    }

    /**
     * Calculate the statistics including the number of tasks done and those yet to be done.
     */
    public static void calculateStats() {
        for (Task t: taskList) {
            if (t instanceof Todo) {
                numTodo++;
            } else if (t instanceof Event) {
                numEvent++;
            } else if (t instanceof Deadline) {
                numDeadline++;
            }

            if (t.isDone) {
                numTaskDone++;
                if (doneLastYear(t)) {
                    numDonePastYear++;
                }
                if (doneLastMonth(t)) {
                    numDonePastMonth++;
                }
                if (doneLastWeek(t)) {
                    numDonePastWeek++;
                }
            }
        }
        numTaskNotDone = getSize() - numTaskDone;
    }

    /**
     * Resets the statistics to initial values of 0.
     */
    public static void resetStats() {
        numTaskDone = 0;
        numTaskNotDone = 0;
        numDonePastWeek = 0;
        numDonePastMonth = 0;
        numDonePastYear = 0;
        numTodo = 0;
        numEvent = 0;
        numDeadline = 0;
    }

    /**
     * Returns true if the task was done last week.
     *
     * @param t the task to check
     * @return true if the task was done last week. Else, return false
     */
    public static boolean doneLastWeek(Task t) {
        Duration duration = Duration.between(t.getDoneDateTime(), LocalDateTime.now());
        return (duration.toDays() <= 7);
    }

    /**
     * Returns true if the task was done within the last month.
     *
     * @param t the task to check
     * @return true if the task was done last month. Else, return false
     */
    public static boolean doneLastMonth(Task t) {
        Duration duration = Duration.between(t.getDoneDateTime(), LocalDateTime.now());
        return (duration.toDays() <= 30);
    }

    /**
     * Returns true if the task was done within last year.
     *
     * @param t the task to check
     * @return true if the task was done last year. Else, return false
     */
    public static boolean doneLastYear(Task t) {
        Duration duration = Duration.between(t.getDoneDateTime(), LocalDateTime.now());
        return (duration.toDays() <= 365);
    }

}
