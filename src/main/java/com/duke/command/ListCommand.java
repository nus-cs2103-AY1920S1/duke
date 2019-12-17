package com.duke.command;

import com.duke.TaskList;

/**
 * Encapsulates a list command to list out all the tasks.
 */
public class ListCommand extends Command {
    private static final String LIST_MESSAGE = "Here are the tasks in your list:";
    private static final String EMPTY_LIST_MESSAGE = "The task list is empty. Try adding some tasks.";

    /**
     * Constructs a ListCommand object.
     *
     * @param list Input task list.
     */
    public ListCommand(TaskList list) {
        super(list);
    }

    /**
     * Executes the command.
     *
     * @return Output shown to user.
     */
    public String execute() {
        return list.isEmpty() ? EMPTY_LIST_MESSAGE + "\n" : LIST_MESSAGE + "\n" + list.toString();
    }
}
