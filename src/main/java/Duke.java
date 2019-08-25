package com.leeyiyuan;


import com.leeyiyuan.command.AbortException;
import com.leeyiyuan.command.Command;
import com.leeyiyuan.command.CommandExecuteException;
import com.leeyiyuan.command.format.CommandParseException;
import com.leeyiyuan.command.format.Parser;
import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.storage.StorageException;
import com.leeyiyuan.task.TaskList;
import com.leeyiyuan.ui.Ui;
import com.leeyiyuan.ui.UiException;

/**
 * Main class of the Duke program.
 */
public class Duke {

    /**
     * Main entry point of the Duke program.
     */
    public static void main(String[] args) {
        Storage storage = new Storage("/home/leeyiyuan/Projects/duke/data/duke.txt");
        Parser parser = new Parser();
        Ui ui = new Ui();

        TaskList tasks = null;
        try {
            tasks = new TaskList(storage.load());
        } catch (StorageException e) {
            ui.showError(e.toString());
            ui.showLine("Using new tasks list instead.");
        }

        try {
            ui.showWelcome();
            while (true) {
                try {
                    String commandText = ui.readCommand();
                    Command command = parser.parse(commandText);
                    command.execute(tasks, ui, storage);
                } catch (CommandParseException e) {
                    ui.showError(e.toString());
                } catch (AbortException e) {
                    throw e;
                } catch (CommandExecuteException e) {
                    ui.showError(e.toString());
                } catch (StorageException e) {
                    ui.showError(e.toString());
                }
            }
        } catch (UiException e) {

        } catch (AbortException e) {

        }
    }
}
