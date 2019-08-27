import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private static List<Task> tasks = new ArrayList<>();
    private Ui ui;

    /**
     * Entry Point of entire Program.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        try {
            tasks = Storage.load();
        } catch (FileNotFoundException ignore) {
        } catch (DukeException | IOException e) {
            System.out.println(e.getMessage());
        }

        Ui ui = new Ui();
        ui.showWelcome();

        String input;
        while (!(input = ui.readCommand()).equals("bye")) {
            String output;
            try {
                handleInput(input, ui);
            } catch (DukeException e) {
                output = e.getMessage();
            }
            try {
                Storage.save(tasks);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        ui.showBye();
    }

    /**
     * Is called for each line of input.
     * @param input Input string.
     * @throws DukeException When input argument wrong format.
     */
    private static void handleInput(String input, Ui ui) throws DukeException {
        String output;
        String[] splitInput = input.split(" ");
        String command = splitInput[0];
        switch (command) {
        case "list":
            ui.showTasks(tasks);
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
                taskToMarkAsDone = tasks.get(selectedIndex);
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
                taskToDelete = tasks.get(deleteIndex);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Selected task number does not exist.");
            }
            tasks.remove(deleteIndex);
            ui.showDeleteTask(taskToDelete, tasks.size());
            break;
        case "deadline":
            int byIndex = input.indexOf(" /by ");
            if (byIndex < 0) {
                throw new DukeException("Command deadline requires an argument /by, followed by deadline date");
            }
            String deadlineDescription =  input.substring(9, byIndex);
            String by = input.substring(byIndex + 5);
            Deadline deadline = new Deadline(deadlineDescription, by);
            tasks.add(deadline);
            ui.showAddTask(deadline, tasks.size());
            break;
        case "event":
            int atIndex = input.indexOf(" /at ");
            if (atIndex < 0) {
                throw new DukeException("Command event requires an argument /at, followed by event date");
            }
            String eventDescription =  input.substring(6, atIndex);
            String at = input.substring(atIndex + 5);
            Event event = new Event(eventDescription, at);
            tasks.add(event);
            ui.showAddTask(event, tasks.size());
            break;
        case "todo":
            String todoDescription;
            try {
                todoDescription = input.substring(5);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
            }
            Todo todo = new Todo(todoDescription);
            tasks.add(todo);
            ui.showAddTask(todo, tasks.size());
            break;
        default:
            ui.showGenericError();
        }
    }
}
