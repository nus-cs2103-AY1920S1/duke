public class EventCommand extends AddCommand {

    public EventCommand(String description, String at) throws DukeException {
        super(new Event(description, at));
    }

}