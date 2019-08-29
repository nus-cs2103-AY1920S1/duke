import java.util.Scanner;

/**
 * Represents a parser to parse user command.
 */
public class Parser {
    public static void readList(Scanner sc, TaskList tasks) {
        while(sc.hasNext()) {
            try {
                String command = sc.nextLine();
                String[] words = command.split(" ", 2);
                String action = words[0];

                if (command.equals("bye")) {
                    tasks.printBye();
                    return;
                } else if (action.equals("done")) {
                    tasks.doneTask(words);
                } else if (action.equals("delete")) {
                    tasks.deleteTask(words);
                } else if (action.equals("find")) {
                    tasks.findTask(words);
                } else if (action.equals("todo")) {
                    tasks.addTodo(words);
                } else if (action.equals("deadline")) {
                    tasks.addDeadline(words);
                } else if (action.equals("event")) {
                    tasks.addEvent(words);
                } else if (command.equals("list")) {
                    tasks.printList();
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch(DukeException ex) {
                System.out.println("    ____________________________________________________________");
                System.err.println("     " + ex);
                System.out.println("    ____________________________________________________________");
            }

        }
    }
}
