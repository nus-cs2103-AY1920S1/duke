package duke.command;

import duke.exception.InvalidParameterException;
import duke.task.Event;

public class AddEventCommand extends AddCommand {

    public AddEventCommand(String line) {
        super(line);
        try {
            String[] arr = super.line.split(" /at ");
            super.task = new Event(arr[0], arr[1]);
        } catch(ArrayIndexOutOfBoundsException aioobe) {
            throw new InvalidParameterException(line.isBlank() ? null : line);
        }
    }

    public boolean isExit() {
        return false;
    }

}
