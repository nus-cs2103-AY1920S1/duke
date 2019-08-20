package duke;

import duke.commands.Parser;

abstract public class Command {
    protected Parser parser = new Parser();
    protected String name;
    protected Duke duke;
    public Command(Duke duke) {
        this.duke = duke;
    }
    public String getName() {
        return name;
    };
    abstract public void execute(String[] args) throws DukeException;
}
