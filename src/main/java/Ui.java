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
    public Ui() {}

    /**
     * Prints the hello message at the start of the program.
     */
    public void hello() {
        String openingMessage = "\tHello! I'm Duke\n\tWhat can I do for you?\n";
        System.out.println(openingMessage);
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
     * Prints the necessary output when a specific task is added to the tasks list.
     * @param task The specified task that was added to the task list.
     */
    public void printAddedTask(Task task) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task.toString());
        ArrayList<Task> tasks = (new TaskList()).getTaskList();
        System.out.println(String.format("\tNow you have %d tasks in the list.", tasks.size()));
    }

    /**
     * Prints the necessary output when a specific task is deleted from the tasks list.
     * @param task The specified task that was deleted from the task list.
     */
    public void printDeletedTask(Task task) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + task.toString());
        ArrayList<Task> tasks = (new TaskList()).getTaskList();
        System.out.println(String.format("\tNow you have %d tasks in the list.", tasks.size()));
    }

    public void printMatchingTasks(ArrayList<Task> matchingTasks) {
        System.out.println("\tHere are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println(String.format("\t%d.%s", i + 1, matchingTasks.get(i)));
        }
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

}
