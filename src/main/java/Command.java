import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public interface Command {
    void execute(List<Task> tasks, Ui ui, TaskSerializer storage);

    default boolean isExit() {
        return false;
    }
}

class ExitCommand implements Command {
    public void execute(List<Task> tasks, Ui ui, TaskSerializer storage) {
        ui.printBlock("Bye. Hope to see you again soon!");
    }

    public boolean isExit() {
        return true;
    }
}

class ListCommand implements Command {
    public void execute(List<Task> tasks, Ui ui, TaskSerializer storage) {
        ui.displayTasks("Here are the tasks in your list:", tasks);
    }
}

class DoneCommand implements Command {
    private final int taskIndex;

    public DoneCommand(String command) {
        taskIndex = Integer.parseInt(command);
    }

    public void execute(List<Task> tasks, Ui ui, TaskSerializer storage) {
        Task t = tasks.get(taskIndex - 1);
        t.markAsDone();

        storage.save(tasks);
        ui.displaySuccessfullyDoneTask("Nice! I've marked this task as done:", t);
    }
}

class DeleteCommand implements Command {
    private final int taskIndex;

    public DeleteCommand(String command) {
        taskIndex = Integer.parseInt(command);
    }

    public void execute(List<Task> tasks, Ui ui, TaskSerializer storage) {
        Task t = tasks.get(taskIndex - 1);
        tasks.remove(taskIndex - 1);

        storage.save(tasks);

        ui.displaySuccessfullyRemovedTask("Noted. I've removed this task:", t, tasks.size());
    }
}

abstract class AddCommand implements Command {
    protected final String command;
    protected final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public AddCommand(String command) {
        this.command = command;
    }

    abstract protected Task instantiateTask();

    public void execute(List<Task> tasks, Ui ui, TaskSerializer storage) {
        final Task t = instantiateTask();
        tasks.add(t);

        storage.save(tasks);
        ui.displaySuccessfullyAddedTask("Got it. I've added this task: ", t, tasks.size());

    }
}

class AddTodoTaskCommand extends AddCommand {
    public AddTodoTaskCommand(String command) {
        super(command);
    }

    protected Task instantiateTask() {
        String todoDescription = command;
        if (todoDescription.isEmpty()) {
            throw new EmptyTaskDescriptionException("The description of a todo cannot be empty.");
        }
        return new Todo(todoDescription);
    }
}

class AddDeadlineTaskCommand extends AddCommand {
    public AddDeadlineTaskCommand(String command) {
        super(command);
    }

    protected Task instantiateTask() {
        final String[] deadlineArgs = command.split(" /by ");
        final String deadlineDescription = deadlineArgs[0];
        if (deadlineDescription.isEmpty()) {
            throw new EmptyTaskDescriptionException("The description of a deadline cannot be empty.");
        }

        final LocalDateTime deadlineDue = LocalDateTime.parse(deadlineArgs[1], dateTimeFormatter);

        return new Deadline(deadlineDescription, deadlineDue);
    }
}

class AddEventTaskCommand extends AddCommand {
    public AddEventTaskCommand(String command) {
        super(command);
    }

    protected Task instantiateTask() {
        final String[] eventArgs = command.split(" /at ");
        final String eventDescription = eventArgs[0];
        if (eventDescription.isEmpty()) {
            throw new EmptyTaskDescriptionException("The description of an event cannot be empty.");
        }

        final LocalDateTime eventDateTime = LocalDateTime.parse(eventArgs[1], dateTimeFormatter);

        return new Event(eventDescription, eventDateTime);
    }
}