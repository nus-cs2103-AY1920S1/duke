package com.leeyiyuan.command;


import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.storage.StorageException;
import com.leeyiyuan.task.TaskList;
import com.leeyiyuan.ui.UserOutputInterface;

/** 
 * Represents a command. 
 */
public abstract class Command {

    /**
     * Executs the command against a given context.
     *
     * @param tasks List of tasks.
     * @param uoi User output interface to interact with the user.
     * @param storage Storage to handle serialization and deserialization of tasks.
     * @throws CommandExecuteException If there was an error executing the command.
     * @throws StorageException If there was an error interacting with storage.
     */
    public abstract void execute(TaskList tasks, UserOutputInterface uoi, Storage storage)
            throws CommandExecuteException, StorageException;
}
