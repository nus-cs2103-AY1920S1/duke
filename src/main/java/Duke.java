import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String desc, input, by, duration, indent = "    ";
        String[] inputs, inputFormatted;
        Task task;
        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>();


        System.out.println(indent + "____________________________________________________________");
        System.out.println(indent + " Hello! I'm Duke");
        System.out.println(indent + " What can I do for you?");
        System.out.println(indent + "____________________________________________________________\n");

        while (true) {
            input = sc.nextLine();
            inputs = input.split(" ", 2);
            System.out.println(indent + "____________________________________________________________");
            // Exit program
            if (inputs[0].equals("bye")) {
                System.out.println(indent + " Bye. Hope to see you again soon!");
                break;
            }
            // Show list of events
            else if (inputs[0].equals("list")) {
                System.out.println(indent + " Here are the tasks in your list:");
                for (Task t: list) System.out.println(indent + " " + (list.indexOf(t) + 1) + ". " + t);
            }
            // Mark as Done
            else if (inputs[0].equals("done")) {
                int i = Integer.parseInt(inputs[1]);
                try {
                    task = list.get(i - 1);
                    task.setDone();
                    System.out.println(indent + " Nice! I've marked this task as done: ");
                    System.out.println(indent + "   " + task);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(indent + " Invalid index, please try again");
                }
            }
            else if (inputs[0].equals("todo") || inputs[0].equals("deadline") || inputs[0].equals("event")) {
                try {
                    if (inputs[0].equals("todo")) {
                        task = new Todo(inputs[1]);
                    } else if (inputs[0].equals("deadline")) {
                        if (!inputs[1].contains("/by")) throw new Exception();
                        inputFormatted = inputs[1].split(" /by ", 2);
                        task = new Deadline(inputFormatted[0], inputFormatted[1]);
                    } else /*if (inputs[0].equals("event"))*/ {
                        if (!inputs[1].contains("/at")) throw new Exception();
                        inputFormatted = inputs[1].split(" /at ", 2);
                        task = new Event(inputFormatted[0], inputFormatted[1]);
                    }
                    list.add(task);
                    System.out.println(indent + " Got it. I've added this task:");
                    System.out.println(indent + "   " + task);
                    System.out.println(indent + " Now you have " + list.size() + " tasks in the list.");
                } catch (Exception e) {
                    System.out.println(indent + " Please enter date info using '/by' for deadline and '/at' for event");
                }
            }

            System.out.println(indent + "____________________________________________________________\n");
        }
    }
}
