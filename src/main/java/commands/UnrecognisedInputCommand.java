package commands;

import components.Storage;
import components.TaskList;

public class UnrecognisedInputCommand implements Command{
    @Override
    public String[] execute(Storage storage, TaskList taskList) {
        return new String[] {"Oops! I'm sorry, but I don't know what that means :-("};
    }

}
