import java.util.Scanner;

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
        Tasks tasks = new Tasks();
        String input = sc.next();
        while(!input.equals("bye")) {
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
                        if(todoDescription.equals("")) {
                            throw new NullDescriptionException("todo");
                        }
                        tasks.addTodo(todoDescription);
                        break;
                    case "deadline":
                        String deadlineLine = sc.nextLine().trim();
                        String[] splitBy = deadlineLine.split("/by");
                        if(splitBy.length == 2) {
                            tasks.addDeadline(splitBy[0], splitBy[1]);
                        }
                        break;
                    case "event":
                        String eventLine = sc.nextLine().trim();
                        String[] splitAt = eventLine.split("/at");
                        if(splitAt.length == 2) {
                            tasks.addDeadline(splitAt[0], splitAt[1]);
                        }
                        break;
                    default :
                        throw new InvalidArgumentException();
                }
            } catch (DukeException ex) {
                System.out.println(ex);
            }
            input = sc.next();
        }
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
