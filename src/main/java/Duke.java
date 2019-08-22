import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        final String lineSpace = "_______________________________\n";
        String startMessage = lineSpace + "Hello! I'm Duke\nWhat can I do for you?\n" + lineSpace;
        System.out.println(startMessage);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList();
        String taskName;
        Task task;
        while (sc.hasNext()) {
            try {
                String userCmd = sc.next();
                String[] userWords;
                if (userCmd.equals("bye")) {
                    System.out.println(lineSpace + "Bye. Hope to see you again soon!\n" + lineSpace);
                    break;
                }
                switch (userCmd) {
                    case "list":
                        System.out.println(lineSpace + "Here are the tasks in your list:");
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println(i + 1 + "." + list.get(i));
                        }
                        System.out.print(lineSpace);
                        break;
                    case "todo":
                        taskName = sc.nextLine().trim();
                        if (taskName.isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        task = new Task(taskName);
                        list.add(task);
                        System.out.println(lineSpace + "Got it. I've added this task:\n" + task
                                + "\nNow you have " + list.size() + " tasks in the list.\n" + lineSpace);
                        break;
                    case "deadline":
                        taskName = sc.nextLine().trim();
                        if (taskName.isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        userWords = taskName.split("/by");
                        if (userWords.length == 1) {
                            throw new DukeException("☹ OOPS!!! The date/time of a deadline cannot be empty or is wrongly typed.");
                        }
                        task = new Deadline(userWords[0].trim(), userWords[1].trim());
                        list.add(task);
                        System.out.println(lineSpace + "Got it. I've added this task:\n" + task
                                + "\nNow you have " + list.size() + " tasks in the list.\n" + lineSpace);

                        break;
                    case "event":
                        taskName = sc.nextLine().trim();
                        if (taskName.isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        }
                        userWords = taskName.split("/at");
                        if (userWords.length == 1) {
                            throw new DukeException("☹ OOPS!!! The date/time of an event cannot be empty or is wrongly typed.");
                        }
                        task = new Event(userWords[0].trim(), userWords[1].trim());
                        list.add(task);
                        System.out.println(lineSpace + "Got it. I've added this task:\n" + task
                                + "\nNow you have " + list.size() + " tasks in the list.\n" + lineSpace);
                        break;
                    case "done":
                        int taskNo = sc.nextInt();
                        list.get(taskNo - 1).markAsDone();
                        System.out.println(lineSpace + "Nice! I've marked this task as done:\n"
                                + list.get(taskNo - 1) + "\n" + lineSpace);
                        break;
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}

