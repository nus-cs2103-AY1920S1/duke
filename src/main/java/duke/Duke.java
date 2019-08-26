package duke;

import duke.command.Command;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        } catch (DukeException e) {
            ui.printError(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public static Date parseDate(String rawDate) throws DukeException {
        try {
            Date newDate = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(rawDate);
            return newDate;
        } catch (ParseException e) {
            throw new DukeException("Cannot parse date/time.");
        }
    }

    public static String formatDate(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy HHmm").format(date);
    }
}
