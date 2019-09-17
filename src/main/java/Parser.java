/** Handles user input and decides on what to do. */
public class Parser {

    /**
    * Handles and parses text commands input by user.
    *
    * @param in The input from user.
    * @param sc Scanner object from UI class.
    * @return false signals that user wants to quit.
    */
    public static String handleCommand(String input) throws DukeException {
        String[] arr = input.split(" ", 2);
        String in = arr[0].trim();
        String others = "";
        if (arr.length > 1) {
            others = arr[1].trim(); // extra info
        }

        switch (in) {
            case "bye":
                return "Bye!";
            case "list":
                return "Here are the tasks in your list:\n" +
                    TaskList.stringifyTasks();
            case "done":
                {
                    Task t = TaskList.get(Integer.parseInt(others) - 1);
                    t.markDone();
                    return String.format(
                        "Nice! I've marked this task as done:\n  %s",
                        t.toString()
                    );
                }
            case "delete":
                TaskList.removeTask(Integer.parseInt(others) - 1);
                return "Deleted!";
            case "todo":
                {
                    if (others.isEmpty()) {
                        throw new DukeException("Todo name cannot be empty!");
                    }
                    return TaskList.addTask(new ToDo(others));
                }
            case "deadline":
                {
                    String[] details = others.split(" /by ");
                    try {
                        return TaskList.addTask(
                            new Deadline(details[0], details[1])
                        );
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException(
                            "Wrong format for deadline! Format: <name> /by dd/mm/yyyy hh:mm"
                        );
                    }
                }
            case "event":
                {
                    String[] details = others.split(" /at ");
                    try {
                        return TaskList.addTask(
                            new Event(details[0], details[1])
                        );
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException(
                            "Wrong format for event! Format: <name> /at dd/mm/yyyy hh:mm"
                            );
                    }
                }
            case "find":
                return ("Here are matching tasks in your list:") +
                    TaskList.stringifyTasks(TaskList.query(others));
            case "help":
                return MainWindow.greeting;
            default:
                throw new DukeException("Unknown command " + in);
        }
    }
}
