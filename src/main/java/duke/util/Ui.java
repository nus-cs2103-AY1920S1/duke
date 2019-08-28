package duke.util;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private Scanner sc;
    private static final String horizontalLine =
            "    ____________________________________________________________";

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readInput() {
        return sc.nextLine();
    }

    public void printError(DukeException e) {
        System.out.println(horizontalLine);
        System.out.println("     \u2639 OOPS!!! " + e.getMessage());
        System.out.println(horizontalLine);
        System.out.println();
    }

    public void printGreeting() {
        // Greet
        System.out.println(horizontalLine);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(horizontalLine);
        System.out.println();
    }

    public void printExit() {
        // Exit
        System.out.println(horizontalLine);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }

    public void printTasks(TaskList tasks) {
        System.out.println(horizontalLine);
        System.out.println("     Here are the tasks in your list:");

        int id = 1;
        for (Task task : tasks) {
            System.out.println("     " + id + ". " + task);
            id++;
        }

        System.out.println(horizontalLine);
        System.out.println();
    }

    public void printMarkTaskAsDone(Task task) {
        System.out.println(horizontalLine);
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task);
        System.out.println(horizontalLine);
        System.out.println();
    }

    public void printDeleteTask(Task task) {
        System.out.println(horizontalLine);
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + task);
        System.out.println(horizontalLine);
        System.out.println();
    }

    public void printAddTask(Task task, int numOfTasks) {
        System.out.println(horizontalLine);
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + numOfTasks + " tasks in the list.");
        System.out.println(horizontalLine);
        System.out.println();
    }

    public void printFoundTasks(TaskList tasksWithKeyword) {
        System.out.println(horizontalLine);
        if (tasksWithKeyword.size() > 0) {
            System.out.println("     Here are the matching tasks in your list:");

            int id = 1;
            for (Task task : tasksWithKeyword) {
                System.out.println("     " + id + ". " + task);
                id++;
            }

        } else {
            System.out.println("     Sorry, there are no matching tasks in your list!");
        }
        System.out.println(horizontalLine);
        System.out.println();
    }
}
