package duke.command;

import duke.*;

public abstract class IndexBasedCommand extends Command {
    //protected String command; //unnecessary, commented out for potential future use
    protected int index;

    public IndexBasedCommand(String line) throws IndexOutOfBoundsException, NumberFormatException, DukeException {
        String[] data = line.split(" ");
        if (data.length != 2) {
            throw new DukeException("Invalid number of arguments in an index based command");
        }
        //command = data[0];
        index = Integer.parseInt(data[1].trim()) - 1;
    }
}
