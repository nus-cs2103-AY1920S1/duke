public class InvalidCommand extends Command{

    public InvalidCommand() {
        super(false);
    }

    @Override
    public void execute(Storage storage, TaskList tasks, UI ui) {
        ui.echoException(new DukeException());
    }

}
