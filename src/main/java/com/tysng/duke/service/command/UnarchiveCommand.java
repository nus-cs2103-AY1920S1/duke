package com.tysng.duke.service.command;

import com.tysng.duke.domain.Task;
import com.tysng.duke.dto.ParsedCommand;
import com.tysng.duke.service.DukeService;
import com.tysng.duke.ui.Response;

public class UnarchiveCommand extends AbstractCommand implements Command {
    public UnarchiveCommand(ParsedCommand parsed) {
        super(parsed);
    }

    @Override
    public Response execute(DukeService service) {
        Task removed = service.archiveList
                .getTaskList()
                .remove(parsed.getTargetIndex().getIndex());
        service.taskList.addTask(removed);
        return Response.newListing(service.taskList.getTaskList());
    }
}
