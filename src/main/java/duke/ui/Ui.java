package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scan = new Scanner(System.in);

    /**
     * Prints welcome message for user.
     */
    public void printIntro() {
        String logo = "\t____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\tHello from\n" + logo);
        printLine();
        printDuke("Hello! I'm Duke\n");
        printDuke("What can I do for you?\n");
        printLine();
    }

    /**
     * Prints line that encloses Duke's dialogues.
     */
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Scans command from user input.
     */
    public String scanCmd() {
        return scan.nextLine();
    }

    /**
     * Prints messages from Duke.
     *
     * @param toPrint string to be printed
     */
    public void printDuke(String toPrint) {
        System.out.print(toPrint);
    }

    /**
     * Prints a list in Duke format.
     *
     * @param list list containing tasks
     */
    public String showList(ArrayList<Task> list) {
        StringBuilder msg = new StringBuilder();

        msg.append("Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            msg.append(i + 1 + ". " + task.toString() + "\n");

        }
        return msg.toString();
    }

    /**
     * Returns a message to be printed once a task is added.
     * @param numOfTasks total number of tasks after a task has been added
     * @param taskDescription description of the newly added task
     */
    public String showAddTaskMessage(int numOfTasks, String taskDescription) {
        StringBuilder msg = new StringBuilder();
        msg.append("Got it. I've added this task:\n  " + taskDescription + "\n");
        msg.append("Now you have " + numOfTasks + " task(s) in the list.");
        return msg.toString();
    }
    /**
     * Prints error messages.
     *
     * @param error the type of error
     */
    public void printError(String error) {
        printDuke(error);
    }

    public String showDeleteMessage(String removedTask, int numOfTasks) {
        StringBuilder msg = new StringBuilder();
        msg.append("Noted. I've removed this task:\n  " + removedTask + "\n");
        msg.append("Now you have " + numOfTasks + " task(s) in the list.");
        return msg.toString();
    }

    public String showBye() {
        return "Bye! Hope to see you again!";
    }

    public String showDone(Task task) {
        StringBuilder msg = new StringBuilder();
        msg.append("Nice! I've marked this task as done:\n");
        msg.append(task.toString() + task.getDesc());
        return msg.toString();
    }

    public String showHelp() {
        StringBuilder helpMsg = new StringBuilder();
        helpMsg.append("Hello, I'm Duke!\n" +
                        "I can keep track of your to-dos, deadlines and events. Use these commands to control me:\n" +
                        "\t- todo [desc]\n" +
                        "\t- event [desc] /at [date time]\n" +
                        "\t- deadline [des] /by [date time]\n" +
                        "\t- list\n" +
                        "\t- bye (exits duke)\n" +
                        "\t- find [keyword]\n" +
                        "\t* date format - DD/MM/YYYY\n" +
                        "\t* time format - 0000");
        return helpMsg.toString();
    }
}
