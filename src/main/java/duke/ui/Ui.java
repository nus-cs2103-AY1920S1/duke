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

    public void showFarewell() {
        String farewellText = "Bye <3 Hope to see you again soon!";
        System.out.println(farewellText);
        sc.close(); // Close the scanner.
    }

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

    public String[] readCommand() {
        String command = sc.next();
        String details = sc.nextLine();
        return new String[] {command, details};
    }

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

    public void showDone(String message) {
        String doneText = "Nice! I've marked this task as done: ";
        System.out.println(doneText);
        System.out.println("  " + message);
    }

    public void showDeleted(String message, int size) {
        String removeText = "Noted. I've removed this task:";
        String taskWord = size <= 1 ? "task" : "tasks"; // Ensure correct grammar.

        System.out.println(removeText);
        System.out.println("  " + message);
        System.out.printf("Now you have %d %s in the list.\n", size, taskWord);

    }
}
