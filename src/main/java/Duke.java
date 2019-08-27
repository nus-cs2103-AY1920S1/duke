import java.io.*;
import java.util.Scanner;
import java.util.stream.Stream;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                      + "|  _ \\ _   _| | _____ \n"
                      + "| | | | | | | |/ / _ \\\n"
                      + "| |_| | |_| |   <  __/\n"
                      + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        Scanner sc = new Scanner(System.in);
        TaskManager tasks = new TaskManager();
        tasks.readFile();
        String input = sc.next();
        while (!input.equals("bye")) {
            try {
                switch (input) {
                case "list":
                    tasks.list();
                    break;
                case "done":
                    tasks.done(sc.nextInt());
                    break;
                case "todo":
                    String todoDescription = sc.nextLine().trim();
                    if (todoDescription.equals("")) {
                        throw new NullDescriptionException("todo");
                    }
                    tasks.add("todo", todoDescription, null);
                    break;
                case "deadline":
                    String deadlineLine = sc.nextLine().trim();
                    String[] splitBy = deadlineLine.split(" /by ");
                    if (splitBy.length == 2) {
                        tasks.add("deadline", splitBy[0], splitBy[1]);
                    }
                    break;
                case "event":
                    String eventLine = sc.nextLine().trim();
                    String[] splitAt = eventLine.split(" /at ");
                    if (splitAt.length == 2) {
                        tasks.add("event", splitAt[0], splitAt[1]);
                    }
                    break;
                case "delete":
                    tasks.delete(sc.nextInt());
                    break;
                default:
                    throw new InvalidArgumentException();
                }
            } catch (DukeException ex) {
                System.out.println(ex);
            }
            input = sc.next();
        }
        tasks.writeFile();
        bye();
    }

    private static void greet() {
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    ------------------------------------------------------------");
    }

    private static void bye() {
        System.out.println("    ------------------------------------------------------------");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ------------------------------------------------------------");
    }

}
