package duke.ui;

import duke.task.Task;
import duke.tasklist.MyList;

/**
 * Represents an interface of the user interface of the application. Provides methods for
 * reading input and printing output to the console.
 */
public interface DukeUserInterface {

    /**
     * Reads from standard input and returns it.
     *
     * @return String which consists of user input.
     */
    public String readCommand();

    /**
     * Closes the scanner when the application is exiting.
     */
    public void exit();

    /**
     * Prints line with indentation in front.
     */
    public void printLine();

    /**
     * Prints the introduction of the application.
     */
    public void printIntro();

    /**
     * Prints the exit message.
     */
    public void printExitMsg();

    /**
     * Prints a message when a task is added.
     * @param task Task that was added.
     * @param taskList Task List where the Task is stored.
     */
    public void printAddTaskMsg(Task task, MyList taskList);

    /**
     * Prints the list of tasks.
     *
     * @param myList List of tasks stored in the application.
     */
    public void printList(MyList myList);

    /**
     * Prints out a message when a task is marked as done.
     * @param task Task that was marked as done.
     */
    public void printDoneMsg(Task task);

    /**
     * Prints out the exception.
     * @param msg Message of the exception.
     */
    public void printException(String msg);

    /**
     * Prints out the message when a task is deleted.
     * @param task Task that was deleted.
     * @param taskList Task list that the Task was removed from.
     */
    public void printDeleteMsg(Task task, MyList taskList);
    public void printFindList(MyList resultList);
}
