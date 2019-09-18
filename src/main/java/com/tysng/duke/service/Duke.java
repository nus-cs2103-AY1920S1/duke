package com.tysng.duke.service;

import com.tysng.duke.domain.Task;
import com.tysng.duke.storage.Storage;
import com.tysng.duke.ui.Response;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class handles the logic of the Application.
 *
 * <p>For every Command, it will process and return a Response object. This class
 * also interacts with te Storage class to retrieve and save TaskList objects to local storage.
 */
public class Duke {
    private TaskList taskList;
    private TaskList archiveList;
    private Storage storage;

    /**
     * Constructs a service layer object with the external Storage layer.
     *
     * @param storage an instance of the Storage object
     */
    public Duke(Storage storage) {
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
        Response response;
        switch (command.getType()) {
        case LIST:
            response = this.handleList(command.getKeyword());
            break;
        case DONE:
            response = this.handleDone(command.getTargetIndex());
            break;
        case ARCHIVE:
            response = this.handleArchive(command.getTargetIndex());
            break;
        case DELETE:
            response = this.handleDelete(command.getTargetIndex());
            break;
        case ADD:
            response = this.handleAddItem(command.getAddedTask());
            break;
        case BYE:
            response = this.handleBye();
            break;
        case FIND:
            response = this.handleFind(command.getKeyword());
            break;
        case ECHO:
            response = this.handleEcho(command.toString());
            break;
        default:
            throw new Error("Fatal error: the command is not valid");
        }
        this.storage.saveTasks(this.taskList.getTaskList());
        this.storage.saveArchive(this.archiveList.getTaskList());
        return response;
    }

    private Response handleFind(String keyword) {
        List<Task> matchedTasks = taskList.getTaskList().stream()
                .filter(task -> task.getTaskName().contains(keyword))
                .collect(Collectors.toList());
        return Response.newMatch(matchedTasks);
    }

    private Response handleDone(int targetIndex) {
        taskList.getTask(targetIndex).setCompleted(true);
        return Response.newDone(this.taskList.getTask(targetIndex));
    }

    private Response handleArchive(int targetIndex) {
        Task removed = taskList.getTaskList().remove(targetIndex);
        archiveList.addTask(removed);
        return Response.newArchive(removed);
    }

    private Response handleDelete(int targetIndex) {
        Task removed = this.taskList.removeTask(targetIndex);
        return Response.newDelete(removed, this.taskList.getTaskList().size());
    }

    private Response handleBye() {
        return Response.newFarewell();
    }

    private Response handleEcho(String command) {
        return Response.newEcho(command);
    }

    private Response handleAddItem(Task item) {
        this.taskList.addTask(item);
        return Response.newAdded(item, this.taskList.getTaskList().size());
    }

    private Response handleList(String arg) {
        if (arg == null) {
            return Response.newListing(this.taskList.getTaskList());
        }
        if (arg.equals("archive")) {
            return Response.newListing(this.archiveList.getTaskList());
        }
        return Response.newListing(this.taskList.getTaskList());
    }

}
