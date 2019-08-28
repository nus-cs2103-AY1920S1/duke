public class EventCommand extends NewTaskCommand {

    public EventCommand(String description, DateTime at) {
        super(new Event(description, at));
    }
}
