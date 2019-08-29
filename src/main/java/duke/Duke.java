package duke;

import java.io.IOException;

public class Duke {
    private PreParser preParser;
    private TaskList taskList;
    private Storage storage;
    private Ui ui;


    public Duke(String filePath) {
        preParser = new PreParser();
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = storage.load();
        } catch(IOException | ClassNotFoundException e) {
            System.err.println(e);
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = preParser.parse(fullCommand);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch(DukeException e) {
                ui.oops(e.getMessage());
            }
        }

        try {
            storage.write(taskList);
        } catch(IOException e) {
            System.err.println(e);
            ui.oops("Couldn't save tasks to disk.");
        }
    }
}
