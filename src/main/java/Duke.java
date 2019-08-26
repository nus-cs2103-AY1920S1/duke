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
            } else {
                addTask(s);
            }
        }
    }

    private static void markTaskAsDone(int index) {
        Task t = tasks.get(index);
        t.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + t.getStatusIcon() + "] " + t.getDescription());
    }

    private static void addTask(String description) {
        tasks.add(new Task(description));
        System.out.println("added: " + description);
    }

    private static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.println((i + 1) + ".[" + t.getStatusIcon() + "] " + t.getDescription());
        }
    }
}

