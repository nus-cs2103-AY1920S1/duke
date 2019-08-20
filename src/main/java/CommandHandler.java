import java.util.List;

public class CommandHandler {

    private TaskList tasks;

    public CommandHandler(TaskList tasks) {
        this.tasks = tasks;
    }

    public void executeCommand(String cmd, String input) {
        switch (cmd) {
            case "list":
                tasks.printTasks();
                break;
            case "done":
                int id = Integer.parseInt(input);
                tasks.markTaskAsCompleted(id);
                break;
            case "bye":
                OutputUtilities.sayBye();
                return;
            case "todo":
                String todoText = input;
                tasks.addTask( new Todo(todoText));
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
        }
    }


}
