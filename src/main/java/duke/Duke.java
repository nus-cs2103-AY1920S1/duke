package duke;

import duke.command.Command;

public class Duke {
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        tasks = new TaskList(Storage.load());
    }

    public String getStartingMessage() {
        return ui.getStartingMessage(tasks);
    }

    public String getResponse(String input) {
        Command userCommand = Parser.parse(input);
        String response;
        try {
            response = userCommand.execute(tasks);
        } catch (DukeException e) {
            response = ui.getErrorMessage(e);
        }
        return response;
    }
}
