package com.leeyiyuan.storage.format;


import com.leeyiyuan.task.Task;

/**
 * Represents a formatter to format a task.
 */
public abstract class TaskFormatter {

    /**
     * Returns a Task parsed from a string of text.
     *
     * @param text Text to parse Task from.
     * @return Task parsed from text.
     * @throws TaskParseException If Task cannot be parsed from text.
     */
    public abstract Task parse(String text) throws TaskParseException;

    /**
     * Returns a string of text formatted from a Task.
     *
     * @param task Task to use to format text.
     * @return Text formatted from task.
     * @throws TaskFormatException If Task cannot be formatted into text.
     */
    public abstract String format(Task task) throws TaskFormatException;
}
