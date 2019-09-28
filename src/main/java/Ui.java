import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is responsible of taking in User inputs and to display information
 * after the User's command.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Instantiate a Ui object.
     */
    protected Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Display a welcome message for the User.
     */
    protected String showWelcome() {
        String logo = "  ____        _        \n"
                + " |  _ \\ _   _| | _____ \n"
                + " | | | | | | | |/ / _ \\\n"
                + " | |_| | |_| |   <  __/\n"
                + " |____/ \\__,_|_|\\_\\___|\n";

        String welcome = logo
                + "\n Hello! I'm Duke\n"
                + " What can I do for you?\n";

        return welcome;
    }

    /**
     * Prompt the user to enter command.
     * @return the Users command.
     */
    protected String enterCommand() {
        return scanner.nextLine();
    }

    /**
     * Display the list of tasks to the User.
     * @param taskList The tasks list.
     * @return the tasks list.
     */
    protected String showList(ArrayList<Task> taskList) {
        String showList = "";
        for (int x = 0; x < taskList.size(); x++) {
            showList = showList + (x + 1) + ". " + taskList.get(x) + "\n";
        }
        return showList;
    }

    /**
     * Display the error when there is an empty .txt file.
     * @param e Loading error when no task is found.
     */
    protected String showLoadingError(DukeException e) {
        return e.toString();
    }

    /**
     * Display message when a task is added.
     * @param taskList The tasks list.
     * @return the UI message shown to the User.
     */
    protected String getAddedMessage(ArrayList<Task> taskList) {
        String addedMessage = " Got it. I've added this task:\n"
                + "   " + taskList.get(taskList.size() - 1)
                + "\n Now you have " + taskList.size() + " tasks in the list.";

        return addedMessage;
    }

    /**
     * Display message when a task is deleted.
     * @param taskList The tasks list.
     * @param deleted The task deleted.
     * @return the UI message shown to the User.
     */
    protected String getDeletedMessage(ArrayList<Task> taskList, String deleted) {
        String deletedMessage = " Got it. I've added this task:\n"
                + "   " + deleted
                + "\n Now you have " + taskList.size() + " tasks in the list.";

        return deletedMessage;

    }

    /**
     * Display message and also the change in status icon to show the task is done.
     * @param taskDoneStr String of the task done.
     */
    protected String getDoneMessage(String taskDoneStr) {

        String doneMessage = " Nice! I've marked this tasked as done:\n"
                + taskDoneStr;

        return doneMessage;
    }

    /**
     * Find the task that the User inputs.
     * @param foundTaskList task list consists of all the similar words.
     * @return List of tasks found.
     */
    protected String showFoundMessage(ArrayList<Task> foundTaskList) {
        String foundMessage = "";
        for (int x = 0; x < foundTaskList.size(); x++) {
            foundMessage = foundMessage + 1 + ". " + foundTaskList.get(x) + "\n";
        }

        return foundMessage;
    }

    protected String showViewMessage(ArrayList<Task> viewTasklist) {
        String viewMessage = "";
        for (int x = 0; x < viewTasklist.size(); x++) {
            viewMessage = viewMessage + 1 + ". " + viewTasklist.get(x) + "\n";
        }
        if (viewMessage.equals("")) {
            viewMessage = "No task found";
        }

        return viewMessage;
    }

}
