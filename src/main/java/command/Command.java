package com.leeyiyuan.command;


import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.storage.StorageException;
import com.leeyiyuan.task.TaskList;
import com.leeyiyuan.ui.Ui;

/** 
 * Represents a command. 
 */
public abstract class Command {

    /**
     * Executs the command against a given context.
     *
     * @param tasks List of tasks.
     * @param ui User interface to interact with the user.
     * @param Storage Storage to handle serialization and deserialization of tasks.
     * @throws CommandExecuteException If there was an error executing the command.
     * @throws StorageException If there was an error interacting with storage.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage)
            throws CommandExecuteException, StorageException;
}
