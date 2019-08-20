import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            String command = sc.next();
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (command.equals("list")) {
                System.out.println("Here are the " + (tasks.size() == 1 ? "task" : "tasks") + " in your list:");
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println(i + "." + tasks.get(i - 1));
                }
            } else if (command.equals("done")) {
                int number = sc.nextInt();
                System.out.println("Nice! I've marked this task as done:");
                tasks.get(number - 1).markAsDone();
                System.out.println("  " + tasks.get(number - 1));
            } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                System.out.println("Got it. I've added this task:");
                if (command.equals("todo")) {
                    String task = sc.next() + sc.nextLine();
                    Task t = new Todo(task);
                    tasks.add(t);
                    System.out.println("  " + t);
                } else if (command.equals("deadline")) {
                    String sentence = sc.nextLine();
                    String[] arr = sentence.split("/by");
                    Task t = new Deadline(arr[0].trim(), arr[1].trim());
                    tasks.add(t);
                    System.out.println("  " + t);
                } else if (command.equals("event")) {
                    String sentence = sc.nextLine();
                    String[] arr = sentence.split("/at");
                    Task t = new Event(arr[0].trim(), arr[1].trim());
                    tasks.add(t);
                    System.out.println("  " + t);
                }
                System.out.println("Now you have " + tasks.size() + " " + (tasks.size() == 1 ? "task" : "tasks") + " in the list.");
            }
        }
    }
}
