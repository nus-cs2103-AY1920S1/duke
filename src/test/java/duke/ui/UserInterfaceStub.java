package duke.ui;

import duke.constant.Consts;
import duke.task.Task;
import duke.tasklist.MyList;
import duke.tasklist.TaskList;

import java.util.List;
import java.util.Scanner;

public class UserInterfaceStub implements DukeUserInterface {
    private Scanner sc;

    public UserInterfaceStub() {
    }

    /**
     * Returns the introduction message of the application.
     */
    public String getIntro() {
        return "";
    }

    /**
     * Returns the exit message.
     */
    public String getExitMsg() {
        return "";
    }

    /**
     * Returns a message when a task is added.
     *
     * @param task Task that was added.
     * @param taskList Task List where the Task is stored.
     * @return String which represents the output text.
     */
    public String getAddTaskMsg(Task task, MyList taskList) {
        return "";
    }

    /**
     * Returns the list of tasks.
     *
     * @param myList List of tasks stored in the application.
     * @return String which represents the output text.
     */
    public String getList(MyList myList) {
        return "";
    }

    /**
     * Returns the message containing the list of tasks that matches the String word from the Find command.
     *
     * @param myList MyList of tasks which contains the String word from the Find command.
     * @return String which represents the output text.
     */
    public String getFindList(MyList myList) {
        return "";
    }

    /**
     * Returns the message to be printed when a task is marked as done.
     *
     * @param task Task that was marked as done.
     * @return String which represents the output text.
     */
    public String getDoneMsg(Task task) {
        return "";
    }

    /**
     * Returns the exception message.
     *
     * @param msg Message of the exception.
     * @return String which represents the output text.
     */
    public String getException(String msg) {
        return "";
    }

    /**
     * Returns the message to be printed when a task is deleted.
     *
     * @param task Task that was deleted.
     * @param taskList Task list that the Task was removed from.
     * @return String which represents the output text.
     */
    public String getDeleteMsg(Task task, MyList taskList) {
        return "";
    }

    /**
     * Returns the message to be printed when a tag is added to a task.
     *
     * @param task Task which the tag is added to.
     * @param tagName String name of the tag.
     * @return String message to be printed.
     */
    public String getAddTagMsg(Task task, String tagName) {
        return "";
    }

    /**
     * Returns the message to be printed when a tag is deleted from a task.
     *
     * @param task Task which the tag is added to.
     * @param tagName String name of the tag.
     * @return String message to be printed.
     */
    public String getDeleteTagMsg(Task task, String tagName) {
        return "";
    }
}
