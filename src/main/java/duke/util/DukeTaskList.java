package duke.util;

import duke.task.DukeTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DukeTaskList {

    private static final int DUKE_MAXIMUM_TASKS = 100;

    private List<DukeTask> userDukeTasks;
    private StringBuilder sb;

    /**
     * This constructor is used if a new List&lt;duke.task.DukeTask&gt; of initial capacity of
     * {@link #DUKE_MAXIMUM_TASKS} is
     * to be instantiated.
     */
    public DukeTaskList() {
        this.userDukeTasks = new ArrayList<>(DUKE_MAXIMUM_TASKS);
        this.sb = new StringBuilder();
    }

    /**
     * This constructor is used if an existing List&lt;duke.task.DukeTask&gt; is to be used.
     * @param userDukeTasks An existing and initialized List&lt;duke.task.DukeTask&gt; to be used.
     */
    public DukeTaskList(List<DukeTask> userDukeTasks) {
        this.userDukeTasks = userDukeTasks;
        this.sb = new StringBuilder();
    }

    /**
     * Creates a new duke.task.DukeTask and adds it into the current list of user {@link duke.task.DukeTask}.
     * The specified input is also mirrored to the user. The list of user {@link duke.task.DukeTask}
     * is then saved to the hard disk via {@link DukeStorage#save}.
     * @param inputTask User specified input that will be the name of the {@link duke.task.DukeTask}
     *                  to be added to the current list of {@link duke.task.DukeTask}.
     * @param ui duke.util.DukeUi object for displaying output to the user.
     * @param storage duke.util.DukeStorage object for updating the data file on the hard disk.
     */
    public void addToDukeTasks(DukeTask inputTask, DukeUi ui, DukeStorage storage) {
        try {
            userDukeTasks.add(inputTask);
            sb.setLength(0);
            sb.append("Got it. I've added this task:\n\t   ");
            sb.append(inputTask.toString());
            sb.append("\n\t Now you have " + userDukeTasks.size() + " tasks in the list.");
            ui.displayToUser(sb.toString());
            storage.save(userDukeTasks);
        } catch (IOException ex) {
            ui.displayFileLoadingError();
        }
    }

    /**
     * Displays the user-supplied list of tasks in a formatted style. This method will prepare the list by looping
     * through the List of tasks and printing each task with its index. Then it will call
     * {@link DukeUi#displayToUser(String)} to display the final formatted list.
     * @param ui duke.util.DukeUi object for displaying output to the user.
     */
    public void displayDukeTasks(DukeUi ui) {
        sb.setLength(0);
        sb.append("Here are the tasks in your list:\n\t ");
        for (int index = 0; index < userDukeTasks.size(); index++) {
            sb.append((index + 1) + "." + userDukeTasks.get(index).toString());
            if (index != (userDukeTasks.size() - 1)) {
                sb.append("\n\t ");
            }
        }
        ui.displayToUser(sb.toString());
    }

    /**
     * Checks if the specified task index has already been marked as complete. If it is not then mark the task as
     * complete and print out the name of this task.
     * @param taskIndexString Raw String index of the task following the printed list from running the "list" command.
     * @param ui duke.util.DukeUi object for displaying output to the user.
     * @param storage duke.util.DukeStorage object for updating the data file on the hard disk.
     */
    public void markDukeTaskComplete(String taskIndexString, DukeUi ui, DukeStorage storage) {
        try {
            int taskIndex = Integer.parseInt(taskIndexString);
            if (taskIndex < 1 || taskIndex >= userDukeTasks.size()) {
                ui.displayTaskIndexOutOfBounds();
            } else {
                DukeTask completedTask = userDukeTasks.get(taskIndex - 1);
                sb.setLength(0);
                if (completedTask.getTaskIsComplete()) {
                    sb.append("This task has already been marked as done!");
                } else {
                    completedTask.setTaskComplete();
                    sb.append("Nice! I've marked this task as done:\n\t   " + completedTask.toString());
                }
                ui.displayToUser(sb.toString());
                storage.save(userDukeTasks);
            }
        } catch (IOException ex) {
            ui.displayFileLoadingError();
        } catch (NumberFormatException ex) {
            ui.displayTaskInvalidIndex();
        }
    }

    /**
     * Deletes the specified task index.
     * @param taskIndexString Raw String index of the task to be deleted, following the printed list index from
     *                        the "list" command.
     * @param ui duke.util.DukeUi object for displaying output to the user.
     * @param storage duke.util.DukeStorage object for updating the data file on the hard disk.
     */
    public void deleteDukeTask(String taskIndexString, DukeUi ui, DukeStorage storage) {
        try {
            int taskIndex = Integer.parseInt(taskIndexString);
            if (taskIndex < 1 || taskIndex > userDukeTasks.size()) {
                ui.displayTaskIndexOutOfBounds();
            } else {
                sb.setLength(0);
                DukeTask deletedTask = userDukeTasks.get(taskIndex - 1);
                userDukeTasks.remove(taskIndex - 1);
                sb.append("Noted. I've removed this task:\n\t   ");
                sb.append(deletedTask.toString());
                sb.append("\n\t Now you have " + userDukeTasks.size() + " tasks in the list.");
                ui.displayToUser(sb.toString());
                storage.save(userDukeTasks);
            }
        } catch (IOException ex) {
            ui.displayFileLoadingError();
        } catch (NumberFormatException ex) {
            ui.displayTaskInvalidIndex();
        }
    }
}
