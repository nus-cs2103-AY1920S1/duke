import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
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
                String description = String.join(" ", next);
                todolist.add(new Task(description));
                System.out.println(reply("added: " + description));
            }
            next = scanner.nextLine().trim().split(" ");
        }
        System.out.println(reply("Bye. Hope to see you again soon!"));
    }

    private static String reply(String string) {
        return "---------------------------\n" + string + "\n---------------------------";
    }
}
