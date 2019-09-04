package duke.command;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.Scanner;

/**
 * Manages input and output.
 */
public class Ui {
    public static TaskList taskList;

    /**
     * Creates a new Ui object.
     */
    public Ui() {
    }

    /**
     * Loops through the input and calls the Parser to evaluate each line. Loop terminate
     * when the user types in "bye".
     * @param parser the Parser that is used to evaluate each line
     * @param storage the Storage that is used to save information into file
     * @param taskList the TaskList that is used to save the current information
     */
    public void start(Parser parser, Storage storage, TaskList taskList) {
        this.taskList = taskList;

        System.out.println("Hello! I'm duke.Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();

        while (!word.equals("bye")) {
            try {
                parser.parseLine(word);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Must input an integer");
            } finally {
                word = sc.nextLine();
            }

        }
        storage.updateFile(taskList.getList());
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the loading error.
     */
    public String showLoadingError() {
        System.out.println("File not found");
        return "File not found";
    }

    /**
     * Prints the list of Tasks stored in the TaskList.
     */
    public String printList() {
        int i = 1;
        System.out.println("Here are the tasks in your list:");
        String result = "Here are the tasks in your list:\n";
        for (Task task : taskList.getList()) {
            System.out.println(i + ". " + task);
            result += i + ". " + task + "\n";
            i++;
        }
        return result;
    }

    /**
     * Prints the message displayed when the user deletes a Task.
     */
    public String printDelete(Task task) {
        System.out.println("Noted. I've removed this task: : ");
        System.out.println("\t" + task);
        System.out.println("Now you have " +  taskList.getSize()  +  " tasks in the list.");

        return "Noted. I've removed this task: : \n"
                + "\t" + task + "\n"
                + "Now you have " +  taskList.getSize()  +  " tasks in the list.";
    }

    /**
     * Prints the message displayed when the user marks a Task as done.
     */
    public String printDone(Task task) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("\t" + task);
        return "Nice! I've marked this task as done: \n\t" +task;
    }

    /**
     * Prints the message displayed when the user adds a new Task.
     */
    public String printAdd(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " +  taskList.getSize()  +  " tasks in the list.");
        return "Got it. I've added this task:\n"
                + "\t" + task.toString() + "\n"
                + "Now you have " +  taskList.getSize()  +  " tasks in the list.";
    }

    /**
     * Prints the list of Tasks stored in the TaskList that contains the user input.
     */
    public String printMatchingList(String pattern) {
        int i = 1;
        System.out.println("Here are the matching tasks in your list:");
        String result = "Here are the matching tasks in your list:\n";
        for (Task task : taskList.getList()) {
            if (task.toString().contains(pattern) || task.getFileStringFormat().contains(pattern)) {
                System.out.println(i + ". " + task);
                result += i + ". " + task + "\n";
                i++;
            }
        }
        return result;
    }
}
