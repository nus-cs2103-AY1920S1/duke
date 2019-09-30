package com.tysng.duke.service;

import com.tysng.duke.service.command.Command;
import com.tysng.duke.storage.Storage;
import com.tysng.duke.ui.Response;

/**
 * This class handles the logic of the Application.
 *
 * <p>For every Command, it will process and return a Response object. This class
 * also interacts with te Storage class to retrieve and save TaskList objects to local storage.
 */
public class DukeService {
    public TaskList taskList;
    public TaskList archiveList;
    public Storage storage;

    /**
     * Constructs a service layer object with the external Storage layer.
     *
     * @param storage an instance of the Storage object
     */
    public DukeService(Storage storage) {
        this.storage = storage;
        this.taskList = new TaskList(this.storage.loadTasks());
        this.archiveList = new TaskList(this.storage.loadArchive());
    }

    /**
     * Processes a Command object. This is the driver method of the class.
     *
     * @param command a successfully parsed user command
     * @return the corresponding response for the given command
     */
    public Response handle(Command command) {
        Response response = command.execute(this);
        this.storage.saveTasks(this.taskList.getTaskList());
        this.storage.saveArchive(this.archiveList.getTaskList());
        return response;
    }


}
