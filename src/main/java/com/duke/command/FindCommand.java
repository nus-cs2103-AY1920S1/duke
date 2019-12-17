package com.duke.command;

import com.duke.TaskList;

/**
 * Encapsulates a find command to search for a keyword.
 */
public class FindCommand extends Command {
    private static final String FIND_MESSAGE = "Here are the matching tasks in your list:";
    private static final String FAIL_MESSAGE = "Sorry. I cannot find any matching results in the task list";

    private String text;

    /**
     * Constructs a FindCommand object.
     *
     * @param list Input task list.
     * @param text Text to find specified by user input.
     */
    public FindCommand(TaskList list, String text) {
        super(list);
        this.text = text;
    }

    /**
     * Executes the command.
     *
     * @return Output shown to user.
     */
    public String execute() {
        TaskList taskList = this.list.query(this.text);
        return taskList.isEmpty() ? FAIL_MESSAGE : FIND_MESSAGE + "\n" + taskList.toString();
    }
}
