package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

public class LoadCommand extends Command {
    private String filePath;

    public LoadCommand(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String execute() throws DukeException {
        String previousPath = this.storage.getSavePath();
        // intuitively, users would want to save to same location that
        // the new list was loaded from instead of overwriting the current list.
        this.storage.changeSavePath(this.filePath);
        TaskList loadedList = this.storage.loadFromDisk();
        this.taskList.replaceWith(loadedList);
        return "Got it. I have changed the loading filepath from\n'"
                + previousPath + "'\n"
                + "to\n'"
                + this.filePath + "'\n\n"
                + (new ListCommand()).initialize(storage, taskList, ui).execute()
                + "\nNOTE: I will also update the save path to the same location\n"
                // remind users that save location was also changed
                + "If you want to save to a different location, use the 'save' command!";
    }
}
