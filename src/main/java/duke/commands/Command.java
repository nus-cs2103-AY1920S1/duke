package duke.commands;

import duke.exception.DukeException;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

public abstract class Command {

    String input;

    Command(String input) {
        this.input = input;
    }

    Command() {

    }

    public abstract String execute(TaskList tasks, Ui ui, Storage store) throws DukeException;

}
