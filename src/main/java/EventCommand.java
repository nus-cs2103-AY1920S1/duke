import java.io.IOException;

/**
 * Execute Event command in Duke.
 */

public class EventCommand extends Command {
    private String command;

    /**
     *  Event Command constructor using string user input.
     * @param command user String input
     */
    public EventCommand(String command) {
        super(command);
        this.command = command;
    }

    /**
     * Execute the Event command. Add an event task. Format: event (desc) /at (place)
     * @param task Tasklist data
     * @param ui Ui interfaces and strings
     * @param storage stored file
     * @return a string showing an event task is added
     * @throws DukeException if any of raw values are invalid
     */
    @Override
    public String execute(TaskList task, Ui ui, Storage storage) throws DukeException, AssertionError {

        String[] eventtask = command.split("event ");
        assert eventtask.length > 0 : " Must input event task";
        if (eventtask.length == 0) {
            throw new DukeException(" ☹ OOPS!!! The description of an Event cannot be empty.");
        }
        String eventString = eventtask[1];
        String[] eventarr = eventString.split(" /at ");
        if (eventarr.length < 2) {
            throw new DukeException(" ☹ OOPS!!! Wrong format. Must be event desc /at place");
        }
        String output = "";
        output += ("     Got it. I've added this task:\n");
        Event event = new Event(eventarr[0], eventarr[1]);
        task.addTask(event);
        int numberOfTask = task.getCount();
        output += ("       " + event.toString() + "\n");
        output += ("     Now you have " + numberOfTask + " tasks in the list.\n");
        int checkdone = event.isDone ? 1 : 0;
        try {
            String text = checkdone + "/event/" + event.description + "/" + event.getAt() + System.lineSeparator();
            storage.appendToFile(text);
        } catch (IOException e) {
            return "WRITING ERROR";
        }
        return output + "\n" + ui.showLine();
    }
}