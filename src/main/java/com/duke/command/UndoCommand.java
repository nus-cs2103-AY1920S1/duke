package com.duke.command;

import com.duke.RecycleBin;
import com.duke.task.Task;
import com.duke.TaskList;

import java.io.IOException;

/**
 * Encapsulates an undo command to undo previous command.
 */
public class UndoCommand extends Command {
    private RecycleBin recycleBin;

    /**
     * Constructs an UndoCommand object.
     *
     * @param taskList   The task list to be modified.
     * @param recycleBin The recycle bin which record historical commands and deleted tasks.
     */
    public UndoCommand(TaskList taskList, RecycleBin recycleBin) {
        super(taskList);
        this.recycleBin = recycleBin;
    }


    @Override
    public String execute() throws IOException {
        return undoLastCommand();
    }

    private String undoLastCommand() throws IOException {
        String commandString = recycleBin.getCommandString();
        String[] tempArray = commandString.split(" ");
        String command = tempArray[0];

        if (command.equals("todo") || command.equals("deadeline") || command.equals("event")) {
            Command deleteCommand = new DeleteCommand(super.list, super.list.size() - 1);
            deleteCommand.execute();
        } else if (command.equals("delete")) {
            Task deletedTask = recycleBin.getDeletedTask();
            int index = Integer.parseInt(tempArray[1]) - 1;
            Command insertCommand = new InsertCommand(index, super.list, deletedTask);
            insertCommand.execute();
        } else if (command.equals("done")) {
            int index = Integer.parseInt(tempArray[1]) - 1;
            Command reverseDoneCommand = new ReverseDoneCommand(super.list, index);
            reverseDoneCommand.execute();
        }
        return "Undo last command: " + commandString;
    }

    @Override
    public boolean isUndoCommand() {
        return true;
    }
}
