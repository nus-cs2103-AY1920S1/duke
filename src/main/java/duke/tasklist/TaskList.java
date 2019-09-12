package duke.tasklist;

import duke.datetime.DateTime;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Contains the task list.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Creates an empty task list if there is no previous session of Duke, or no tasks in the task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Fills the new  task list with tasks from previous session of Duke.
     *
     * @param savedTasks Task list from the previous session of Duke.
     */
    public TaskList(ArrayList<Task> savedTasks) {
        tasks = new ArrayList<>();
        tasks.addAll(savedTasks);
    }

    /**
     * Retrieves the task list.
     *
     * @return Task list.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Updates task description only while retaining task's position in list.
     *
     * @param index Index of the task to be updated.
     * @param description New description of the task.
     * @return Response to user.
     */
    public String updateDescription(int index, String description) {
        Task taskToUpdate = tasks.get(index - 1);
        String oldTaskString = taskToUpdate.toString();
        taskToUpdate.updateDescription(description);
        return "I have updated the task\n" + oldTaskString + "\n" + "as follows\n" + taskToUpdate.toString();
    }

    /**
     * Displays the contents of the task list to the user.
     */
    public String printTasks() {
        if (tasks.isEmpty()) {
            return "You do not have any tasks in your list.\n" + "Use 'help' to see how to add tasks" +
                    " to your list!";
        }

        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        return retrieveTasks(sb).toString();
    }

    /**
     * Retrieves the contents of the tasks list.
     *
     * @param sb StringBuilder to append to.
     * @return StringBuilder with tasks appended to it.
     */
    public StringBuilder retrieveTasks(StringBuilder sb) {
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + "." + tasks.get(i) + "\n");
        }
        return sb;
    }

    /**
     * Marks the task as completed and informs the user.
     *
     * @param taskNumber Index of the task to be marked as completed.
     */
    public String completeTask(int taskNumber) {
        tasks.get(taskNumber - 1).markAsDone();
        return "Nice! I've marked this task as completed:\n" + tasks.get(taskNumber - 1).toString();
    }

    /**
     * Deletes the task an informs the user.
     *
     * @param taskNumber Index of the task to be deleted.
     */
    public String deleteTask(int taskNumber) {
        String taskDescription = tasks.get(taskNumber - 1).toString();
        tasks.remove(taskNumber - 1);
        return "Noted. I've removed this task:\n" + taskDescription + "\n" + getListSize();
    }

    /**
     * Searches for tasks matching the search term and displays the matching tasks to the user.
     *
     * @param searchTerm Key word/phrase to search for.
     */
    public String findTasks(String searchTerm) {
        ArrayList<Task> searchResults = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(searchTerm)) {
                searchResults.add(task);
            }
        }

        if (searchResults.isEmpty()) {
            return "OOPS!!! You don't have any tasks containing the term \"" + searchTerm + "\".";
        }

        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < searchResults.size(); i++) {
            sb.append((i + 1) + "." + searchResults.get(i) + "\n");
        }
        return sb.toString();
    }

    /**
     * Searches for deadlines and events on a specified date and compiles it for the user.
     *
     * @param scheduleDate Date the user is searching for.
     * @return Response to user.
     */
    public String findSchedule(int[] scheduleDate) {
        // i know this method is super long i will change it
        ArrayList<Task> scheduledTasks = new ArrayList<>();
        // searching through tasks and ignoring todo types
        for (Task task : tasks) {
            if (!(task instanceof Todo)) {
                int[] taskDate = task.getDate();
                // checking if date of task matches date we are searching for
                if (Arrays.equals(scheduleDate, taskDate)) {
                    scheduledTasks.add(task);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        if (scheduledTasks.isEmpty()) {
            return "You do not have any tasks on that day!";
        } else if (scheduledTasks.size() == 1) {
            sb.append("You have 1 task on that day:\n");
        } else if (scheduledTasks.size() > 1) {
            sb.append("You have " + scheduledTasks.size() + " tasks on that day:\n");
        } else {
            return "OOPS!!! Duke encountered an unexpected error.";
        }

        for (Task task : scheduledTasks) {
            sb.append(task.toString() + "\n");
        }
        return sb.toString();
    }

    /**
     * Adds the task to the task list.
     *
     * @param task Task to be added.
     */
    public String addTask(Task task) {
        tasks.add(task);
        return "Got it. I've added this task:\n" + task.toString() + "\n" + getListSize();
    }

    /**
     * Informs users of the size of their task list.
     *
     * @return Number of tasks in the list.
     */
    public String getListSize() {
        if (tasks.size() == 1) {
            return "Now you have 1 task in the list.";
        }

        return "Now you have " + tasks.size() + " tasks in the list.";
    }

}