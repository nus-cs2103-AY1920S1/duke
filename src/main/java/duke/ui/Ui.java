package duke.ui;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidParameterException;

import java.lang.StringBuilder;

/**
 * This is the user interface of the Duke program. The user interface will format the information. Return the system
 * output of duke with given inputs. Welcome messages, error messages, display messages for addition, deletion, marked
 * as done etc of tasks will be done here.
 */
public class Ui implements UserInterface {

    /**
     * Shows a welcome message for the user.
     * @return a string representation of the welcome message for the user
     */
    public String showWelcome() {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("    ------------------------------------------------------------\n");
        outputBuilder.append("    Hello! I'm Duke\n");
        outputBuilder.append("    What can I do for you?\n");
        outputBuilder.append("    ------------------------------------------------------------\n");
        String output = outputBuilder.toString();
        return output;
    }

    /**
     * Shows an error message for the user when data cannot be saved to <code>data/duke.txt</code>.
     * @return a string representation of the error message
     */
    public String showSaveError() {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("    ------------------------------------------------------------\n");
        outputBuilder.append("    ⚠️ Cannot be saved to 'data/duke.txt' ⚠️");
        outputBuilder.append("    ------------------------------------------------------------\n");
        String output = outputBuilder.toString();
        return output;
    }

    /**
     * Shows an error message when the user inputs a invalid command.
     * @param invalidCommand the invalid command entered
     * @return a string representation of the error message
     */
    public String showInvalidCommandError(InvalidCommandException invalidCommand) {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("    ------------------------------------------------------------\n");
        outputBuilder.append("    🙁 OOPS!! Invalid Command: " + invalidCommand.getInvalidCommand() + " 🙁\n");
        outputBuilder.append("    ------------------------------------------------------------\n");
        String output = outputBuilder.toString();
        return output;
    }

    /**
     * Shows an error message when the user inputs a invalid parameter for the command.
     * @param invalidParameter the invalid parameter entered
     * @return a string representation fo the error message
     */
    public String showInvalidParametersError(InvalidParameterException invalidParameter) {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("    ------------------------------------------------------------\n");
        outputBuilder.append("    🙁 OOPS!! Invalid Parameters: " + invalidParameter.getInvalidParameter() + " 🙁\n");
        outputBuilder.append("    ------------------------------------------------------------\n");
        String output = outputBuilder.toString();
        return output;
    }

    /**
     * Shows a loading error message for the user. This will occur when Duke is unable to load the
     * file path specified in Duke. See {@link duke.main.Duke} for more information.
     * @return a string representation of the error message
     */
    public String showLoadingError() {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("* FAILED TO LOAD DATA\n");
        String output = outputBuilder.toString();
        return output;
    }

    /**
     * Shows an error message for the user with the constituent line number and the its content during parsing.
     * @param lineCount the line number of the error
     * @param line the contents of the line itself
     * @return a string representation of the error message
     */
    public String showLineError(int lineCount, String line) {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("* Unable to parse line " + lineCount + " : " + line + "\n");
        String output = outputBuilder.toString();
        return output;
    }

    /**
     * Reads the command entered by the user.
     * @param userInput the input entered by the user
     * @return the input entered by the user
     */
    public String readCommand(String userInput) {
        return userInput;
    }

    /**
     * Shows the current tasks in the list.
     * @param taskList the list to be displayed
     * @return a string representation of the list in table form
     */
    public String showTable(String taskList) {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("    ============================================================\n");
        outputBuilder.append("    Here are the tasks in your list:\n");
        outputBuilder.append("    ------------------------------------------------------------\n");
        outputBuilder.append(taskList);
        outputBuilder.append("    ============================================================\n");
        String output = outputBuilder.toString();
        return output;
    }

    /**
     * Shows the schedule.
     * @param schedule the schedule to be displayed
     * @return a string representation of the schedule in table form
     */
    public String showSchedule(String schedule) {

        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("    I have fetched your schedule: \n\n");
        outputBuilder.append(schedule);
        return outputBuilder.toString();
    }

    /**
     * Shows the results of tasks found in the list.
     * @param list the list of tasks to be displayed
     * @return a string representation of the results of tasks found in the list in table form
     */
    public String showResultsFound(String list) {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("    ============================================================\n");
        outputBuilder.append("    Here are the matching tasks in your list:\n");
        outputBuilder.append("    ------------------------------------------------------------\n");
        outputBuilder.append(list);
        outputBuilder.append("    ============================================================\n");
        String output = outputBuilder.toString();
        return output;
    }

    /**
     * Shows a message that informs the user that a task has been added into the list.
     * @param task the task to be added into the list
     * @param size the number of tasks in the list
     * @return a string representation of the add information
     */
    public String showAddInformation(String task, int size) {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("     ------------------------------------------------------------\n");
        outputBuilder.append("     Got it. I've added this task:\n");
        outputBuilder.append("     ➕  " + task + "\n");
        outputBuilder.append("     Now you have " + size + " tasks in the list.\n");
        outputBuilder.append("     ------------------------------------------------------------\n");
        String output = outputBuilder.toString();
        return output;
    }

    /**
     * Shows a message that informs the user that the task has been marked as done.
     * @param task the task that is marked done
     * @return a string representation of the task information marked as done
     */
    public String showMarkedAsDone(String task) {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("    ------------------------------------------------------------\n");
        outputBuilder.append("    Nice! I've marked this task as done:\n");
        outputBuilder.append("      " + task + "\n");
        outputBuilder.append("    ------------------------------------------------------------\n");
        String output = outputBuilder.toString();
        return output;
    }

    /**
     * Shows a message that informs the user that the task has been deleted.
     * @param task the task that is deleted
     * @param size the number of tasks remaining in the list
     * @return a string representation of the deleted message
     */
    public String showDeletedMessage(String task, int size) {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("    ------------------------------------------------------------\n");
        outputBuilder.append("    Noted. I've removed this task:\n");
        outputBuilder.append("    🗑  " + task + "\n");
        outputBuilder.append("    Now you have " + size + " tasks in the list.\n");
        outputBuilder.append("    ------------------------------------------------------------\n");
        String output = outputBuilder.toString();
        return output;
    }

    /**
     * Shows a exit message for the user.
     * @return a string representation of the exit message
     */
    public String showExitMessage() {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("    ------------------------------------------------------------\n");
        outputBuilder.append("    Bye. Hope to see you again soon!\n");
        outputBuilder.append("    ------------------------------------------------------------\n");
        String output = outputBuilder.toString();
        return output;
    }

    /**
     * Shows a message that says that the reminder has been set.
     * @param task the task whose reminder has been set
     * @param dateToRemind the date of the reminder set
     * @return a string representation of the task whose reminder that has been set
     */
    public String showSetReminder(String task, String dateToRemind) {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("    ------------------------------------------------------------\n");
        outputBuilder.append("    Acknowledged. I've set the reminder for this task:\n");
        outputBuilder.append("    ⏰ " + task + "\n");
        outputBuilder.append("    Reminder has been set to: " + dateToRemind + "\n");
        outputBuilder.append("    ------------------------------------------------------------\n");
        String output = outputBuilder.toString();
        return output;
    }

}
