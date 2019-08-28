package duke.ui;

import duke.task.Task;
import duke.tasklist.Tasklist;

import java.util.Scanner;

public class Ui {
    private static Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        String greetingText = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greetingText);
    }

    /**
     * Outputs farewell message.
     */
    public void showFarewell() {
        String farewellText = "Bye <3 Hope to see you again soon!";
        System.out.println(farewellText);
        sc.close(); // Close the scanner.
    }

    /**
     * Outputs the user's current list of tasks.
     * @param tasks Represents the user's current list.
     */
    public void showList(Tasklist tasks) {
        String listText = "Here are the tasks in your list:";
        System.out.println(listText);
        listEntries(tasks);
    } // End method.

    /**
     * List all entries recorded by Duke; print nothing if no entries are present.
     */
    private static void listEntries(Tasklist tasks) {
        int numEntry = 1;
        for (Task task : tasks.tasks) {
            System.out.printf("%d. %s\n", numEntry, task.toString());
            numEntry++;
        } // End for loop.
    } // End method.

    public void invalidCommand() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means. "
                + "I sure need more sleep...");
    } // End method.

    /**
     * Reads the user's command.
     * @return the user's command.
     */
    public String[] readCommand() {
        String command = sc.next();
        String details = sc.nextLine();
        return new String[] {command, details};
    }

    /**
     * Tells the user that a task was added and the current amount of tasks in the list.
     * @param desc Description of the task added.
     * @param size The current size of the list.
     */
    public void addTaskDialogue(String desc, int size) {
        String addText = "Got it. I've added this task:";
        String taskWord = size <= 1 ? "task" : "tasks"; // Ensure correct grammar.
        String numTaskText = String.format("Now you have %d %s in the list.", size, taskWord);

        System.out.println(addText);
        System.out.println("  " + desc);
        System.out.println(numTaskText);
    } // End method.


    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Tell the user that the task has been marked as done.
     * @param message String representation of the task that was marked as done.
     */
    public void showDone(String message) {
        String doneText = "Nice! I've marked this task as done: ";
        System.out.println(doneText);
        System.out.println("  " + message);
    }

    /**
     * Inform user that a task was deleted.
     * @param message Represents the deleted task.
     * @param size The current size of the list after deletion.
     */
    public void showDeleted(String message, int size) {
        String removeText = "Noted. I've removed this task:";
        String taskWord = size <= 1 ? "task" : "tasks"; // Ensure correct grammar.

        System.out.println(removeText);
        System.out.println("  " + message);
        System.out.printf("Now you have %d %s in the list.\n", size, taskWord);

    }

    public void showLoadingError() {
        System.out.println("Error loading savefile.");
    }
}
