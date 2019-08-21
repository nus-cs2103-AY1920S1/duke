import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static String divider = "\t____________________________________________________________\n";
    private static String intro = "\t Hello! I'm Duke\n\t What can I do for you?\n";
    private static String goodbye = "\t Bye. Hope to see you again soon!\n";
    private static String tasklist_header = "\t Here are the tasks in your list:\n";
    private static String done_message = "\t Nice! I've marked this task as done:\n";
    private static String task_added_message = "\t Got it. I've added this task:\n";
    private static String delete_message = "\t Noted. I've removed this task:\n";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        print(intro);

        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        ArrayList<Task> list = new ArrayList<>(100);

        while (run) {
            try {
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
                        if (list.isEmpty()) {
                            throw new DukeException("There are no tasks to display.");
                        }
                        StringBuilder s = new StringBuilder(tasklist_header);
                        for (int i = 0; i < list.size(); i++) {
                            s.append("\t " + (i+1) + ".").append(list.get(i));
                        }
                        print(s.toString());
                        break;
                    }
                    case "done": {
                        int id;
                        try {
                            id = Integer.valueOf(params[0]);
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("You need to specify a task ID to mark as done.");
                        }
                        Task current;
                        try {
                            current = list.get(id - 1);
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("No such task with that ID.");
                        }
                        boolean status = current.markAsComplete();
                        if (!status) {
                            throw new DukeException("Action already marked as done!");
                        }
                        String s = done_message + "\t   " + current;
                        print(s);
                        break;
                    }
                    case "delete":{
                        int id;
                        try {
                            id = Integer.valueOf(params[0]);
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("You need to specify a task ID to delete.");
                        }
                        Task current;
                        try {
                            current = list.remove(id - 1);
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("No such task with that ID.");
                        }
                        String s = delete_message + "\t   " + current;
                        print(s);
                        break;
                    }
                    case "todo": {
                        String task = joinStrings(params);
                        if (task.isEmpty()) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                        Task current = new ToDo(task);
                        list.add(current);
                        String s = task_added_message + "\t   " + current + totalNoOfTasks(list);
                        print(s);
                        break;
                    }
                    case "deadline": {
                        if (joinStrings(params).isEmpty()) {
                            throw new DukeException("The description and due date of a deadline cannot be empty.");
                        }
                        String[] details = splitByIdentifier(params, "/by");
                        if (details[0].isEmpty() && details[1].isEmpty()) {
                            throw new DukeException("You need to specify a due date, denoted by /by");
                        } else if (details[0].isEmpty() && !details[1].isEmpty()) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        }
                        Task current = new Deadline(details[0], details[1]);
                        list.add(current);
                        String s = task_added_message + "\t   " + current + totalNoOfTasks(list);
                        print(s);
                        break;
                    }
                    case "event": {
                        if (joinStrings(params).isEmpty()) {
                            throw new DukeException("The description and due date of an event cannot be empty.");
                        }
                        String[] details = splitByIdentifier(params, "/at");
                        if (details[0].isEmpty() && details[1].isEmpty()) {
                            throw new DukeException("You need to specify a due date, denoted by /at");
                        } else if (details[0].isEmpty() && !details[1].isEmpty()) {
                            throw new DukeException("The description of an event cannot be empty.");
                        }
                        Task current = new Event(details[0], details[1]);
                        list.add(current);
                        String s = task_added_message + "\t   " + current + totalNoOfTasks(list);
                        print(s);
                        break;
                    }
                    default: {
                        throw new DukeException("I'm sorry, but I don't know what that means.");
                    }
                }
            } catch (DukeException e) {
                print(e.toString());
            }

        }

    }

    private static void print (String s) {
        System.out.print(divider);
        System.out.print(s);
        System.out.print(divider);
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

    private static String totalNoOfTasks(ArrayList list) {
        int noOfTasks = list.size();
        return "\t Now you have " + (noOfTasks) + (noOfTasks == 1? " task" : " tasks") + " in the list.\n";
    }

}
