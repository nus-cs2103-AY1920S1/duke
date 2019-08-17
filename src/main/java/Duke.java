import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String inputln;
        String[] inputs;
        int index;
        Task task;
        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        System.out.println("    " + "____________________________________________________________");
        System.out.println("    " + "Hello! I'm Duke");
        System.out.println("    " + "What can I do for you?");
        System.out.println("    " + "____________________________________________________________\n");

        while (true) {
            inputln = sc.nextLine();
            inputs = inputln.split(" ");
            if (inputs[0].equals("bye")) {
                System.out.println("    " + "____________________________________________________________");
                System.out.println("    " + "Bye. Hope to see you again soon!");
                System.out.println("    " + "____________________________________________________________\n");
                break;
            }
            else if (inputs[0].equals("list")) {
                System.out.println("    " + "____________________________________________________________");
                System.out.println("    " + "Here are the tasks in your list:");
                index = 1;
                for (Task t: list) System.out.println("    " + (list.indexOf(t) + 1) + ". " + t);
                System.out.println("    " + "____________________________________________________________\n");
            }
            else if (inputs[0].equals("done")) {
                int i = Integer.parseInt(inputs[1]);
                System.out.println("    " + "____________________________________________________________");
                try {
                    task = list.get(i - 1);
                    task.setDone();
                    System.out.println("    " + "Nice! I've marked this task as done: ");
                    System.out.println("      " + task);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("    " + "Invalid index, please try again");
                }
                System.out.println("    " + "____________________________________________________________\n");
            }
            else {
                task = new Task(inputln);
                list.add(task);
                System.out.println("    " + "____________________________________________________________");
                System.out.println("    " + "Added: " + task);
                System.out.println("    " + "____________________________________________________________\n");
            }
        }
    }
}
