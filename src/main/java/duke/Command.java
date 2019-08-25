package duke;

abstract public class Command {
    Commands commandType;

    abstract public void execute(TaskList tasks, Ui ui, Storage storage);
}