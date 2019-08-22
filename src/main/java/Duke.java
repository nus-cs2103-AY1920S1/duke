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
        String next = scanner.nextLine().trim();
        TodoList todolist = new TodoList();

        while (!next.equals("bye")) {
            if (next.equals("list")) {
                System.out.println(reply(todolist.toString()));
            } else {
                todolist.add(new Task(next));
                System.out.println(reply("added: " + next));
            }
            next = scanner.nextLine().trim();
        }
        System.out.println(reply("Bye. Hope to see you again soon!"));
    }

    private static String reply(String string) {
        return "---------------------------\n" + string + "\n---------------------------";
    }
}
