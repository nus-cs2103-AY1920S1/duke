package seedu.duke.ui;

import java.util.Scanner;

import seedu.duke.model.task.TaskList;

/**
 * Defines a Ui class.
 * Interacts with user of the program.
 */
public class Ui {

    private Scanner sc;
    private String input;
    private String output;
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Creates an instance of Ui.
     * Initialises a Scanner object stored in the sc attribute of this Ui.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Returns a String that displays the Duke Logo to the user.
     * @return String representation of Duke Logo to be displayed to the user.
     */
    public static String showLogo() {
        assert !logo.equals("") : "Logo to be displayed to user is an empty String.";
        return logo;
    }

    /**
     * Returns a message to display to the user an About message about this Duke program.
     * @return An about message to display to the user information about this Duke program.
     */
    public static String aboutMessage() {
        String intro = "Hi! I'm Weihong, developer for this implementation of Duke - "
                + "a task-managing chatbot designed at NUS for educational purposes, with details as follows!\n\n";

        String productInfo = "Product Name: Duke\n"
                + "Version: V2.5\n"
                + "Category: Personal Assistant Chatbot\n"
                + "Software Requirements: Java 11; Mac / Linux / Windows OS\n"
                + "Developer: Neo Weihong\n\n";

        String codeInfo = "This product is free for non-commercial use and its code is "
                + "publicly available open-source via: https://github.com/whneo97/duke.\n\n";

        String credits = "Space background by Andy Holmes (https://unsplash.com/photos/LUpDjlJv4_c)\n"
                + "User profile icon by Smashicons (https://www.flaticon.com/authors/smashicons)\n"
                + "Duke profile icon by Freepik (https://www.flaticon.com/authors/freepik)\n"
                + "Font by Andreas Larsen (https://github.com/larsenwork/Gidole)\n\n";

        String conclusion = "Hope you'll enjoy trying out this simple program!";

        return intro + productInfo + codeInfo + credits + conclusion;
    }

    /**
     * Returns a String that displays the welcome message to the user.
     * @return String representation of the welcome message to be displayed to the user.
     */
    public static String showWelcome() {
        assert !logo.equals("") : "Welcome message to be displayed to user is an empty String.";
        return "\nHello! I'm Duke\n"
                + "What can I do for you?";
    }

    /**
     * Returns the next line of text obtained from user input.
     * @return Next line of text obtained from user input via Scanner stored in sc attribute.
     */
    public String readCommand() {
        input = sc.nextLine();
        return input;
    }

    /**
     * Stores the separator line between program inputs / outputs to be displayed to the user.
     */
    public void showLine() {
        output = "____________________________________________________________";
    }

    /**
     * Stores a loading error message to be displayed to the user.
     * Pre-condition: An error is thrown to the client that calls this method, specifically when loading a file.
     */
    public void showLoadingError() {
        output = "Error loading specified file.";
    }

    /**
     * Stores a notification message to be displayed to the user with the given message.
     * Pre-condition: A notification is to be displayed to client that calls this method.
     * @param message Notification message that is to be displayed to a client that calls this method.
     */
    public void showNotification(String message) {
        output += "\n\nNotification: " + message;
    }

    /**
     * Stores an error message to be displayed to the user with the given message.
     * Pre-condition: An error is thrown to the client that calls this method.
     * @param message Error message from Exception that is thrown to a client that calls this method.
     */
    public void showError(String message) {
        output = "OOPS!!! " + message + "\n\n"
                + "Input \'help [command1] [command2] etc.\' for usage information of requested commands or "
                + "simply input \'help\' for the full list of commands known to this program.";
    }

    /**
     * Stores a message to be displayed to the user that a Task has been added to a TaskList.
     * Presumably called by the Task class when a client adds it to a TaskList.
     * @param addedMessage Message to indicate to the user that a Task has been added to a TaskList.
     */
    public void showAddedMessage(String addedMessage) {
        output = addedMessage;
    }

    /**
     * Stores a message to be displayed to the user that a Task has been removed from a TaskList.
     * Presumably called by the Task class when a client removes it from a TaskList.
     * @param deletedMessage Message to indicate to the user that a Task has been removed from a TaskList.
     */
    public void showDeletedMessage(String deletedMessage) {
        output = deletedMessage;
    }

    /**
     * Stores a message to be displayed to the user that a Task has been marked as done (completed).
     * Presumably called by the Task class when a client marks it as done.
     * @param doneMessage Message to indicate to the user that a Task has been marked as done.
     */
    public void showDoneMessage(String doneMessage) {
        output = doneMessage;
    }

    /**
     * Stores a message to be displayed to the user that a Task has been marked as undone (yet to complete).
     * Presumably called by the Task class when a client marks it as undone.
     * @param undoneMessage Message to indicate to the user that a Task has been marked as undone.
     */
    public void showUndoneMessage(String undoneMessage) {
        output = undoneMessage;
    }

    /**
     * Stores a message to the be displayed to the user the message that a search on a TaskList has been completed.
     * Completed message is followed by search results.
     * @param searchResults TaskList containing list of search results based on keyword from user input.
     */
    public void showSearchResults(TaskList searchResults) {
        output = "Here are the matching tasks in your list:\n"
                + searchResults;
    }

    /**
     * Stores a message to be displayed to the user the String representation of a TaskList.
     * Presumably called along with the toString method of the TaskList class.
     * @param tasks TaskList to be displayed as its String representation.
     */
    public void showTaskList(TaskList tasks) {
        output = "Here are the tasks in your list:\n"
                + tasks.toString();
    }

