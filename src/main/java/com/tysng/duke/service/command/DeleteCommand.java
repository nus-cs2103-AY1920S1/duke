package com.tysng.duke.service.command;

import com.tysng.duke.domain.Task;
import com.tysng.duke.dto.ParsedCommand;
import com.tysng.duke.service.DukeService;
import com.tysng.duke.ui.Response;

public class DeleteCommand extends AbstractCommand implements Command {
    public DeleteCommand(ParsedCommand parsed) {
        super(parsed);
    }

    @Override
    public Response execute(DukeService service) {
        Task removed = service.taskList.removeTask(parsed.getTargetIndex().getIndex());
        return Response.newDelete(removed, service.taskList.getTaskList().size());
    }
}
