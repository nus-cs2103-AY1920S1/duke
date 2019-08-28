package duke.command;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.Scanner;

/**
 * Manages input and output
 */
public class Ui {
    private static TaskList taskList;

    /**
     * Creates a new Ui object
     */
    public Ui() {
    }

    /**
     * Loops through the input and calls the Parser to evaluate each line. Loop terminate
     * when the user types in "bye"
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

    public void showLoadingError() {
        System.out.println("FIle not founddd");
    }

    public void printList() {
        int i = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : taskList.getList()) {
            System.out.println(i + ". " + task);
            i++;
        }
    }

    /**
     * Prints the message displayed when the user deletes a Task
     */
    public void printDelete(Task task) {
        System.out.println("Noted. I've removed this task: : ");
        System.out.println("\t" + task);
        System.out.println("Now you have " +  taskList.getSize()  +  " tasks in the list.");
    }

    /**
     * Prints the message displayed when the user marks a Task as done
     */
    public void printDone(Task task) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("\t" + task);
    }

    /**
     * Prints the message displayed when the user adds a new Task
     */
    public void printAdd(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " +  taskList.getSize()  +  " tasks in the list.");
    }
}
