package duke.ui;

import duke.exception.InvalidArgumentException;
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
        return "harro!! i am duke! :>\n"
                + "wat can i do for u??\n"
                + "type 'help' if u dk or forgot d commands!!\n"
                + "& don't forget to type 'bye' to save ur changes okie!!";
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
        } catch (InvalidCommandException | InvalidArgumentException | MissingInputException |
                MissingDescriptionException e) {
            output += e.getMessage();
        }
        assert(output != null);
        return output;
    }

    /**
     * Retrieves the response that Duke gives when a task is added to the task list.
     * @param task the specifed task that is added to the task list.
     * @return the response that Duke gives to inform the user that the specified task is added to the list and the
     *     current number of tasks in the list.
     */
    public String getAddTaskResponse(Task task) {
        String output = "okie! i added this task:\n";
        output += "" + task.toString() + "\n";
        int size = (new TaskList()).getSize();
        if (size == 1) {
            output += String.format("now u haf %d task in the list", size);
        } else {
            output += String.format("now u haf %d tasks in the list", size);
        }
        return output;
    }

    /**
     * Retrieves the response that Duke gives when the specified task is marked as done.
     * @param task the specified task that is marked as done.
     * @return the response that Duke gives to inform the user that the specified task is marked as done.
     */
    public String getDoneTaskResponse(Task task) {
        return "naisu! i marked this task as done:\n" + task.toString();
    }

    /**
     * Retrieves the response that Duke gives when a task is deleted from the task list.
     * @param task the specified task that is deleted from the list.
     * @return the appropriate response that Duke gives to let the user know that the corresponding task has been
     *     deleted.
     */
    public String getDeleteTaskResponse(Task task) {
        String output = "okie! i delete this task:\n";
        output += "" + task.toString() + "\n";
        int size = (new TaskList()).getSize();
        if (size == 1) {
            output += String.format("now u haf %d task in the list", size);
        } else {
            output += String.format("now u haf %d tasks in the list", size);
        }
        return output;
    }

    /**
     * Retrieves the appropriate response that Duke gives when the user wants to find certain tasks.
     * @param matchingTasks the list of matching tasks that are found for the user.
     * @return the response that Duke gives for the user to view the matching tasks.
     */
    public String getFindTaskResponse(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            return null;
        }

        StringBuilder output = new StringBuilder("here ya go, the matchy-matchy tasks in ur list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            output.append(String.format("%d. %s\n", i + 1, matchingTasks.get(i)));
        }
        return output.toString();
    }

    /**
     * Retrieves the appropriate response that Duke gives when the user wants to view the task list.
     * @param tasks the current list of tasks.
     * @return the appropriate response that Duke gives for the user to view the task list.
     */
    public String getListResponse(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "u haf no tasks in ur list!!1!";
        }

        StringBuilder output = new StringBuilder("here ya go, the tasks in ur list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            output.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        return output.toString();
    }

    /**
     * Retrieves the appropriate response when the user enters "bye", i.e. wants to exit the program.
     * @return the appropriate response that Duke gives when the user enters "bye".
     */
    public String getByeResponse() {
        return "okie baibai~ hope to see u again soon!~ :>";
    }

    /**
     * Retrives the appropriate response when the user requests for help.
     * @return the appropriate response that Duke gives when the user enters "help".
     */
    public String getHelpResponse() {
       String output = "u asked for halp?? here ya go~\n\n";
       output += "todo <description> - to add a Todo task with the description\n\n";
       output += "deadline <description> <deadline: eg. 1/9/2019 2359> - to add a Deadline task with the description and deadline\n\n";
       output += "event <description> <event day & time: eg. 1/9/2019 2359> - to add an Event task with the description and event day & "
               + "time\n\n";
       output += "list - to display all the tasks in your list\n\n";
       output += "done <task no.> - to mark that task as done\n\n";
       output += "delete <task no.> - to delete that task from the list\n\n";
       output += "find <keyword(s)> - to find the matching tasks with the keyword(s)\n\n";
       output += "bye - to save the changes made to your list and exit the program\n\n" ;
       output += "help - to display this help page\n";
       return output;
    }
}
