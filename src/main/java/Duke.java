import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private void run() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you ?");

        Scanner scanner = new Scanner(System.in);
        String[] next = scanner.nextLine().trim().split(" ");
        TodoList todolist = new TodoList();

        while (!next[0].equals("bye")) {
            try {
                switch (next[0]) {
                    case "list":
                        System.out.println(reply(todolist.toString()));
                        break;
                    case "done": {
                        int index = Integer.parseInt(next[1]);
                        if (index > todolist.length()) throw new DukeException("OOPS!!! That's an invalid index");
                        Task completed = todolist.markAsDone(index);
                        System.out.println(reply("Nice! I've marked this task as done:\n  "
                                + completed.toString()));
                        break;
                    }
                    case "delete": {
                        int index = Integer.parseInt(next[1]);
                        if (index > todolist.length()) throw new DukeException("OOPS!!! That's an invalid index");
                        Task deleted = todolist.delete(index);
                        System.out.println(reply("Noted. I've removed this task: \n  " + deleted + "\nNow you have " + todolist.length() + " tasks in the list."));
                        break;
                    }
                    default:
                        Task task;
                        switch (next[0]) {
                            case "todo":
                                String[] desc = Arrays.copyOfRange(next, 1, next.length);
                                if (desc.length == 0)
                                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                                task = new Todo(desc);
                                break;
                            case "deadline": {
                                String[] details = String.join(" ", next).split("/by");
                                if (details.length == 1)
                                    throw new DukeException("OOPS!!! The deadline of a deadline cannot be empty.");
                                task = new Deadline(details[0].trim(), details[1].trim());
                                break;
                            }
                            case "event": {
                                String[] details = String.join(" ", next).split("/at");
                                if (details.length == 1)
                                    throw new DukeException("OOPS!!! The duration of an event cannot be empty.");
                                task = new Event(details[0].trim(), details[1].trim());
                                break;
                            }
                            default:
                                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }
                        todolist.add(task);
                        System.out.println(reply("Got it. I've added this task: \n  " + task + "\nNow you have " + todolist.length() + " tasks in the list."));
                        break;
                }
            } catch (DukeException e) {
                System.out.println(reply(e.toString()));
            } finally {
                next = scanner.nextLine().trim().split(" ");
            }
        }
        System.out.println(reply("Bye. Hope to see you again soon!"));
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    private static String reply(String string) {
        return "---------------------------\n" + string + "\n---------------------------";
    }
}
