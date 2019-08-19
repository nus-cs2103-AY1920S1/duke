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
                int num = Integer.parseInt(s.split(" ")[1]);
                tasks[num - 1].isDone = true;
                String[] print_list = {"Nice! I've marked this task as done: ", tasks[num - 1].done()};
                format_print(print_list);
            } else { // add new task
                String type = s.substring(0, s.indexOf(" "));
                String description = s.substring(s.indexOf(" ") + 1);
                Task newTask;
                if (type.equals("todo")) {
                    newTask = new Todo(description);
                } else if (type.equals("event")) {
                    newTask = new Event(description);
                } else {
                    newTask = new Deadline(description);
                }
                tasks[Task.count - 1] = newTask;
                System.out.println(line);
                System.out.println(format("Got it. I've added this task: "));
                System.out.println(format(newTask.toString()));
                if (Task.count == 1) {
                    System.out.println(format("Now you have 1 task in the list."));
                } else {
                    System.out.println(format("Now you have "  + Task.count + " tasks in the list."));}
                System.out.println(line);
            }
        }
    }
}
