import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>(100);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i));
                }
            } else if (input.startsWith("done")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                taskList.get(index - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskList.get(index - 1));
            } else {
                System.out.println("added: " + input);
                taskList.add(new Task(input));
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
