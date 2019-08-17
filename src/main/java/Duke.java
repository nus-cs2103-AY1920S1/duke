import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String welcomeMessage = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(welcomeMessage);

        Scanner sc = new Scanner(System.in);

        String[] tasks = new String[100];
        int num_tasks = 0;
        String input = "";

        while (true) {
            input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                String task = "";
                // print all tasks in list
                for (int i = 0; i < num_tasks; i++) {
                    task = (i + 1) + ". " + tasks[i];
                    System.out.println(task);
                }
            } else {
                // add task to list
                tasks[num_tasks] = input;
                num_tasks++;
                System.out.println("added: " + input);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
