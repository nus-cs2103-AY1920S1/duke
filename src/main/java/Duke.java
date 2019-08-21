import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        while (true) {
            try {
                String input = sc.nextLine();
                String[] splitString = input.split(" ");
                String taskType = splitString[0];
                if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");

                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + "." + tasks.get(i).toString());
                    }
                } else if (splitString[0].equals("done")) {
                    int taskNum = Integer.valueOf(splitString[1]) - 1;
                    tasks.get(taskNum).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("    [" + tasks.get(taskNum).getStatusIcon() + "] " +
                            tasks.get(taskNum).getDescription());
                } else if (input.equals("bye")) {
                    System.out.println("Bye! Hope to see you again!");
                    return;
                } else if (taskType.equals("todo") || taskType.equals("deadline")
                        || taskType.equals("event")){
                    switch (taskType) {
                        case "todo":
                            String[] todoSplit = input.split("todo ");
                            if (todoSplit.length == 1 || todoSplit[1].isEmpty()) {
                                throw new InvalidDescriptionException("\u2639 OOPS!!! The description of a todo cannot be empty.");
                            }
                            Todo todo = new Todo(todoSplit[1].trim());
                            tasks.add(todo);
                            System.out.println("Got it. I've added this task:\n  " + todo);
                            break;
                        case "deadline": {
                            String[] deadlineSplit = input.split("/by ");
                            String[] temp = deadlineSplit[0].split("deadline ");
                            if (temp.length == 1 || temp[1].isEmpty()) {
                                throw new InvalidDescriptionException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
                            }
                            Deadline deadline = new Deadline(temp[1].trim(), deadlineSplit[1].trim());
                            tasks.add(deadline);
                            System.out.println("Got it. I've added this task:\n " + deadline);
                            break;
                        }
                        case "event": {
                            String[] deadlineSplit = input.split("/at ");
                            String[] temp = deadlineSplit[0].split("event ");
                            if (temp.length == 1 || temp[1].isEmpty()) {
                                throw new InvalidDescriptionException("\u2639 OOPS!!! The description of a event cannot be empty.");
                            }
                            Event event = new Event(temp[1].trim(), deadlineSplit[1].trim());
                            tasks.add(event);
                            System.out.println("Got it. I've added this task:\n " + event);
                            break;
                        }
                        default: {
                            System.out.println("If you see this something is wrong >:D");
                        }
                    }
                    if (tasks.size() == 1) {
                        System.out.println("Now you have " + tasks.size() + " task in the list.");
                    } else {
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    }
                } else {
                    throw new InvalidCommandException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}