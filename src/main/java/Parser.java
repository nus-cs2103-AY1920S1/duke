import java.util.ArrayList;
import java.util.Arrays;

public class Parser {

    /**
     * The method takes in user input constantly and updated the tasklist based on the input commands.
     * @param input the next user input which decides what the code does.
     * @param taskList the list of tasks currently saved.
     */
    public static void Parse(String input, TaskList taskList) {
        Ui ui = new Ui();
        Storage storage = new Storage();
        Task[] tasks = taskList.getTasks();

        int NUM_OF_TASKS;
        NUM_OF_TASKS = Task.initialiseNumOfTasks(tasks);

        while (!input.equals("bye")) {
            try {
                if (input.equals("todo") || input.equals("event") || input.equals("deadline")) {
                    throw new EmptyTaskException("☹ OOPS!!! The description of a " + input + " cannot be empty.");
                } else {
                    switch (input.split(" ")[0]) {
                        case "list":
                            ui.printList(taskList);
                            input = ui.readCommand();
                            break;
                        case "done":
                            int index = Integer.parseInt(input.substring(5));
                            taskList.get(index - 1).markAsDone();
                            ui.printDone(taskList.get(index - 1));

                            storage.save(taskList);
                            input = ui.readCommand();
                            break;
                        case "todo":
                            String tododescription = input.substring(5);
                            Task todotask = new ToDo(tododescription);
                            TaskList.add(todotask, NUM_OF_TASKS);
                            NUM_OF_TASKS++;
                            ui.echo(taskList.get(NUM_OF_TASKS - 1), NUM_OF_TASKS);

                            storage.save(taskList);
                            input = ui.readCommand();
                            break;
                        case "event":
                            String eventdescription = input.split(" /at ")[0].substring(6);
                            String at = input.split(" /at ")[1];
                            if (at.contains("/") && at.contains(" ")) {
                                at = DateReader.readDate(new DateReader(at));
                            } else {}
                            Event eventtask = new Event(eventdescription, at);
                            TaskList.add(eventtask, NUM_OF_TASKS);
                            NUM_OF_TASKS++;
                            ui.echo(taskList.get(NUM_OF_TASKS - 1), NUM_OF_TASKS);

                            storage.save(taskList);
                            input = ui.readCommand();
                            break;
                        case "deadline":
                            String deadlinedescription = input.split(" /by ")[0].substring(9);
                            String by = input.split(" /by ")[1];
                            if (by.contains("/") && by.contains(" ")) {
                                by = DateReader.readDate(new DateReader(by));
                            } else {}
                            Deadline deadlinetask = new Deadline(deadlinedescription, by);
                            TaskList.add(deadlinetask, NUM_OF_TASKS);
                            NUM_OF_TASKS++;
                            ui.echo(taskList.get(NUM_OF_TASKS - 1), NUM_OF_TASKS);

                            storage.save(taskList);
                            input = ui.readCommand();
                            break;
                        case "delete":
                            if (taskList.get(0) == null) {
                                throw new TaskDoesNotExistException("That task does not exist!");
                            } else if (taskList.get(Integer.parseInt(input.substring(7)) - 1) == null) {
                                throw new TaskDoesNotExistException("That task does not exist!");
                            } else {
                                int indextodel = Integer.parseInt(input.substring(7));
                                Task removed = taskList.delete(indextodel);
                                ui.printDeleted(removed, --NUM_OF_TASKS);

                                storage.save(taskList);
                                input = ui.readCommand();
                            }
                            break;
                        case "find":
                            String keyword = input.substring(5);
                            ui.printFound(keyword, taskList);

                            input = ui.readCommand();
                            break;
                        default:
                            throw new UnknownInputException("☹ OOPS!!! I'm sorry, " +
                                    "but I don't know what that means :-(");
                    }
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
                input = ui.readCommand();
            }
        }
    }
}
