/*
 * Duke.java
 * Level-6
 * CS2103T
 * @author Gabriel Ong
 *
 * This program is an interactive task list that takes in several preset commands from the user
 * to create tasks, view the list of tasks and mark each of it as completed.
 * This class contains the main method and is responsible for all input/output and Task creation.
 *
 */

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        greetHello();

        String input;

        // Run input loop
        while (!(input = sc.nextLine()).equals("bye")) {

            try {
                // Check if task type and add accordingly
                if (input.startsWith("done ")) {
                    doneTask(input, list);
                } else if (input.startsWith("delete ")) {
                    deleteTask(input, list);
                } else if (input.startsWith("todo")) {
                    addToList(input, TaskType.Todo, list);
                } else if (input.startsWith("deadline ")) {
                    addToList(input, TaskType.Deadline, list);
                } else if (input.startsWith("event ")) {
                    addToList(input, TaskType.Event, list);
                } else if (input.equals("list")) {
                    readList(list);
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            }
            catch (DukeException e) {
                printOutput(e.getMessage());
            }
        }

        greetBye();
    }

    public static void printOutput(String output) {
        String line = "    ____________________________________________________________\n";

        // Indent and process output line
        output = "      " + output.replaceAll("\n","\n      ") + '\n';

        System.out.println(line + output + line);
    }


    public static void greetHello() {
        printOutput("Hello I'm Duke\nWhat can I do for you?");
    }

    public static void greetBye() {
        printOutput("Bye. Hope to see you again soon!");
    }

    //Adds a list of a particular task type to the task list.
    public static void addToList(String input, TaskType type, ArrayList<Task> list) throws DukeException {
        Task task = null;
        // Process input string (Cut command suffix)
        switch(type) {
            case Todo:
                if (input.length() > 5) {
                    task = new Todo(input.substring(5));
                } else {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                break;
            case Deadline:
                if (input.length() > 9) {
                    task = new Deadline(input.substring(9));
                } else {
                    throw new DukeException("The description of a deadline cannot be empty.");
                }
                break;
            case Event:
                if (input.length() > 6) {
                    task = new Event(input.substring(6));
                } else {
                    throw new DukeException("The description of an event cannot be empty.");
                }
                break;
        }
        if (task != null) {
            list.add(task);
            printOutput("Got it. I've added this task:\n  " + task
                    + "\nNow you have " + list.size() + " tasks in the list.");
        }
    }

    // Iterates through and prints every task in the task list
    public static void readList(ArrayList<Task> list) throws DukeException {
        int count = 1;
        StringBuilder output = new StringBuilder();

        output.append("Here are the tasks in your list:\n");

        // List and print all items stored
        for (Task item: list) {
            output.append(count++ + "." + item + '\n');
        }

        // Remove terminal newline character if at least 1 item inserted
        if (count > 1) {
            output.deleteCharAt(output.toString().length() - 1);
            printOutput(output.toString());
        } else {
            throw new DukeException("The task list is empty.");
        }
    }

    // Marks if item in list as done.
    public static void doneTask(String input, ArrayList<Task> list) throws DukeException {
        // Process input
        if (input.length() > 5) {
            try {
                int taskIndex = Integer.parseInt(input.substring(5));
                Task item = list.get(taskIndex-1);
                item.setDone();
                printOutput("Nice! I've marked this task as done:\n  " + item);
            } catch (NumberFormatException e) {
                throw new DukeException("The description of a done must be an integer.");
            }
        } else {
            throw new DukeException("The description of a done cannot be empty.");
        }
    }

    // Removes the item in list
    public static void deleteTask(String input, ArrayList<Task> list) throws DukeException {
        // Process input
        if (input.length() > 7) {
            try {
                int taskIndex = Integer.parseInt(input.substring(7));
                if (list.size() >= taskIndex && taskIndex > 0) {
                    printOutput("Noted. I've removed this task:\n  " + list.get(taskIndex - 1) +
                            "\nNow you have " + (list.size() - 1) + " tasks in the list.");
                    list.remove(taskIndex - 1);
                }
                else {
                    throw new DukeException("The integer entered for deletion is not valid.");
                }
            } catch (NumberFormatException e) {
                throw new DukeException("The description of a delete must be an integer.");
            }
        } else {
            throw new DukeException("The description of a delete cannot be empty.");
        }
    }
}
