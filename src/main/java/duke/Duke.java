package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.io.Parser;
import duke.io.Storage;
import duke.io.Ui;
import duke.task.TaskList;
import java.text.ParseException;

/**
 *  CS2103 iP Deliverable, Duke.
 *  @author Ahmed Bahajjaj
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor creates ui, storage, and taskList.
     * @param filePath Path for duke data file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = storage.loadTasks(ui);
        ui.out("What can I do for you?");
        ui.showLine();
    }

    public String getOutput() {
        return ui.getDukeOut().toString();
    }

    /**
     * Runs the duke To-do Application.
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.read();
                Command command = Parser.parse(input);
                command.execute(taskList, ui, storage);
                isExit = (command instanceof ExitCommand);
            } catch (ArrayIndexOutOfBoundsException ex) {
                ui.out("The details of an Event/Deadline cannot be empty.");
            } catch (UnsupportedOperationException ex) {
                ui.out("I'm sorry, but I don't know what that means.");
            } catch (NumberFormatException ex) {
                ui.out("Please only complete/delete tasks on the list.");
            } catch (ParseException ex) {
                ui.out("Please format date/time as 'dd-MM-yyyy HHmm'.");
            }
        }
        ui.close();
        storage.writeTasks(taskList);
    }

    public String getResponse(String input) {
        ui.clear();
        try {
            Command command = Parser.parse(input);
            command.execute(taskList, ui, storage);
        } catch (ArrayIndexOutOfBoundsException ex) {
            ui.out("The details of an Event/Deadline cannot be empty.");
        } catch (UnsupportedOperationException ex) {
            ui.out("I'm sorry, but I don't know what that means.");
        } catch (NumberFormatException ex) {
            ui.out("Please only complete/delete tasks on the list.");
        } catch (ParseException ex) {
            ui.out("Please format date/time as 'dd-MM-yyyy HHmm'.");
        }
        return ui.getDukeOut().toString();
    }

    public void close() {
        ui.close();
        storage.writeTasks(taskList);
    }
}
