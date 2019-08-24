package com.leeyiyuan.storage.format;


import com.leeyiyuan.task.Task;

public abstract class TaskFormatter {

    public abstract Task parse(String text) throws TaskParseException;

    public abstract String format(Task task) throws TaskFormatException;
}
