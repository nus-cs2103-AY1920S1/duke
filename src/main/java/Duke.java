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
            if (next[0].equals("list")) {
                System.out.println(reply(todolist.toString()));
            } else if (next[0].equals("done")) {
                int index = Integer.parseInt(next[1]);
                Task completed = todolist.markAsDone(index);
                System.out.println(reply("Nice! I've marked this task as done:\n  "
                        + completed.toString()));
            } else {
                Task task = new Task("placeholder");
                if (next[0].equals("todo")) {
                    task = new Todo(Arrays.copyOfRange(next, 1, next.length));
                } else if (next[0].equals("deadline")) {
                    String[] details = String.join(" ", next).split("/by");
                    task = new Deadline(details[0].trim(), details[1].trim());
                } else if (next[0].equals("event")) {
                    String[] details = String.join(" ", next).split("/at");
                    task = new Event(details[0].trim(), details[1].trim());
                }
                todolist.add(task);
                System.out.println(reply("Got it. I've added this task: \n" + task + "\n  Now you have " + todolist.length() + " tasks in the list."));
            }
            next = scanner.nextLine().trim().split(" ");
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
