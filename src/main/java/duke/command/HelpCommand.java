package duke.command;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UiText;

public class HelpCommand extends Command {
    public HelpCommand(String[] msg) {
        super(msg);
    }

    /**
     * Check if the argument is correct. If is correct then execute throws a DukeException.
     */
    @Override
    public String execute(TaskList list, UiText ui, Storage storage) throws DukeException {
        if (super.command.length > 2) {
            throw new DukeException("OOPS!! I\'m sorry, but I don\'t know what that means :-(");
        }

        //only help keyword. display a content of commands
        if (super.command.length == 1) {
            return UiText.helpFullContent();
        } else {
            assert super.command.length == 2;
            String[] keyword = super.command[1].trim().split(" ");
            if (keyword.length > 1) {
                throw new DukeException("OOPS!! I\'m sorry, but "
                        + super.command[1] + " is not a valid keyword. You may key in help for list of commands");
            }
            assert keyword.length == 1;
            return UiText.helpForKeyword(keyword[0].trim());
        }
    }
}
