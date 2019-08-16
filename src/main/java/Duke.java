import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    /**
     * This is the main method and entry point for the Duke program.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i));
                }
            } else if (input.split(" ")[0].equals("done")) {
                try {
                    int position = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task task = tasks.get(position);
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + task);
                } catch (Exception e) {
                    System.out.println("Your input was invalid, please try again.");
                }
            } else {
                Task task = new Task(input);
                tasks.add(task);
                System.out.println("added: " + input);
            }
        }
    }
}
