package duke.main;

import java.text.ParseException;
import duke.command.*;
import duke.io.*;
import duke.task.*;

/**
 *  CS2103 iP Deliverable, Duke
 *  @author Ahmed Bahajjaj
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = storage.loadTasks(ui);
    }

    void run() {
        boolean isExit = false;
        ui.out("What can I do for you?");
        ui.showLine();
        while(!isExit) {
            try{
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
}
