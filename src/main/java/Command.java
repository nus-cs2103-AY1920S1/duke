import java.io.IOException;

abstract class Command {
    String commandDesc;

    Command(String commandDesc){
        this.commandDesc = commandDesc;
    }

    abstract boolean isExit();

    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;
}
