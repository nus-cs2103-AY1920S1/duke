import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> tasks;

    private static void printNumberTasks() {
        String s = tasks.size() == 1 ? "" : "s";
        System.out.println("Now you have " + tasks.size() + " task" + s + " in the list.");
    }

    private static void addTask(Task task) throws DukeException {
        if (!task.isValid()) {
            throw new DukeException(task.invalidMessage());
        }
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        printNumberTasks();
    }

    /**
     * This is the main method and entry point for the Duke program.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        tasks = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            Command cmd = new Command(input);
            String cmdInput = cmd.getInput();
            String cmdKeyword = cmd.getKeyword();
            String cmdArgs = cmd.getArgs();
            String cmdBeforeSlashArgs = cmd.getBeforeSlashArgs();
            String cmdSlashKeyword = cmd.getSlashKeyword();
            String cmdSlashArgs = cmd.getSlashArgs();

            try {
                if (cmdInput.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (cmdInput.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i));
                    }
                } else if (cmdKeyword.equals("done")) {
                    try {
                        int position = Integer.parseInt(cmdArgs) - 1;
                        Task task = tasks.get(position);
                        task.markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + task);
                    } catch (NumberFormatException e) {
                        throw new DukeException("Your input should be a number.");
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("There is no task at the given position.");
                    }
                } else if (cmdKeyword.equals("delete")) {
                    try {
                        int position = Integer.parseInt(cmdArgs) - 1;
                        Task oldTask = tasks.get(position);
                        tasks.remove(position);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println("  " + oldTask);
                        printNumberTasks();
                    } catch (NumberFormatException e) {
                        throw new DukeException("Your input should be a number.");
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("There is no task at the given position.");
                    }
                } else if (cmdKeyword.equals("todo")) {
                    addTask(new Todo(cmdArgs));
                } else if (cmdKeyword.equals("event")) {
                    addTask(new Event(cmdBeforeSlashArgs, cmdSlashKeyword, cmdSlashArgs));
                } else if (cmdKeyword.equals("deadline")) {
                    addTask(new Deadline(cmdBeforeSlashArgs, cmdSlashKeyword, cmdSlashArgs));
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println("☹ OOPS!!! " + e.getMessage());
            }
        }
    }
}
