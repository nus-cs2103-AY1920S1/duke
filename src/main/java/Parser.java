import java.util.ArrayList;
import java.util.Arrays;

public class Parser {

    /**
     * The method takes in user input constantly and updated the tasklist based on the input commands.
     * @param input the next user input which decides what the code does.
     * @param tasklist the list of tasks currently saved.
     */
    public static void Parse(String input, TaskList tasklist) {
        Ui ui = new Ui();
        Storage storage = new Storage();
        Task[] tasks = tasklist.getTasks();

        int numoftasks;
        numoftasks = Task.initialiseNumOfTasks(tasks);

        while (!input.equals("bye")) {
            try {
                if (input.equals("todo") || input.equals("event") || input.equals("deadline")) {
                    throw new EmptyTaskException("☹ OOPS!!! The description of a " + input + " cannot be empty.");
                } else {
                    switch (input.split(" ")[0]) {
                        case "list":
                            ui.printList(tasklist);
                            input = ui.readCommand();
                            break;
                        case "done":
                            int index = Integer.parseInt(input.substring(5));
                            tasklist.get(index - 1).markAsDone();
                            ui.printDone(tasklist.get(index - 1));
                            storage.save(tasklist);
                            input = ui.readCommand();
                            break;
                        case "todo":
                            String tododescription = input.substring(5);
                            Task todotask = new ToDo(tododescription);
                            TaskList.add(todotask, numoftasks);

                            numoftasks++;
                            ui.echo(tasklist.get(numoftasks - 1), numoftasks);
                            storage.save(tasklist);
                            input = ui.readCommand();
                            break;
                        case "event":
                            String eventdescription = input.split(" /at ")[0].substring(6);
                            String at = input.split(" /at ")[1];
                            if (at.contains("/") && at.contains(" ")) {
                                at = DateReader.readDate(new DateReader(at));
                            } else {}

                            Event eventtask = new Event(eventdescription, at);
                            TaskList.add(eventtask, numoftasks);

                            numoftasks++;
                            ui.echo(tasklist.get(numoftasks - 1), numoftasks);
                            storage.save(tasklist);
                            input = ui.readCommand();
                            break;
                        case "deadline":
                            String deadlinedescription = input.split(" /by ")[0].substring(9);
                            String by = input.split(" /by ")[1];
                            if (by.contains("/") && by.contains(" ")) {
                                by = DateReader.readDate(new DateReader(by));
                            } else {}

                            Deadline deadlinetask = new Deadline(deadlinedescription, by);
                            TaskList.add(deadlinetask, numoftasks);

                            numoftasks++;
                            ui.echo(tasklist.get(numoftasks - 1), numoftasks);
                            storage.save(tasklist);
                            input = ui.readCommand();
                            break;
                        case "delete":
                            if (tasklist.get(0) == null) {
                                throw new TaskDoesNotExistException("That task does not exist!");
                            } else if (tasklist.get(Integer.parseInt(input.substring(7)) - 1) == null) {
                                throw new TaskDoesNotExistException("That task does not exist!");
                            } else {
                                int indextodel = Integer.parseInt(input.substring(7));
                                Task removed = tasklist.delete(indextodel);
                                ui.printDeleted(removed, --numoftasks);
                                storage.save(tasklist);
                                input = ui.readCommand();
                            }
                            break;
                        default:
                            throw new UnknownInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
                input = ui.readCommand();
            }
        }
    }
}
