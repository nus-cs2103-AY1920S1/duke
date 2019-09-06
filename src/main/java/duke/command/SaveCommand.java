package duke.command;

import duke.exception.DukeException;

public class SaveCommand extends Command {
    private String filePath;

    public SaveCommand(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String execute() throws DukeException {
        String previousPath = this.storage.changeSavePath(filePath);
        this.storage.saveToDisk(this.taskList);

        return "Got it. I've saved and changed the current save file path from\n'"
                + previousPath
                + "'\nto\n'"
                + this.filePath + "'";
    }
}
