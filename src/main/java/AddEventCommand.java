import java.util.Arrays;
import java.util.List;

public class AddEventCommand extends Command{

    String [] tokens;

    public AddEventCommand(String [] tokens) {
        this.tokens = tokens;
        this.commandType = CommandType.ADDEVENT;
    }

    public static AddEventCommand addEventIfValid(String [] tokens) {
        if (!Arrays.asList(tokens).contains("/at")) {
            throw new IllegalArgumentException("Missing deadline");
        } else {
            return new AddEventCommand(tokens);
        }
    }

    public void execute(List<Task> lst, Ui ui) throws DukeException {
        Event task = Event.createEvent(tokens);
        lst.add(task);
        ui.printInput(task, lst);
    }

}
