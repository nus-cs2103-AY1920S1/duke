import java.util.Scanner;
import java.util.ArrayList;

public class Duke extends Task {

    public Duke(String description) {
        super(description);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        ArrayList<Task> alist = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            String part1 = parts[0];
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:\n");
                for (int i = 1; i <= alist.size(); i++) {
                    Task task = alist.get(i - 1);
                    System.out.println(i + ". [" + task.getStatusIcon() + "] " + task.description);
                }
            } else if (part1.equals("done")) {
                String part2 = parts[1];
                int index = Integer.parseInt(part2);
                Task task = alist.get(index - 1);
                task.markDone();
                System.out.println("Nice! I've marked this task as done: \n" +
                        "[" + task.getStatusIcon() + "] " + task.description);
            }else {
                alist.add(new Task(input));
                System.out.println("added: " + input + "\n");
            }
        }
    }
}
