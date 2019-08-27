package duke;

import java.util.Scanner;

public class Parser {

    /**
     * Handles and parses text commands input by user.
     * @param in The input from user.
     * @param sc Scanner object from UI class.
     * @return false signals that user wants to quit.
     */
    public static boolean handleCommand(String in, Scanner sc)
        throws DukeException {
        switch (in) {
            case "bye":
                System.out.println("Bye!");
                return false;
            case "list":
                TaskList.printTasks();
                break;
            case "done":
                {
                    Task t = TaskList.get(sc.nextInt());
                    t.markDone();
                    System.out.println(
                        String.format(
                            "Nice! I've marked this task as done:\n  %s",
                            t.toString()
                        )
                    );
                    break;
                }
            case "delete":
                TaskList.removeTask(sc.nextInt());
                break;
            case "todo":
                {
                    String details = sc.nextLine().trim();
                    if (details.isEmpty()) {
                        throw new DukeException("Todo name cannot be empty!");
                    }
                    TaskList.addTask(new ToDo(details));
                    break;
                }
            case "deadline":
                {
                    String[] details = sc.nextLine().trim().split(" /by ");
                    try {
                        TaskList.addTask(new Deadline(details[0], details[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException(
                            "Too few details for deadline!"
                        );
                    }
                    break;
                }
            case "event":
                {
                    String[] details = sc.nextLine().trim().split(" /at ");
                    try {
                        TaskList.addTask(new Event(details[0], details[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("Too few details for event!");
                    }
                    break;
                }
            default:
                throw new DukeException("Unknown command " + in);
        }
        return true;
    }
}
