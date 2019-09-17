package duke;

import tasks.Task;

import java.util.ArrayList;

/**
 * Ui class that handles user interaction.
 * Stores methods that return messages addressed to the user.
 */
public class Ui {

    // ===========================================================================================================
    // =============================Messages indicating what the task has done====================================
    // ===========================================================================================================

    /**
     * Returns String message representing Duke's response
     * upon successful addition of the task.
     *
     * @param taskLst the ArrayList of tasks.
     * @return String message representing Duke's response.
     */
    public String getSuccessfulAddMsg(ArrayList<Task> taskLst) {
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                taskLst.get(taskLst.size() - 1), taskLst.size());
    }

    /**
     * Returns String message representing Duke's response
     * upon successful deletion of the task.
     *
     * @param taskLst the ArrayList of tasks.
     * @param deletedTask the Task that has been deleted.
     * @return String message representing Duke's response.
     */
    public String getSuccessfulDeleteMsg(Task deletedTask, ArrayList<Task> taskLst) {
        return String.format("Noted. I've removed this task:\n"
                + "%s\nNow you have %d tasks in the list.", deletedTask, taskLst.size());
    }

    /**
     * Returns String message representing Duke's response
     * upon successfully marking the task as completed.
     *
     * @param taskLst the ArrayList of tasks.
     * @param taskDoneIndex the index of the Task that has been marked as Done.
     * @return String message representing Duke's response.
     */
    public String getSuccessfulDoneMsg(int taskDoneIndex, ArrayList<Task> taskLst) {
        return String.format("Nice! I've marked this task as done:\n%s", taskLst.get(taskDoneIndex));
    }

    /**
     * Returns String message representing Duke's response
     * upon exiting the program.
     *
     * @return String message representing Duke's response.
     */
    public String getExitMsg() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns String message specifying the full list of commands.
     *
     * @return String message representing Duke's response.
     */
    public String getListOfCommands() {
        return "Listed here are the available commands in Duke:\n\n" +
                "1)  Adding a new task\n\n" +
                "\"todo [description]\": Adds a todo task to the list\n\"deadline [description] /by [date-time]\"" +
                ": Adds a deadline task to the list\nevent [description] /at [date-time]\": " +
                "Adds an event task to the list\n\nValid Date-Time formats:\n \u2022 3 October 2019 2359" +
                "\n \u2022 03/10/2019 2359\n \u2022 03102019 11:59PM\n \u2022 03 10 2019 11:59AM\n\n" +
                "Examples of valid commands:\n \u2022 todo run 2.4km\n \u2022 deadline submit assignment " +
                "/by 5 November 2019 0900\n \u2022 event play basketball /at 05/11/2019 9:00AM\n\n" +
                "\n2)  Listing all tasks\n\n" +
                "\"list\": Displays the current list of all tasks\n\n" +
                "\n3)  Marking a task as done\n\n" +
                "\"done [task-number]\": Marks the task with the specified task-number as done.\n\n" +
                "The task number of a task is indicated by its position in the list of tasks. (starting from 1)\n" +
                "This number can be displayed by using the list command.\n\n" +
                "\n4)  Deleting a task\n\n" +
                "\"delete [task-number]\": Removes the task with the specified task-number from the current list.\n\n" +
                "This command has similar syntax to the done command.\n\n" +
                "\n5)  Searching for task by keyword(s)\n\n" +
                "\"find [keywords delimited by a single space]\":\n" +
                "Finds and displays tasks whose description contains the given keyword(s)\n\n" +
                "Multiple keywords may be specified, delimited by a single space.\n" +
                "Note for find command:\n" +
                " \u2022 The order of the keywords do not matter.\n" +
                " \u2022 A task description is a match only if it contains all the keywords.\n\n" +
                "\n6)  Exiting the program\n\n" +
                "\"bye\": Displays the exit message and closes the program.";

    }

    // ============================================================================================================
    // ===============================Exception messages to be output to the user==================================
    // ============================================================================================================

    /**
     * Returns a String representing Duke's response
     * when the user supplies an invalid command.
     *
     * @return String message representing Duke's response.
     */
    public String getInvalidCommandMsg() {
        return "\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Returns a String representing Duke's response
     * when the user does not supply a description for a task.
     *
     * @return String message representing Duke's response.
     */
    public String getEmptyDescriptionMsg() {
        return "\u2639 OOPS!!! The description of a task cannot be empty.";
    }

    /**
     * Returns a String representing Duke's response
     * when the user does not supply a valid command for a deadline task.
     * (Missing out " /by ")
     *
     * @return String message representing Duke's response.
     */
    public String getInvalidDeadlineCmdMsg() {
        return "\u2639 The command is missing a \" /by \" required for a deadline task.\n" +
                "Required format: deadline [description] /by [date-time]";
    }

    /**
     * Returns a String representing Duke's response
     * when the user does not supply a valid command for an event task.
     * (Missing out " /at ")
     *
     * @return String message representing Duke's response.
     */
    public String getInvalidEventCmdMsg() {
        return "\u2639 The command is missing a \" /at \" required for a deadline task.\n" +
                "Required format: event [description] /at [date-time]";
    }

    /**
     * Returns a String representing Duke's response
     * when the user does not supply enough arguments
     * for an event or deadline task.
     *
     * @param command the command given by the user. ("event" or "deadline")
     * @return String message representing Duke's response.
     */
    public String getMissingArgumentsMsg(String command) {
        return "\u2639 Either the description of " + (command.equals("deadline")
                ? "a deadline " : "an event ") + "or its date-time is not provided." +
                        "\nRequired format: deadline [description] /by [date-time]";
    }

    /**
     * Returns a String representing Duke's response
     * when the user supplies a date-time in an invalid format.
     *
     * @return String message representing Duke's response.
     */
    public String getInvalidDateTimeFormatMsg() {
        String dateInstruction = "Example date formats allowed: 07101997, 07/10/1997, " +
                "07 10 1997, 7 October 1997.\n";
        String timeInstruction = "Example time formats allowed: 8:39AM, 0839.\n";
        String dtInstruction = "Example date-times allowed: 07/10/1997 0839, 7 October 8:39AM.";
        return "\u2639 You have entered an invalid date-time format!\n" + dateInstruction + timeInstruction
                + dtInstruction;
    }

    /**
     * Returns a String representing Duke's response
     * when the user does not supply a task number (for "done" or "delete" commands)
     *
     * @return String message representing Duke's response.
     */
    public String getMissingTaskNumMsg() {
        return "\u2639 A task number has to be specified.";
    }

    public String getInvalidNumFormatMsg() {
        return "\u2639 Please specify an integer as the index, also not followed by any spaces.";
    }

    /**
     * Returns a String representing Duke's response
     * when the user supplies an invalid, out of range task number (for "done" or "delete" commands)
     *
     * @param taskLst the ArrayList of tasks.
     * @return String message representing Duke's response.
     */
    public String getInvalidTaskNumMsg(ArrayList<Task> taskLst) {
        return String.format("\u2639 You have entered an invalid task number!" +
                " Please enter a number from between 1 to %d.", taskLst.size());
    }


}
