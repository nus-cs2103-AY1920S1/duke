package duke.commands;

import duke.data.tasks.Event;

/**
 * Implements the event command.
 * @author Lim Yong Shen, Kevin
 */
public class EventCommand extends AddCommand {

    private Event event;
    public static final String COMMAND_WORD = "event";

    /**
     * Constructs an EventCommand with the specified event task.
     * @param event The specified event task.
     */
    public EventCommand(Event event) {
        this.event = event;
    }

    /**
     * Executes this EventCommand and returns its CommandResult.
     * @return This EventCommand's CommandResult.
     */
    @Override
    public CommandResult execute() {
        tasks.add(event);
        return new CommandResult(String.format(SUCCESS_MESSAGE, event.toString(), tasks.size()));
    }

}
