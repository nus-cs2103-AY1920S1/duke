import java.util.List;

public class CommandHandler {

    private TaskList tasks;

    public CommandHandler(TaskList tasks) {
        this.tasks = tasks;
    }

    public void executeCommand(String cmd, String input) {
        OutputUtilities.printLine();
        try {
            switch (cmd) {
                case "list":
                    tasks.printTasks();
                    break;
                case "done":
                    int taskNumber = Integer.parseInt(input);
                    tasks.markTaskAsCompleted(taskNumber);
                    break;
                case "bye":
                    OutputUtilities.sayBye();
                    break;
                case "todo":
                    String todoText = input;
                    tasks.addTask(new Todo(todoText));
                    break;
                case "deadline":
                    String deadline = input;
                    String[] deadlineParts = deadline.split(" /by ");
                    String deadLineText = deadlineParts[0];
                    String by = deadlineParts[1];
                    tasks.addTask(new Deadline(deadLineText, by));
                    break;
                case "event":
                    String event = input;
                    String[] eventParts = event.split(" /at ");
                    String eventText = eventParts[0];
                    String at = eventParts[1];
                    tasks.addTask(new Event(eventText, at));
                    break;
                default:
                    throw new InvalidCommandException();
            }
        } catch (DukeException dukeException) {
            if (dukeException instanceof EmptyTodoTextException) System.out.println("\t ☹ OOPS!!! The description of a todo cannot be empty.");
            else if (dukeException instanceof InvalidCommandException) System.out.println("\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        OutputUtilities.printLine();
    }


}
