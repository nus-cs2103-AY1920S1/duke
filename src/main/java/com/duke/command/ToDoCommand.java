package com.duke.command;

import com.duke.TaskList;
import com.duke.task.ToDo;

/**
 * Encapsulates a ToDo command to create a ToDo task.
 */
public class ToDoCommand extends AddCommand {

    /**
     * Constructs a ToDoCommand object.
     *
     * @param list Task list.
     * @param des  Task description.
     */
    public ToDoCommand(TaskList list, String des) {
        super(list);
        super.task = new ToDo(des);
    }
}
