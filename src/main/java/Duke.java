import java.util.Scanner;
import java.util.*;

public class Duke {

    private static String line = "    ____________________________________________________________";

    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    // print a list of strings with horizontal lines and indentation
    private static void format_print(String[] lists) {
        System.out.println(line);
        for (String s : lists) {
            System.out.println("     " + s);
        }
        System.out.println(line);
    }

    // print a string with horizontal lines and indentation
    private static void format_print(String s) {
        System.out.println(line);
        System.out.println("     " + s);
        System.out.println(line);
    }

    private static String format(String s) {
        return "     " + s;
    }

    // echos with input string
    private static void echo(String s) {
        format_print(s);
    }

    public static void main(String[] args) {
        // initialize objects
        Scanner sc = new Scanner(System.in);
        // String[] list = new String[100];
        Task[] tasks = new Task[100];
        Task.count = 0;

        // greetings
        String[] greetings = {"Hello! I'm Duke", "What can I do for you?"};
        format_print(greetings);

        // interacts until the input is "bye"
        while (true) {
            try {
                // read input line
                String s = sc.nextLine();
                if (s.equals("bye")) { // exit
                    format_print("Bye. Hope to see you again soon!");
                    break;
                } else if (s.equals("list")) { // show all tasks
                    System.out.println(line);
                    System.out.println(format("Here are the tasks in your list:"));
                    for (Task t : Arrays.copyOfRange(tasks, 0, Task.count)) {
                        System.out.println(format(t.toString()));
                    }
                    System.out.println(line);
                } else if (s.split(" ")[0].equals("done")) { // mark as done
                    try {
                        int num = Integer.parseInt(s.split(" ")[1]);
                        if (num >= Task.count) {
                            throw new DukeException("Task number out of range.");
                        }
                        tasks[num - 1].isDone = true;
                        String[] print_list = {"Nice! I've marked this task as done: ", tasks[num - 1].done()};
                        format_print(print_list);
                    } catch (NumberFormatException ex) {
                        throw new DukeException("Task number should be integer.");
                    }
                } else { // add new task
                    if (s.split(" ").length == 1) {
                        String type = s.split(" ")[0];
                        switch (type) {
                            case "todo":
                                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                            case "event":
                                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                            case "deadline":
                                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                            default:
                                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }
                    }
                    String type = s.substring(0, s.indexOf(" "));
                    String description = s.substring(s.indexOf(" ") + 1);
                    Task newTask;
                    switch (type) {
                        case "todo":
                            newTask = new Todo(description);
                            break;
                        case "event":
                            newTask = new Event(description);
                            break;
                        case "deadline":
                            newTask = new Deadline(description);
                            break;
                        default:
                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    try {
                        String str = format(newTask.toString());
                        tasks[Task.count - 1] = newTask;
                        System.out.println(line);
                        System.out.println(format("Got it. I've added this task: "));
                        System.out.println(str);
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        throw new DukeException("Task description should be of format \"context /prep date time\"");
                    }
                    if (Task.count == 1) {
                        System.out.println(format("Now you have 1 task in the list."));
                    } else {
                        System.out.println(format("Now you have "  + Task.count + " tasks in the list."));}
                    System.out.println(line);
                }
            } catch (DukeException ex) {
                format_print(ex.getMessage());
            }
        }
    }
}
