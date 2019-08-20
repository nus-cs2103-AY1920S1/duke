import java.util.ArrayList;
import java.util.Scanner;

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

        while (true) {
            String input = sc.nextLine();
            String[] splitString = input.split(" ");
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");

                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + ".[" + tasks.get(i).getStatusIcon()
                            + "] " + tasks.get(i).getDescription());
                }
            } else if (splitString[0].equals("done")) {
                int taskNum = Integer.valueOf(splitString[1]) - 1;
                tasks.get(taskNum).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [" + tasks.get(taskNum).getStatusIcon() + "] " +
                        tasks.get(taskNum).getDescription());
            } else if (input.equals("bye")) {
                System.out.println("Bye! Hope to see you again!");
                return;
            } else {
                tasks.add(new Task(input));
                System.out.println("added: " + input);
            }
        }
    }
}