import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * printLine() will print the separator lline from input and output.
     */
    public void printLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    /**
     * printWelcome() will print the welcome message from Duke.
     */
    public void printWelcome() {
        printLine();
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    /**
     * printGoodbye() will print the goodbye message from Duke.
     */
    public void printGoodbye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * printList() will print out all tasks from Duke.
     * @param tasks is the list of tasks
     */
    public void printList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + "." + tasks.get(i - 1));
        }
    }

    /**
     * printDone() will print the message after a task is completed.
     * @param taskToMarkAsDone is the task Object
     */
    public void printDone(Task taskToMarkAsDone) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("  ");
        System.out.println(taskToMarkAsDone);
    }

    /**
     * printDelete will print the message after task is deleted by Duke.
     * @param taskToBeRemoved is the task Object reference
     * @param numberOfTasks number of tasks in the list
     */
    public void printDelete(Task taskToBeRemoved, int numberOfTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.print("  ");
        System.out.println(taskToBeRemoved);
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
    }

    /**
     * printAdd will print the message after new Task has been added.
     * @param newTaskToBeAdded is the Task Object reference
     * @param numberOfTasks number of tasks in the list
     */
    public void printAdd(Task newTaskToBeAdded, int numberOfTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.print("  ");
        System.out.println(newTaskToBeAdded);
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
    }

    /**
     * readCommand() will scan next line of user input command.
     * @return the command
     */
    public String readCommand() {
        String command = sc.nextLine();
        return command;
    }

    /**
     * printError() will print the error message from exception.
     * @param e is the Exception object reference
     */
    public void printError(Exception e) {
        System.out.println("☹ OOPS!!! " + e.getMessage());
    }

    /**
     * printMatches() will print all tasks that match given keyword.
     * @param matchingTasks ArrayList of matching Tasks
     */
    public void printMatches(ArrayList<Task> matchingTasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 1; i <= matchingTasks.size(); i++) {
            System.out.println(i + "." + matchingTasks.get(i - 1));
        }
    }

}
