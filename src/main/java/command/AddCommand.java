package command;

import main.*;

import java.io.IOException;

/**
 * Represents the command to add a task.
 */
public class AddCommand extends Command {
    String input;
    String[] temp;

    /**
     * Creates an AddCommand object.
     *
     * @param input User input taken from the scanner.
     * @param temp  Parsed String array derived from input.
     */
    public AddCommand(String input, String[] temp) {
        this.input = input;
        this.temp = temp;
    }

    /**
     * Adds the given command by creating the associated object and
     * adding it to the array within task, writing the updated list
     * to storage and printing the current list size. If no task is added,
     * input is considered invalid and an error is displayed.
     *
     * @param task    The working TaskList object.
     * @param ui      The working Ui object.
     * @param storage The working storage object.
     * @throws IOException If file is not found.
     */
    public String execute(TaskList task, Ui ui, Storage storage) throws IOException {

        DateTime date;
        String message;

        try {
            switch (temp[0]) {
                case "deadline":
                    task.addDeadline(input, false);
                    storage.arrayToFile(task.getList());
                    return ui.printAdd(task.getList());

                case "event":
                    task.addEvent(input, false);
                    storage.arrayToFile(task.getList());
                    return ui.printAdd(task.getList());

                case "todo":
                    if (temp.length < 2) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    task.addToDo(input, false);
                    storage.arrayToFile(task.getList());
                    return ui.printAdd(task.getList());

                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

        } catch (DukeException e) {
            return ui.printError(e.getMessage());
        } catch (StringIndexOutOfBoundsException e) {
            return ui.printError("Please input a task and date.");
        } catch (IndexOutOfBoundsException e) {
            return ui.printError("Please input a valid date and time.");
        } catch (NumberFormatException e) {
            return ui.printError("Please input a valid date and time.");
        }

    }
}
