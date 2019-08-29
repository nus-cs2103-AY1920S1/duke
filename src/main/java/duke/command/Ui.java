package duke.command;

import duke.DukeException;
import duke.task.Task;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */

public class Ui {
    Parser parser;
    public String line = "";

    public Ui() {
    }

    /**
     * Handles input from user and pass it to parser for processing.
     *
     * @param parser parser to process the input.
     */

    public void start(Parser parser) {
        System.out.println("Hello! I'm duke.Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        line = sc.nextLine();

        this.parser = parser;
        while (!line.equals("bye")) {
            try {
                parser.process(line);
            } catch (DukeException e){
                System.err.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println("☹ OOPS!!! The task done must be a number.");
            } catch (ParseException e) {
                System.err.println("☹ OOPS!!! Wrong date format.\nPlease use the correct format dd/MM/yyyy hh:mm");
            } finally {
                line = sc.nextLine();
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints message when adding task.
     *
     * @param currTask current task being added.
     * @param taskList list of tasks to be added.
     */

    public void printAdd(Task currTask, TaskList taskList) {
        ArrayList<Task> currList = taskList.list;
        int size = currList.size();
        System.out.println("Got it. I've added this task: \n  " + currTask
                + "\nNow you have " + size + " tasks in the list.");
    }

    /**
     * Prints message when deleting task.
     *
     * @param currTask current task being deleted.
     * @param taskList list of tasks to be deleted from.
     */

    public void printDelete(Task currTask, TaskList taskList) {
        System.out.println("Noted. I've removed this task: \n  " + currTask
                + "\nNow you have " + taskList.list.size() + " tasks in the list.");
    }

    /**
     * Prints current list.
     *
     * @param list list of task to be printed.
     */

    public void printList(ArrayList<Task> list) {
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + ". " + list.get(i - 1));
        }
    }

    /**
     * Prints message when a task is done.
     *
     * @param currTask current task which is done.
     */

    public void printDone(Task currTask) {
        System.out.println("Nice! I've marked this task as done: \n  " + currTask);
    }

    /**
     * Prints list of tasks which contains keyword.
     *
     * @param list list of task to be printed.
     */

    public void printFind(ArrayList<Task> list) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + ". " + list.get(i - 1));
        }
    }

    /**
     * Prints loading error.
     */

    public void showLoadingError() {
        System.err.println("File not found.");
    }
}
