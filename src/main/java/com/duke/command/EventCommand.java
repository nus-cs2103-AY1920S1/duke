package com.duke.command;

import com.duke.TaskList;
import com.duke.task.Event;

/**
 * Encapsulates an event command to create an event task.
 */
public class EventCommand extends AddCommand {

    /**
     * Constructs an EventCommand object.
     *
     * @param list  Input task list.
     * @param input Command from user input.
     */
    public EventCommand(TaskList list, String input) {
        super(list);
        super.task = Event.genEvent(input);
    }
}
