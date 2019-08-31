package command;

import command.*;
import main.*;

import java.io.IOException;

public class AddCommand extends Command {
    String input;
    String[] temp;

    public AddCommand(String input, String[] temp) {
        this.input = input;
        this.temp = temp;
    }

    public void execute(TaskList task, Ui ui, Storage storage) throws IOException {

        DateTime date;
        String message;
        boolean added = false;

        try {
            switch (temp[0]) {
            case "deadline":
                task.addDeadline(input, false);
                added = true;
                storage.arrayToFile(task.getList());
                break;
            case "event":
                task.addEvent(input, false);
                added = true;
                storage.arrayToFile(task.getList());
                break;
            case "todo":
                if (temp.length < 2) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                task.addToDo(input, false);
                added = true;
                storage.arrayToFile(task.getList());
                break;
            }

            if (added) {
                ui.printAdd(task.getList());
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

        } catch (DukeException e) {
            ui.printError(e.getMessage());
        } catch (StringIndexOutOfBoundsException e) {
            ui.printError("Please input a task and date.");
        } catch (IndexOutOfBoundsException e) {
            ui.printError("Please input a valid date and time.");
        } catch (NumberFormatException e) {
            ui.printError("Please input a valid date and time.");
        }

    }
}
