import duke.exception.InvalidCommandException;
import duke.exception.MissingDescriptionException;
import duke.exception.MissingInputException;

import duke.task.Task;

import java.io.IOException;

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

    /**
     * Prints the hello message at the start of the program.
     */
    public void hello() {
        String openingMessage = "\tHello! I'm Duke\n\tWhat can I do for you?\n";
        System.out.println(openingMessage);
    }

    public String getHelloMessage() {
        return "\tHello! I'm Duke\n\tWhat can I do for you?\n";
    }

    /**
     * Takes in user input and passes it on to a Parser object to deal with the input.
     */
    public void takeInUserInput() {
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            command = command.trim();

            try {
                parser.executeCommand(command);
            } catch (InvalidCommandException | MissingInputException | MissingDescriptionException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Reads input given by the user and passes it to a Parser class.
     * @param command the input given by the user.
     * @return the response to user's input.
     */
    public String readUserInput(String command) {
        Parser parser = new Parser();
        String output = "";
        try {
            output = parser.executeCommand(command.trim());
        } catch (InvalidCommandException | MissingInputException | MissingDescriptionException e) {
            output += e.getMessage();
        }
        assert(output != null);
        return output;
    }

    /**
     * Prints the necessary output when a specific task is added to the tasks list.
     * @param task The specified task that was added to the task list.
     */
    public void printAddedTask(Task task) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task.toString());
        int size = (new TaskList()).getSize();
        System.out.println(String.format("\tNow you have %d tasks in the list.", size));
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

    public void printMarkAsDone(Task task) {
        System.out.println("\tNice! I've marked this task as done:\n\t\t" + task.toString());
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
     * Prints the necessary output when a specific task is deleted from the tasks list.
     * @param task The specified task that was deleted from the task list.
     */
    public void printDeletedTask(Task task) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + task.toString());
        int size = (new TaskList()).getSize();
        System.out.println(String.format("\tNow you have %d tasks in the list.", size));
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
     * Prints the specified matching tasks one by one.
     * @param matchingTasks the specified list of matching tasks.
     */
    public void printMatchingTasks(ArrayList<Task> matchingTasks) {
        System.out.println("\tHere are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println(String.format("\t%d.%s", i + 1, matchingTasks.get(i)));
        }
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
     * Prints the entire list task by task.
     */
    public void printList(ArrayList<Task> tasks) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("\t%d.%s", i + 1, tasks.get(i)));
        }
    }

    /**
     * Retrieves the appropriate response that Duke gives when the user wants to view the task list.
     * @param tasks the current list of tasks.
     * @return the appropriate response that Duke gives for the user to view the task list.
     */
    public String getListResponse(ArrayList<Task> tasks) {
        StringBuilder output = new StringBuilder("\tHere are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            output.append(String.format("\t%d.%s", i + 1, tasks.get(i)));
        }
        return output.toString();
    }

    /**
     * Prints the exit message when user exits the program. Overwrites the tasks in the stored tasks file.
     */
    public void exit() {
        System.out.println("\tBye. Hope to see you again soon!");
        Storage storage = new Storage();
        try {
            storage.overwriteTasks();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.exit(0);
    }

    /**
     * Retrieves the appropriate response when the user enters "bye", i.e. wants to exit the program.
     * @return the appropriate response that Duke gives when the user enters "bye".
     */
    public String getByeResponse() {
        return "\tBye. Hope to see you again soon!";
    }

}
