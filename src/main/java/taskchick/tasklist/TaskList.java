package taskchick.tasklist;

import taskchick.storage.Storage;
import taskchick.task.Task;
import taskchick.task.Todo;

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
     * Prints tasks with their index for the user.
     *
     * @param tasksToPrint List to print from.
     * @return Tasks with the index starting from 1.
     */
    private String printNumberedTasks(ArrayList<Task> tasksToPrint) {
        StringBuilder sb = new StringBuilder();
        int counter = 1;
        for (Task task : tasksToPrint) {
            sb.append(counter + "." + task.toString() + "\n");
            counter++;
        }
        return sb.toString();
    }

    /**
     * Informs users of the size of their task list.
     *
     * @return Number of tasks in the list.
     */
    private String getListSize() {
        if (tasks.size() == 1) {
            return "Now you have 1 task in the list.";
        }

        return "Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Replaces this task list with an older version of the task list.
     *
     * @param olderTaskList Task list that is replacing this current task list.
     */
    private void replaceTaskList(ArrayList<Task> olderTaskList) {
        tasks.clear();
        for (Task olderTasks : olderTaskList) {
            tasks.add(olderTasks);
        }
    }

    /**
     * Reverts the task list to an older version and shows the user the reverted version.
     *
     * @param storage Where the task list in String can be converted to an ArrayList of tasks.
     * @param olderTaskListInString Older version of the task list in String format.
     * @return Response to user.
     */
    public String undoCommand(Storage storage, String olderTaskListInString) {
        ArrayList<Task> olderTaskList = storage.loadTasks(olderTaskListInString);
        replaceTaskList(olderTaskList);
        StringBuilder sb = new StringBuilder("Ctrl + Z activated!\n" + "Here is your list now:\n");
        sb.append(printNumberedTasks(tasks));
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
        sb.append(printNumberedTasks(tasks));
        return sb.toString();
    }

    /**
     * Marks a task as completed and informs the user.
     *
     * @param taskNumber Index of the task to be marked as completed.
     */
    public String completeTask(int taskNumber) {
        tasks.get(taskNumber - 1).markAsCompleted();
        return "Nice! I've marked this task as completed:\n" + tasks.get(taskNumber - 1).toString();
    }

    /**
     * Removes a task and informs the user.
     *
     * @param taskNumber Index of the task to be removed.
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
        ArrayList<Task> searchResults = getFoundResults(searchTerm);
        StringBuilder sb = new StringBuilder(printFoundGreeting(searchResults.size(), searchTerm));
        sb.append(printNumberedTasks(searchResults));
        return sb.toString();
    }

    /**
     * Saves tasks containing the search term into an ArrayList.
     * @param searchTerm
     * @return
     */
    private ArrayList<Task> getFoundResults(String searchTerm) {
        ArrayList<Task> searchResults = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(searchTerm)) {
                searchResults.add(task);
            }
        }
        return searchResults;
    }

    /**
     * Prints the opening message when users search for a term, depending on how many results their search
     * term yielded.
     *
     * @param numResults Number of search results yielded.
     * @param searchTerm Specified search term.
     * @return Customised message to user.
     */
    private String printFoundGreeting(int numResults, String searchTerm) {
        if (numResults == 0) {
            return "OOPS!!! You don't have any tasks containing the term \"" + searchTerm + "\".";
        } else if (numResults == 1) {
            return "You have 1 task containing the term \"" + searchTerm + "\":";
        } else if (numResults > 1) {
            return "You have " + numResults + " tasks containing the term \"" + searchTerm + "\":";
        } else {
            return "OOPS!!! Duke has encountered an unexpected error.";
        }
    }

    /**
     * Searches for deadlines and events on a specified date and compiles it for the user.
     *
     * @param scheduleDate Date the user is searching for.
     * @return Response to user.
     */
    public String displaySchedule(int[] scheduleDate) {
        ArrayList<Task> scheduledTasks = getScheduledTasks(scheduleDate);
        StringBuilder sb = new StringBuilder();
        sb.append(printScheduleGreeting(scheduledTasks.size()));
        sb.append(printNumberedTasks(scheduledTasks));
        return sb.toString();
    }

    /**
     * Saves deadlines and events scheduled on a specific date into an ArrayList.
     *
     * @param scheduleDate Specified date to match.
     * @return ArrayList with the deadlines and events scheduled on that specific date.
     */
    private ArrayList<Task> getScheduledTasks(int[] scheduleDate) {
        ArrayList<Task> scheduledTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (!(task instanceof Todo)) {
                int[] taskDate = task.getDate();
                if (Arrays.equals(scheduleDate, taskDate)) {
                    scheduledTasks.add(task);
                }
            }
        }
        return scheduledTasks;
    }

    /**
     * Prints the opening message when users call for a schedule, depending on how many tasks they have
     * scheduled on that specific day.
     *
     * @param listSize Number of tasks scheduled on a specific day.
     * @return Customised message to user.
     */
    private String printScheduleGreeting(int listSize) {
        if (listSize == 0) {
            return "You do not have any tasks on that day!";
        } else if (listSize == 1) {
            return "You have 1 task on that day:\n";
        } else if (listSize > 1) {
            return "You have " + listSize + " tasks on that day:\n";
        } else {
            return "OOPS!!! Duke encountered an unexpected error.";
        }
    }

}