import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {

    private static List<Task> tasks = new ArrayList<>(100);

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner userInput = new Scanner(System.in);
        while (true) {
            String s = userInput.nextLine().trim();
            if (s.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                userInput.close();
                break;
            } else if (s.equals("list")){
                printTasks();
            } else if (s.matches("done ([1-9]|[1-9][0-9]|100)")) {
                int displayNumber = Integer.parseInt(s.substring(5));
                markTaskAsDone(displayNumber - 1);
            } else if (s.startsWith("todo ")) {
                s = s.substring(5);
                addTask(new Todo(s));
            } else if (s.startsWith("deadline ")) {
                s = s.substring(9);
                String[] arguments = s.split("/by");
                addTask(new Deadline(arguments[0].trim(), arguments[1].trim()));
            } else if (s.startsWith("event ")) {
                s = s.substring(6);
                String[] arguments = s.split("/at");
                addTask(new Event(arguments[0].trim(), arguments[1].trim()));
            }
        }
    }

    private static void addTask(Task task) {
        System.out.println("Got it. I've added this task:");
        tasks.add(task);
        System.out.println(task);
        if (tasks.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }

    }

    private static void markTaskAsDone(int index) {
        Task t = tasks.get(index);
        t.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
    }

    private static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }
}

