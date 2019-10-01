package duke.ui;

import duke.task.Task;
import duke.tasklist.MyList;

/**
 * Represents an interface of the user interface of the application. Provides methods for
 * reading input and printing output to the console.
 */
public interface DukeUserInterface {

    /**
     * Returns the introduction of the application.
     */
    public String getIntro();

    /**
     * Returns the exit message.
     */
    public String getExitMsg();

    /**
     * Returns a message when a task is added.
     * @param task Task that was added.
     * @param taskList Task List where the Task is stored.
     */
    public String getAddTaskMsg(Task task, MyList taskList);

    /**
     * Returns the list of tasks.
     *
     * @param myList List of tasks stored in the application.
     */
    public String getList(MyList myList);

    /**
     * Returns out a message when a task is marked as done.
     * @param task Task that was marked as done.
     */
    public String getDoneMsg(Task task);

    /**
     * Returns out the exception.
     * @param msg Message of the exception.
     */
    public String getException(String msg);

    /**
     * Returns out the message when a task is deleted.
     * @param task Task that was deleted.
     * @param taskList Task list that the Task was removed from.
     */
    public String getDeleteMsg(Task task, MyList taskList);

    /**
     * Returns the message containing the list of tasks that matches the String word from the Find command.
     *
     * @param resultList MyList of tasks which contains the String word from the Find command.
     * @return String which represents the output text.
     */
    public String getFindList(MyList resultList);

    /**
     * Returns the message to be printed when a tag is added to a task.
     *
     * @param task Task which the tag is added to.
     * @param tagName String name of the tag.
     * @return String message to be printed.
     */
    public String getAddTagMsg(Task task, String tagName);

    /**
     * Returns the message to be printed when a tag is deleted from a task.
     *
     * @param task Task which the tag is added to.
     * @param tagName String name of the tag.
     * @return String message to be printed.
     */
    public String getDeleteTagMsg(Task task, String tagName);
}
