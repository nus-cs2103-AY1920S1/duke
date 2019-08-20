package duke;

abstract public class Command {
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
