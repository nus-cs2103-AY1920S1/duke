package duke.command;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UiText;

public abstract class Command {
    protected String[] command;
    protected boolean isExit = false;

    protected Command() {
    }

    protected Command(String[] msg) {
        this.command = msg;

    }

    public boolean isExit() {
        return isExit;
    }

    public String execute(TaskList list, UiText ui, Storage storage) throws DukeException {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (command == null) {
            return "Invalid";
        }
        for (String s : command) {
            sb.append(s);
            sb.append(" ");
        }
        return  sb.toString();
    }

}
