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

        ArrayList<Task> xs = new ArrayList<>();
        String command = "";

        while (true) {
            command = sc.nextLine();
            String[] arr = command.split(" ");

            if (arr[0].equals("done")) {
                int taskNum = Integer.parseInt(arr[1]);
                xs.get(taskNum - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(xs.get(taskNum - 1));
            } else {
                if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals("list")) {
                    for (int i = 1; i <= xs.size(); i++) {
                        System.out.println("Here are the tasks in your list:");
                        System.out.println(i + ". " + xs.get(i - 1));
                    }
                } else {
                    xs.add(new Task(command));
                    System.out.println("added: " + command);
                }
            }
        }
    }
}
