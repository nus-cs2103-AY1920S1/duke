package duke.command;

import duke.task.Task;

import java.util.ArrayList;

public class CommandResult {
    private CommandType commandType;
    private Task[] tasks;
    private int taskListSize;

    public CommandResult(CommandType commandType) {
        this.commandType = commandType;
    }

    public CommandResult(CommandType commandType, Task ... tasks) {
        this.commandType = commandType;
        this.tasks = tasks;
    }

    public CommandResult(CommandType commandType, int taskListSize, Task ... tasks) {
        this(commandType, tasks);
        this.taskListSize = taskListSize;
    }

    public CommandResult(CommandType commandType, ArrayList<Task> tasks) {
        this(commandType, tasks.toArray(new Task[tasks.size()]));
    }

    public CommandResult(CommandType commandType, int taskListSize, ArrayList<Task> tasks) {
        this(commandType, taskListSize, tasks.toArray(new Task[tasks.size()]));
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public Task[] getTasks() {
        return tasks;
    }

    public int getTaskListSize() {
        return taskListSize;
    }
}
