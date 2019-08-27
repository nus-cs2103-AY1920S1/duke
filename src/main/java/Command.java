import java.time.format.DateTimeParseException;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.String.format;

public class Command {
    private String line;
    private boolean isExit;

    public Command(String line) {
        this.isExit = line.equals("bye");
        this.line = line;
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        String[] arr = line.split(" ", 2);
        String cmd = arr[0];
        switch (cmd) {
            case "todo":
                try {
                    if (arr.length < 2) {
                        throw new DukeException(":( OOPS!!! The description of a todo cannot be empty.");
                    }
                    String toDoContent = arr[1];
                    Task todo = new Todo(toDoContent);
                    tasklist.addTask(todo);
                    ui.printStatement("Got it. I've added this task:",
                            format("  %s", todo),
                            format("Now you have %d tasks in the list.", tasklist.getTaskSize()));
                    storage.updateData(tasklist);
                } catch (DukeException e) {
                    ui.showLoadingError(e);
                }
                break;
            case "deadline":
                try {
                    String[] deadline_content = arr[1].split(" /by ", 2);
                    if (arr.length < 2 || deadline_content.length < 2) {
                        throw new DukeException("Invalid format for Deadline Task.");
                    }
                    String[] datetime = deadline_content[1].split(" ", 2);
                    if (datetime.length < 2) {
                        throw new DukeException("Invalid format for Deadline Task.");
                    }
                    try {
                        Task deadline = new Deadline(deadline_content[0], datetime[0], datetime[1]);
                        tasklist.addTask(deadline);
                        ui.printStatement("got it. i've added this task:",
                                String.format("  %s", deadline),
                                String.format("Now you have %d tasks in the list.", tasklist.getTaskSize()));
                    } catch (DateTimeParseException e) {
                        ui.showLoadingError(new DukeException("Invalid Date Time format input"));
                    }
                    storage.updateData(tasklist);
                } catch (DukeException e) {
                    ui.showLoadingError(e);
                }
                break;
            case "event":
                try {
                    String[] eventContent = arr[1].split(" /at ", 2);
                    if (eventContent.length < 2) {
                        throw new DukeException("Invalid format for Event Task.");
                    }
                    Task event = new Event(eventContent[0], eventContent[1]);
                    tasklist.addTask(event);
                    ui.printStatement("got it. i've added this task:",
                            format("  %s", event),
                            format("Now you have %d tasks in the list.", tasklist.getTaskSize()));
                    storage.updateData(tasklist);
                } catch (DukeException e) {
                    ui.showLoadingError(e);
                }
                break;
            case "done":
                try {
                    if (arr.length < 2) {
                        throw new DukeException("An Integer is required to choose the task.");
                    }
                    int index = Integer.parseInt(arr[1]);
                    if (index < 1 || index > tasklist.getTaskSize()) {
                        throw new DukeException("Index out of range.");
                    }
                    tasklist.markDone(index);
                    ui.printStatement("Nice! I've marked this task as done:",
                            format("  %s", tasklist.getTaskByIndex(index).toString()));
                    storage.updateData(tasklist);
                } catch (DukeException e) {
                    ui.showLoadingError(e);
                }
                break;
            case "list":
                Stream<String> taskStream = IntStream
                        .range(0, tasklist.getTaskSize())
                        .mapToObj(i -> {
                            Task t = tasklist.getTaskByIndex(i + 1);
                            return format("%d.%s", i + 1, t.toString());
                        });
                Stream<String> combined = Stream.concat(Stream.of("Here are the tasks in your list:"), taskStream);
                String[] combinedString = combined.toArray(String[]::new);
                ui.printStatement(combinedString);
                break;
            case "delete":
                try {
                    if (arr.length < 2) {
                        throw new DukeException("An Integer is required to delete the task.");
                    }
                    int index = Integer.parseInt(arr[1]);
                    if (index < 1 || index > tasklist.getTaskSize()) {
                        throw new DukeException("Index out of range.");
                    }
                    Task task = tasklist.removeTaskByIndex(index);
                    ui.printStatement("Noted. I've removed this task:",
                            format("  %s", task.toString()),
                            format("Now you have %d tasks in the list.", tasklist.getTaskSize()));
                    storage.updateData(tasklist);
                } catch (DukeException e) {
                    ui.showLoadingError(e);
                }
                break;
            case "bye":
                ui.printStatement("Bye. Hope to see you again soon!");
                break;
            default:
                ui.printStatement(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public boolean isExit() {
        return this.isExit;
    }
}
