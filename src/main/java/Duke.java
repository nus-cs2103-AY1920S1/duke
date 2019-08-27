import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private static TaskList taskList = new TaskList();
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        try {
            taskList = Storage.load();
        } catch (FileNotFoundException ignore) {
        } catch (DukeException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        Ui ui = new Ui();
        ui.showWelcome();

        String input;
        while (!(input = ui.readCommand()).equals("bye")) {
            try {
                handleInput(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            try {
                Storage.save(taskList);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        ui.showBye();
    }

    public static void main(String[] args) {
        new Duke("/home/yuan/cs2103t/duke/data").run();
    }

    /**
     * Is called for each line of input.
     * @param input Input string.
     * @throws DukeException When input argument wrong format.
     */
    private void handleInput(String input) throws DukeException {
        String[] splitInput = input.split(" ");
        String command = splitInput[0];
        switch (command) {
        case "list":
            ui.showTasks(taskList);
            break;
        case "done":
            Task taskToMarkAsDone;
            int selectedIndex;
            try {
                selectedIndex = Integer.parseInt(splitInput[1]) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("Argument passed to done must be a valid integer");
            }
            try {
                taskToMarkAsDone = taskList.getIndex(selectedIndex);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Selected task number does not exist.");
            }
            taskToMarkAsDone.markAsDone();
            ui.showMarkAsDone(taskToMarkAsDone);
            break;
        case "delete":
            Task taskToDelete;
            int deleteIndex;
            try {
                deleteIndex = Integer.parseInt(splitInput[1]) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("Argument passed to delete must be a valid integer");
            }
            try {
                taskToDelete = taskList.getIndex(deleteIndex);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Selected task number does not exist.");
            }
            taskList.deleteTaskAt(deleteIndex);
            ui.showDeleteTask(taskToDelete, taskList.numberOfTasks());
            break;
        case "deadline":
            int byIndex = input.indexOf(" /by ");
            if (byIndex < 0) {
                throw new DukeException("Command deadline requires an argument /by, followed by deadline date");
            }
            String deadlineDescription =  input.substring(9, byIndex);
            String by = input.substring(byIndex + 5);
            Deadline deadline = new Deadline(deadlineDescription, by);
            taskList.addTask(deadline);
            ui.showAddTask(deadline, taskList.numberOfTasks());
            break;
        case "event":
            int atIndex = input.indexOf(" /at ");
            if (atIndex < 0) {
                throw new DukeException("Command event requires an argument /at, followed by event date");
            }
            String eventDescription =  input.substring(6, atIndex);
            String at = input.substring(atIndex + 5);
            Event event = new Event(eventDescription, at);
            taskList.addTask(event);
            ui.showAddTask(event, taskList.numberOfTasks());
            break;
        case "todo":
            String todoDescription;
            try {
                todoDescription = input.substring(5);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
            }
            Todo todo = new Todo(todoDescription);
            taskList.addTask(todo);
            ui.showAddTask(todo, taskList.numberOfTasks());
            break;
        default:
            ui.showGenericError();
        }
    }
}
