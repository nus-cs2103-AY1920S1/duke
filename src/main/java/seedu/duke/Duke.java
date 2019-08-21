package seedu.duke;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private ArrayList<Task> tasks;

    public Duke() {
        this.tasks = new ArrayList<Task>();
        initDuke();
    }

    /**
     * Creates the Duke Logo.
     * @params args String[]
     */
    public static void main(String[] args) {

        Duke duke = new Duke();
        String cmd = "";
        Scanner sc = new Scanner(System.in);

        cmd = sc.nextLine();

        while (!cmd.equals("bye")) {
            String[] cmdList = cmd.split(" ");
            String keyword = cmdList[0];
            printLine();

            try {

                if (keyword.equals("list")) {
                    System.out.println("Here are the tasks in your list: ");
                    for (int i = 1; i <= duke.tasks.size(); i++) {
                        System.out.println(i + "." + duke.tasks.get(i - 1));
                    }
                } else if (keyword.equals("done")) {
                    System.out.println("Nice! I've marked this task as done: ");
                    Task taskToMarkAsDone = duke.tasks.get(Integer.parseInt(cmdList[1]) - 1);
                    taskToMarkAsDone.markAsDone();
                    System.out.println(taskToMarkAsDone);
                } else { // it is a new Task
                    Task newTaskToBeAdded = duke.handleNewTask(keyword, cmd);
                    duke.tasks.add(newTaskToBeAdded);
                    System.out.println("Got it. I've added this task: ");
                    System.out.print("  ");
                    System.out.println(newTaskToBeAdded);
                    System.out.println("Now you have " + duke.tasks.size() + " tasks in the list.");
                }

            } catch (Exception e) {
                System.out.println("☹ OOPS!!! " + e.getMessage());
            }
            printLine();
            cmd = sc.nextLine();
        }

        closeDuke();

    }

    private static void printLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    private static void initDuke() {
        printLine();
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    private static void closeDuke() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    private Task handleNewTask(String keyword, String cmd) throws InputMismatchException {
        if (keyword.equals("deadline")) {
            String descriptionAndTime = cmd.substring(10);
            String[] details = descriptionAndTime.split(" /by");
            return new Deadline(details[0], details[1]);

        } else if (keyword.equals("event")) {
            String descriptionAndTime = cmd.substring(6);
            String[] details = descriptionAndTime.split(" /at");
            return new Event(details[0], details[1]);

        } else if(keyword.equals("Todo")){
            String description = cmd.substring(5); //words after todo
            return new Todo(description);
        } else {
            throw new InputMismatchException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
