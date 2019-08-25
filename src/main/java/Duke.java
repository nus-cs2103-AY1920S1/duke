import java.util.ArrayList;
import java.util.List;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private UserInterface ui;
    private static final String DEFAULT_STORAGE_FILEPATH = "data/tasks.txt";

    public Duke(String filePath) {
        ui = new UserInterface();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showExceptionMessage(e.getMessage());
            taskList = new TaskList();
            ui.exitProgram();
        }
    }

    private void run() {
        boolean isTerminated = false;
        Task task;
        ui.showWelcomeMessage();
        while (!isTerminated) {
            try {
                String inputLine = ui.readLine();
                ui.echo(inputLine);
                String command = getCommandFrom(inputLine);
                ui.showLine();
                switch (command) {
                    case "bye":
                        isTerminated = true;
                        ui.exitProgram();
                        break;
                    case "list":
                        ui.showTaskList(taskList.getTaskNames());
                        break;
                    case "done":
                        task = taskList.markDone(getIndexFrom(inputLine));
                        storage.save(taskList.getSimplifiedTaskRepresentations());
                        ui.showMarkDone(task);
                        break;
                    case "todo":
                        task = createTodoFrom(inputLine);
                        taskList.addTask(task);
                        storage.save(task.getSimplifiedRepresentation());
                        ui.showAddition(task);
                        ui.showTaskSize(taskList);
                        break;
                    case "deadline":
                        task = createDeadlineFrom(inputLine);
                        taskList.addTask(task);
                        storage.save(task.getSimplifiedRepresentation());
                        ui.showAddition(task);
                        ui.showTaskSize(taskList);
                        break;
                    case "event":
                        task = createEventFrom(inputLine);
                        taskList.addTask(task);
                        storage.save(task.getSimplifiedRepresentation());
                        ui.showAddition(task);
                        ui.showTaskSize(taskList);
                        break;
                    case "delete":
                        task = taskList.delete(getIndexFrom(inputLine));
                        storage.save(taskList.getSimplifiedTaskRepresentations());
                        ui.showDeletion(task);
                        ui.showTaskSize(taskList);
                        break;
                    default:
                        throw new DukeException(ui.MESSAGE_INVALID_COMMAND_FORMAT);
                }
            } catch (DukeException e) {
                ui.showExceptionMessage(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    private String getCommandFrom(String inputLine) {
        return inputLine.strip().split(" ")[0];
    }

    private int getIndexFrom(String inputLine) throws DukeException {
        try {
            int index = Integer.parseInt(inputLine.strip().split(" ")[1]);
            if (index <= 0 || index > taskList.size()) {
                throw new DukeException(ui.MESSAGE_INVALID_TASK_INDEX);
            }
            return index;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ui.MESSAGE_INVALID_TASK_INDEX);
        }
    }

    private Todo createTodoFrom(String inputLine) throws DukeException {
        String todoDescription = inputLine.substring("todo".length()).strip();
        if (todoDescription.isEmpty()) {
            throw new DukeException(ui.MESSAGE_INVALID_TODO_FORMAT);
        }
        return new Todo(todoDescription);
    }

    private Deadline createDeadlineFrom(String inputLine) throws DukeException {
        try {
            String[] deadlinePart = inputLine.substring("deadline".length()).split("/by");
            String deadlineDescription = deadlinePart[0].strip();
            if (deadlineDescription.isEmpty()) {
                throw new DukeException(ui.MESSAGE_INVALID_DEADLINE_FORMAT);
            }
            return new Deadline(deadlineDescription, deadlinePart[1].strip());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ui.MESSAGE_INVALID_DEADLINE_FORMAT);
        }
    }

    private Event createEventFrom(String inputLine) throws DukeException {
        try {
            String[] eventPart = inputLine.substring("event".length()).split("/at");
            String eventDescription = eventPart[0].strip();
            if (eventDescription.isEmpty()) {
                throw new DukeException(ui.MESSAGE_INVALID_EVENT_FORMAT);
            }
            return new Event(eventDescription, eventPart[1].strip());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ui.MESSAGE_INVALID_EVENT_FORMAT);
        }
    }

    public static void main (String[] args){
        new Duke(DEFAULT_STORAGE_FILEPATH).run();
    }
}
