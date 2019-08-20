import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String next = sc.nextLine();
            String firstWord = next.split(" ")[0];
            //String remaining = next.split(" ", 2)[1];
            if (next.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else if (next.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + ". " + tasks.get(i));
                }
            } else if (firstWord.equals("done")) {
                int index = Integer.parseInt(next.split(" ")[1]);
                tasks.get(index - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done\n" + tasks.get(index - 1));
            } else if (firstWord.equals("delete")) {
                int target = Integer.parseInt(next.split(" ")[1]);
                System.out.println("Noted. I've removed this task:\n\t" + tasks.get(target - 1));
                tasks.remove(target - 1);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (firstWord.equals("todo") || firstWord.equals("deadline") || firstWord.equals("event")){
                try {
                    String[] arrCommands = next.split(" ");
                    if (arrCommands.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a " + firstWord + " cannot be empty.");
                    }
                    // remaining is the remaining words after the first command, e.g. todo
                    String remaining = next.split(" ", 2)[1];
                    // desc is the description of the task
                    String desc = remaining.split("/")[0];
                    switch (firstWord) {
                        case "todo":
                            Task todo = new ToDo(desc);
                            tasks.add(todo);
                            System.out.println("Got it. I've added this task: \n\t"
                                    + todo + "\n" + "Now you have " + tasks.size() + " tasks in the list.");
                            break;
                        case "deadline":
                            String dueDateDeadline = remaining.split("/by ")[1];
                            Task deadline = new Deadline(desc, dueDateDeadline);
                            tasks.add(deadline);
                            System.out.println("Got it. I've added this task: \n\t"
                                    + deadline + "\n" + "Now you have " + tasks.size() + " tasks in the list.");
                            break;
                        case "event":
                            String dueDateEvent = remaining.split("/at ")[1];
                            Task event = new Event(desc, dueDateEvent);
                            tasks.add(event);
                            System.out.println("Got it. I've added this task: \n\t"
                                    + event + "\n" + "Now you have " + tasks.size() + " tasks in the list.");
                            break;
                        default:
                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } catch (DukeException de) {
                    System.out.println(de);
                } catch (Exception e) {
                    System.out.println("General Error");
                }
            } else {
                try {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                catch (DukeException de) {
                    System.out.println(de);
                }
            }
        }
    }
}
