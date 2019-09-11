package duke.ui;

import duke.exception.InvalidCommandException;
import duke.exception.MissingDescriptionException;
import duke.exception.MissingInputException;

import duke.parser.Parser;
import duke.task.Task;
import duke.tasklist.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the UI and deals with interactions with the user.
 */
public class Ui {

    /**
     * An empty constructor that creates the Ui object.
     */
    public Ui() {

    }

    public String getHelloMessage() {
        return "\tHello! I'm Duke\n\tWhat can I do for you?\n";
    }

    /**
     * Reads input given by the user and passes it to a Parser class.
     * @param command the input given by the user.
     * @return the response to user's input.
     */
    public String getResponseToUserInput(String command) {
        Parser parser = new Parser();
        String output = "";
        try {
            output = parser.getResponseToCommand(command.trim());
        } catch (InvalidCommandException | MissingInputException | MissingDescriptionException e) {
            output += e.getMessage();
        }
        return output;
    }

    /**
     * Retrieves the response that Duke gives when a task is added to the task list.
     * @param task the specifed task that is added to the task list.
     * @return the response that Duke gives to inform the user that the specified task is added to the list and the
     *     current number of tasks in the list.
     */
    public String getAddTaskResponse(Task task) {
        String output = "\tGot it. I've added this task:";
        output += ("\t" + task.toString());
        int size = (new TaskList()).getSize();
        output += String.format("\tNow you have %d tasks in the list.", size);
        return output;
    }

    /**
     * Retrieves the response that Duke gives when the specified task is marked as done.
     * @param task the specified task that is marked as done.
     * @return the response that Duke gives to inform the user that the specified task is marked as done.
     */
    public String getDoneTaskResponse(Task task) {
        return "\tNice! I've marked this task as done:\n\t\t" + task.toString();
    }

    /**
     * Retrieves the response that Duke gives when a task is deleted from the task list.
     * @param task the specified task that is deleted from the list.
     * @return the appropriate response that Duke gives to let the user know that the corresponding task has been
     *     deleted.
     */
    public String getDeleteTaskResponse(Task task) {
        String output = "\tNoted. I've removed this task:";
        output += "\t  " + task.toString();
        int size = (new TaskList()).getSize();
        output += String.format("\tNow you have %d tasks in the list.", size);
        return output;
    }

    /**
     * Retrieves the appropriate response that Duke gives when the user wants to find certain tasks.
     * @param matchingTasks the list of matching tasks that are found for the user.
     * @return the response that Duke gives for the user to view the matching tasks.
     */
    public String getFindTaskResponse(ArrayList<Task> matchingTasks) {
        StringBuilder output = new StringBuilder("\tHere are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            output.append(String.format("\t%d.%s", i + 1, matchingTasks.get(i)));
        }
        return output.toString();
    }

    /**
     * Retrieves the appropriate response that Duke gives when the user wants to view the task list.
     * @param tasks the current list of tasks.
     * @return the appropriate response that Duke gives for the user to view the task list.
     */
    public String getListResponse(ArrayList<Task> tasks) {
        StringBuilder output = new StringBuilder("\tHere are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            output.append(String.format("\t%d. %s\n", i + 1, tasks.get(i)));
        }
        return output.toString();
    }

    /**
     * Retrieves the appropriate response when the user enters "bye", i.e. wants to exit the program.
     * @return the appropriate response that Duke gives when the user enters "bye".
     */
    public String getByeResponse() {
        return "\tBye. Hope to see you again soon!";
    }

}
