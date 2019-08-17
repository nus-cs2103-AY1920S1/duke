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
            Command cmd = new Command(input);
            String cmdKeyword = cmd.getKeyword();
            String cmdArgs = cmd.getArgs();

            String errorMessage = "Your command is invalid, please try again.";

            if (cmdKeyword.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (cmdKeyword.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i));
                }
            } else if (cmdKeyword.equals("done")) {
                try {
                    int position = Integer.parseInt(cmdArgs) - 1;
                    Task task = tasks.get(position);
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + task);
                } catch (Exception e) {
                    System.out.println(errorMessage);
                }
            } else if (cmdKeyword.equals("todo")) {
                try {
                    Task task = new Task(cmdArgs);
                    tasks.add(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } catch (Exception e) {
                    System.out.println(errorMessage);
                }
            } else {
                System.out.println(errorMessage);
            }
        }
    }
}
