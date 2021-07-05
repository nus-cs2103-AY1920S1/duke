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
     * Constructs an event command with the specified event task.
     * @param event The specified event task.
     */
    public EventCommand(Event event) {
        this.event = event;
    }

    /**
     * Executes this event command and returns its command result.
     * @return This event command's command result.
     */
    @Override
    public CommandResult execute() {
        tasks.add(event);
        return new CommandResult(String.format(SUCCESS_MESSAGE, event.toString(), tasks.size()));
    }

}
