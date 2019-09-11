import java.util.Arrays;

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

    public void execute(TaskList taskList, Ui ui) throws DukeException {
        Event task = Event.createEvent(tokens);
        taskList.addToList(task);
        ui.printInput(task, taskList);
    }



}
