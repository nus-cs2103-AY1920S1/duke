import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Stores the tasks of the user.
 */
public class TaskList implements Serializable {
    private List<Task> taskList;
    private List<Reminder> reminders = new ArrayList<>();
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Returns the number of tasks present in the task list.
     *
     * @return The number of tasks present in the task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns the task present in the specific index of the task list.
     *
     * @param index The target index.
     * @return The task present in the specific index of the task list.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Marks as complete the task present in the specific index of the task list.
     *
     * @param index The target index.
     */
    public void markTaskAsDone(int index) throws OutOfBoundsDeletionException {
        try {
            taskList.get(index).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfBoundsDeletionException("No task with index number " + (index + 1) + " on your tasklist!");
        }
    }

    /**
     * Deletes the task present in the specific index of the task list and returns it.
     *
     * @param index The target index.
     * @return The deleted task.
     * @throws OutOfBoundsDeletionException If the target index is not present in the task list.
     */
    public Task deleteTask(int index) throws OutOfBoundsDeletionException {
        try {
            return taskList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfBoundsDeletionException("No task with index number " + (index + 1) + " on your tasklist!");
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param newTask The new task to be added.
     */
    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    public List<Task> getMatchedTasks(String keyword) {
        List<Task> matchedTasks = new ArrayList<>();
        for (Task task: taskList) {
            if (task.toString().contains(keyword)) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }

    public String getReminders() {
        StringBuilder sb = new StringBuilder();
        if (reminders.isEmpty()) {
            sb.append("There are no reminders right now!");
        } else {
            sb.append("Here are your upcoming tasks: ");
            for (Reminder reminder : reminders) {
                sb.append("\n" + reminder.toString());
            }
        }
        return sb.toString();
    }

    public void createReminders() {
        for (Task task : taskList) {
            Optional<Reminder> potentialReminder = Reminder.createReminderIfValid(task);
            if (potentialReminder.isPresent()) {
                reminders.add(potentialReminder.get());
            }
        }
    }
}
