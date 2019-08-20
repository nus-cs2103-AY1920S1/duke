import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Duke {
    public static String liner = "    ____________________________________________________________";

    public static void main(String[] args) throws DukeException {
        //greet user
        String greeting = "     Hello! I'm Duke\n     What can I do for you?";
        System.out.println(liner + "\n" + greeting + "\n" + liner);

        String input;
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (!(input = sc.nextLine()).equals("bye")) {
            try {
                if (input.equals("list")) {
                    System.out.println(liner);
                    System.out.println("     Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        int num = i + 1;
                        Task currTask = tasks.get(i);
                        System.out.println("     " + num + ". " + currTask.toString());
                    }
                    System.out.println(liner);
                } else if (input.startsWith("done")) {
                    String[] arr = input.split(" ");
                    if (arr.length == 2 && arr[1].matches("\\d+")) {
                        int pos = Integer.parseInt(arr[1]);
                        //error handling
                        if (pos > tasks.size() || pos <= 0 ) {
                            throw new DukeException(liner + "\n       OOPS!!! Task id not within range of total number of tasks!\n" + liner);
                        }
                        //
                        Task currTask = tasks.get(pos - 1);
                        currTask.markAsDone();
                        printDoneTask(currTask);
                    } else {
                        //error handling
                        throw new DukeException(liner + "\n       OOPS!!! Invalid Done Command! Please try again!\n" + liner);
                    }
                } else if (input.startsWith("delete")) {
                    String[] arr = input.split(" ");
                    if (arr.length == 2 && arr[1].matches("\\d+")) {
                        int pos = Integer.parseInt(arr[1]);
                        //error handling
                        if (pos > tasks.size() || pos <= 0 ) {
                            throw new DukeException(liner + "\n       OOPS!!! Task id not within range of total number of tasks!\n" + liner);
                        }
                        //
                        Task currTask = tasks.get(pos - 1);
                        tasks.remove(Integer.parseInt(arr[1]) - 1);
                        printDeletedTask(currTask, tasks.size());
                    } else {
                        //error handling
                        throw new DukeException(liner + "\n       OOPS!!! Invalid Delete Command! Please try again!\n" + liner);
                    }
                } else if (input.startsWith("todo")) {
                    String command = input.replaceFirst("todo", "").trim();
                    Task newTask = new Todo(command);
                    //error handling
                    if (command.isEmpty()) {
                        throw new DukeException(liner + "\n       OOPS!!! The description of a todo cannot be empty.\n" + liner);
                    }
                    //
                    tasks.add(newTask);
                    printAddTask(newTask, tasks.size());
                } else if (input.startsWith("deadline")) {
                    String command = input.replaceFirst("deadline", "").trim();
                    String[] arr = command.split("/by");
                    //error handling
                    if (arr[0].trim().isEmpty()) {
                        throw new DukeException(liner + "\n       OOPS!!! The description of a deadline cannot be empty.\n" + liner);
                    } else if (arr.length < 2 || arr[1].trim().isEmpty()) {
                        throw new DukeException(liner + "\n       OOPS!!! The deadline must have a specified date/time.\n" + liner);
                    }
                    //
                    Task newTask = new Deadline(arr[0].trim(), arr[1].trim());
                    tasks.add(newTask);
                    printAddTask(newTask, tasks.size());
                } else if (input.startsWith("event")) {
                    String command = input.replace("event", "").trim();
                    String[] arr = command.split("/at");
                    //error handling
                    if (arr[0].trim().isEmpty()) {
                        throw new DukeException(liner + "\n       OOPS!!! The description of a event cannot be empty.\n" + liner);
                    } else if (arr.length < 2 || arr[1].trim().isEmpty()) {
                        throw new DukeException(liner + "\n       OOPS!!! The event must have a specified date/time.\n" + liner);
                    }
                    //
                    Task newTask = new Event(arr[0].trim(), arr[1].trim());
                    tasks.add(newTask);
                    printAddTask(newTask, tasks.size());
                } else {
                    throw new DukeException(liner + "\n       OOPS!!! I'm sorry, but I don't know what that means :-(\n" + liner);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println(liner + "\n     Bye. Hope to see you again soon!\n" + liner);
    }

    public static void printAddTask(Task newTask, int totalTasks) {
        System.out.println(liner);
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + newTask.toString());
        System.out.println("     Now you have " + totalTasks + " tasks in the list.");
        System.out.println(liner);
    }

    public static void printDoneTask(Task currTask) {
        System.out.println(liner);
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println("       "  + currTask.toString());
        System.out.println(liner);
    }

    public static void printDeletedTask(Task currTask, int totalTasks) {
        System.out.println(liner);
        System.out.println("     Noted. I've removed this task: ");
        System.out.println("       "  + currTask.toString());
        System.out.println("     Now you have " + totalTasks + " tasks in the list.");
        System.out.println(liner);
    }
}
