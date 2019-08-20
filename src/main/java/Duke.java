import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "    ____________________________________________________________";

        //Greetings message
        System.out.println(horizontalLine);
        System.out.println("     Hello from! I'm Duke\n" + "     What can I do for you?");
        System.out.println(horizontalLine + "\n");

        //Setup Scanner and List
        Scanner sc = new Scanner(System.in);
        List<Task> store = new ArrayList<Task>();

        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println(horizontalLine);
                System.out.println("     Here are the tasks in your list:");
                int i = 1;
                for (Task task : store) {
                    System.out.println("     " + i + ". [" + task.getStatusIcon() + "] " + task);
                    i++;
                }
                System.out.println(horizontalLine + "\n");
            } else if (userInput.length() == 6 && userInput.substring(0,4).equals("done")) {
                Task task = store.get(Integer.parseInt(userInput.charAt(5) + "") - 1);
                task.markAsDone();
                System.out.println(horizontalLine);
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       [" + task.getStatusIcon() + "] " + task);
                System.out.println(horizontalLine + "\n");
            } else {
                store.add(new Task(userInput));
                System.out.println(horizontalLine);
                System.out.println("     " + "added: " + userInput);
                System.out.println(horizontalLine + "\n");
            }
            userInput = sc.nextLine();
        }

        System.out.println(horizontalLine);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);

    }
}
