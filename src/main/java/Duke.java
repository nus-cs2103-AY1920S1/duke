import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int index = 0;

        String greeting = "     Hello! I'm Duke\n" +
                "     What can I do for you?";
        System.out.println(greeting);

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("     Bye. Hope to see you again soon!");
                break;
            }
            if (input.equals("list")) {
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    System.out.printf("     %d.%s\n", i + 1, tasks[i]);
                }
            } else if (input.length() == 6
                    && input.substring(0, 5).equals("done ")
                    && Character.getNumericValue(input.charAt(5)) > 0
                    && Character.getNumericValue(input.charAt(5)) <= index) {
                Task task = tasks[Character.getNumericValue(input.charAt(5)) - 1];
                task.markAsDone();
                System.out.println("     Nice! I've marked this task as done: ");
                System.out.printf("       %s\n", task);
            } else {
                Task task = new Task(input);
                tasks[index] = task;
                index++;
                System.out.printf("     added: %s\n", task);
            }
        }
    }
}