    /**
     * Stores a message to be displayed to the user the String representation of a randomly generated TaskList.
     * Presumably called along with the toString method of the TaskList class.
     * @param tasks Randomly generated TaskList to be displayed as its String representation.
     */
    public void showRandomTaskList(TaskList tasks) {
        output = "Here are your randomly-generated list of Tasks:\n" + tasks
                + "\n\n" + "To revert back to the previous version of the TaskList, use the \'undo\' command or input "
                + "\'help\' for more information on all commands known to Duke.";
    }

    /**
     * Stores a message to be displayed to the user the String representation of a TaskList that has just been sorted.
     * Presumably called along with the toString method of the TaskList class after a TaskList has been sorted.
     * @param tasks TaskList to be displayed as its String representation.
     */
    public void showSortedTaskList(TaskList tasks) {
        output = "Sorting successful! Here are the tasks in your list:\n"
                + tasks.toString();
    }

    /**
     * Stores a message to display to the user that the tasklist is currently empty.
     */
    public void showEmptyTaskListMessage() {
        output = "You currently have no tasks in the task list!\n\nYou may add a Todo, Deadline or Event to the "
                + "tasklist. Input \'help\' to find out more.";
    }

    /**
     * Stores a message to be displayed to the user the String representations of all definitions of commands requested.
     * @param helpDefinitions Definitions and usage of the commands to be displayed to the user.
     */
    public void showHelpMessage(String helpDefinitions) {
        output = helpDefinitions;
    }

    /**
     * Stores a messsage to be displayed to the user an exit message.
     * This message is displayed before the program terminates.
     */
    public void showExitMessage() {
        output = "Bye. Hope to see you again soon!";
    }

    /**
     * Returns the current input of this instance of Ui.
     * @return Current input taken in and stored in this instance of Ui.
     */
    public String getInput() {
        return this.input;
    }

    /**
     * Returns the current output of this instance of Ui.
     * @return Current output stored in this instance of Ui.
     */
    public String getOutput() {
        assert !this.output.equals("") : "Output displayed to user is empty";
        return this.output;
    }

    /**
     * Stores a message to display to the user that execution of the undo command is successful.
     * @param iterations Number of times undo was performed on the TaskList.
     */
    public void showUndoMessage(int iterations) {
        String numberOfVersions = iterations == 1 ? "1 version" : iterations + " versions";
        output = "Undo successful. Current task list is now " + numberOfVersions
                + " older than the one before undo was executed.\n\n"
                + "Input \'list\' to see the version of the tasklist we have retrieved and saved for you.";
    }

    /**
     * Stores a message to display to the user that undo cannot be performed as many times as requested.
     * The earliest version of the TaskList stored in memory is retrieved instead.
     * @param iterations Number of times undo was performed on the TaskList.
     */
    public void showMaxUndoMessageReached(int iterations) {
        String numOfUndosPerformed = iterations == 1
                ? "once"
                : iterations == 2
                ? "twice"
                : iterations == 3
                ? "thrice"
                : "" + iterations + " times";
        output = "We couldn't undo as many times as you requested. However we have performed undo "
                + numOfUndosPerformed + " to revert to the earliest version of the TaskList stored in memory.\n\n"
                + "Input \'list\' to see the version of the tasklist we have retrieved and saved for you.";
    }

    /**
     * Stores a message to display to the user that execution of the redo command is successful.
     * @param iterations Number of times redo was performed on the TaskList.
     */
    public void showRedoMessage(int iterations) {
        String numberOfVersions = iterations == 1 ? "1 version" : iterations + " versions";
        output = "Redo successful. Current task list is now " + numberOfVersions
                + " newer than the one before redo was executed.\n\n"
                + "Input \'list\' to see the version of the tasklist we have retrieved and saved for you.";
    }

    /**
     * Stores a message to display to the user that redo cannot be performed as many times as requested.
     * The latest version of the TaskList stored in memory is retrieved instead.
     * @param iterations Number of times redo was performed on the TaskList.
     */
    public void showMaxRedoMessageReached(int iterations) {
        String numOfRedosPerformed = iterations == 1
                ? "once"
                : iterations == 2
                ? "twice"
                : iterations == 3
                ? "thrice"
                : "" + iterations + " times";
        output = "We couldn't redo as many times as you requested. However we have performed redo "
                + numOfRedosPerformed + " to revert to the latest version of the TaskList stored in memory.\n\n"
                + "Input \'list\' to see the version of the tasklist we have retrieved and saved for you.";
    }

    /**
     * Stores a message to display to the user an About message about this Duke program.
     */
    public void showAboutMessage() {
        output = Ui.aboutMessage();
    }

    /**
     * Stores a message to display to the user a confirmation method to clear Task history used for undo and redo
     * operations.
     */
    public void showClearCacheConfirmationRequest() {
        output = "Are you sure you want to remove all task history?\n\n"
                + "WARNING: THIS ACTION CANNOT BE UNDONE. Once executed, all versions other than the current version "
                + "of the tasklist will be deleted and the undo / redo command will no longer be able to retrieve "
                + "them even within this same session.\n\nTo confirm clearing of cache, type \"CoNfiRMtOclEaR\" with "
                + "the same lettering cases. Otherwise, input any other character or string of text to cancel this "
                + "operation.";
    }

    /**
     * Stores a message to display to the user that all TaskLists in TaskList history except for the current one
     * has been cleared.
     */
    public void showClearCacheConfirmation() {
        output = "All versions of task lists in the undo / redo timeline except for the current task has been "
                + "removed as requested.";
    }
}