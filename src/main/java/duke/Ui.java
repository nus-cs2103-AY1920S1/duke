package duke;

import duke.task.Task;

public class Ui {

    private final MainWindow mainWindow;

    public Ui(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * Show welcome message.
     * Often used in the beginning of the program.
     */
    public void showWelcome() {
        StringBuilder builder = new StringBuilder();
        builder.append("Hello! I'm Duke");
        builder.append("\n");
        builder.append("What can I do for you?");
        this.mainWindow.addDukeDialog(builder.toString());
    }

    public void showBye() {
        this.mainWindow.addDukeDialog("Bye. Hope to see you again soon!");
    }

    /**
     * Show all tasks.
     * This method would format the list of tasks
     * and display them neatly.
     *
     * @param tasks the list of tasks to be shown
     */
    public void showTasks(TaskList tasks) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            builder.append((i + 1) + ". " + tasks.get(i).toString());
            builder.append("\n");
        }
        this.mainWindow.addDukeDialog(builder.toString());
    }

    /**
     * Show notification of successful completion.
     * This method would notify the user that the task
     * is being marked as done.
     *
     * @param task the task that is completed
     */
    public void showDone(Task task) {
        StringBuilder builder = new StringBuilder();
        builder.append("Nice! I've marked this task as done:");
        builder.append("\n");
        builder.append(task.toString());
        this.mainWindow.addDukeDialog(builder.toString());
    }

    /**
     * Show notification of successful deletion.
     * This method would notify the user that the task
     * is successfully deleted.
     *
     * @param task the task that is deleted
     * @param tasks the list of tasks left after deletion
     */
    public void showDelete(Task task, TaskList tasks) {
        StringBuilder builder = new StringBuilder();
        builder.append("Noted. I've removed this task:");
        builder.append("\n");
        builder.append(task.toString());
        builder.append("\n");
        builder.append("Now you have " + tasks.size() + " tasks in the list.");
        this.mainWindow.addDukeDialog(builder.toString());
    }

    /**
     * Show notification of successful addition.
     * This method would notify the user that the task
     * is successfully added.
     *
     * @param task the task that is added
     * @param tasks the list of tasks left after addition
     */
    public void showTaskAdded(Task task, TaskList tasks) {
        StringBuilder builder = new StringBuilder();
        builder.append("Got it. I've added this task:");
        builder.append("\n");
        builder.append(task.toString());
        builder.append("\n");
        builder.append("Now you have " + tasks.size() + " tasks in the list.");
        this.mainWindow.addDukeDialog(builder.toString());
    }

    public void showLoadingError() {
        this.mainWindow.addDukeDialog("Sorry, I am not able to load the saved file.");
    }

    public void showSaveError() {
        this.mainWindow.addDukeDialog("Sorry, I am not able to save your tasks.");
    }

    public void showInvalidCommandError() {
        this.mainWindow.addDukeDialog("Sorry, I do not understand what you mean.");
    }

    public void showInvalidDateError() {
        this.mainWindow.addDukeDialog("Sorry, I do not understand the date format.");
    }

}
