import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static String divider = "\t____________________________________________________________\n";
    private static String divider_no_newline = "\t____________________________________________________________";
    private static String intro = "\t Hello! I'm Duke\n\t What can I do for you?\n";
    private static String goodbye = "\t Bye. Hope to see you again soon!\n";
    private static String tasklist_header = "\t Here are the tasks in your list:\n";
    private static String done_message = "\t Nice! I've marked this task as done:\n";
    private static String task_added_message = "\t Got it. I've added this task:\n";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.print(divider);
        System.out.print(intro);
        System.out.print(divider);

        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        ArrayList<Task> list = new ArrayList<>(100);

        while (run) {
            String[] input = scanner.nextLine().split(" ");
            String command = input[0];
            String[] params = Arrays.copyOfRange(input, 1, input.length);

            switch (command) {
                case "bye": {
                    print(goodbye);
                    run = false;
                    break;
                }
                case "list": {
                    StringBuilder s = new StringBuilder(tasklist_header);
                    for (Task t: list) {
                        s.append("\t ").append(t);
                    }
                    print(s.toString());
                    break;
                }
                case "done": {
                    int id = Integer.valueOf(params[0]);
                    Task current = list.get(id-1);
                    current.markAsComplete();
                    String s = done_message + "\t   " + current.toStringNoID();
                    print(s);
                    break;
                }
                case "todo": {
                    Task current = new ToDo(joinStrings(params));
                    list.add(current);
                    String s = task_added_message + "\t   " + current.toStringNoID() + Task.totalNoOfTasks();
                    print(s);
                    break;
                }
                case "deadline": {
                    String[] details = splitByIdentifier(params, "/by");
                    Task current = new Deadline(details[0], details[1]);
                    list.add(current);
                    String s = task_added_message + "\t   " + current.toStringNoID() + Task.totalNoOfTasks();
                    print(s);
                    break;
                }
                case "event": {
                    String[] details = splitByIdentifier(params, "/at");
                    Task current = new Event(details[0], details[1]);
                    list.add(current);
                    String s = task_added_message + "\t   " + current.toStringNoID() + Task.totalNoOfTasks();
                    print(s);
                    break;
                }
                default: {
                    print("Invalid task entered. Please try again.\n");
                    break;
                }
            }

            System.out.println();
        }
    }

    private static void print (String s) {
        System.out.print(divider);
        System.out.print(s);
        System.out.print(divider_no_newline);
    }

    private static String joinStrings (String[] strings) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            if (i == strings.length - 1) {
                s.append(strings[i]);
            } else {
                s.append(strings[i]).append(" ");
            }
        }
        return s.toString();
    }

    private static String[] splitByIdentifier(String[] params, String identifier) {
        int split = 0;
        for (int i = 0; i < params.length; i++) {
            if (params[i].equals(identifier)) {
                split = i;
            }
        }
        String task_desc = joinStrings(Arrays.copyOfRange(params, 0, split));
        String task_due = joinStrings(Arrays.copyOfRange(params, split + 1, params.length));
        return new String[]{task_desc, task_due};
    }

}
