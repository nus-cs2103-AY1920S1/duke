import java.text.ParseException;

public class ByeCommand extends Command {

    public ByeCommand() {
        super.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ParseException {
        super.execute(tasks, ui, storage);
        ui.bye();
    }
}