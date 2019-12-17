package com.duke.command;

import com.duke.TaskList;
import com.duke.task.Deadline;

/**
 * Encapsulates a deadline command to create a deadline task.
 */
public class DeadlineCommand extends AddCommand {

    /**
     * Constructs a DeadlineCommand object.
     *
     * @param list  Input task list.
     * @param input Task description and date specified by user input.
     */
    public DeadlineCommand(TaskList list, String input) {
        super(list);
        super.task = Deadline.genDeadline(input);
    }
}
