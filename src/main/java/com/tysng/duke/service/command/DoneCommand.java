package com.tysng.duke.service.command;

import com.tysng.duke.dto.ParsedCommand;
import com.tysng.duke.service.DukeService;
import com.tysng.duke.ui.Response;

public class DoneCommand extends AbstractCommand implements Command {
    public DoneCommand(ParsedCommand parsed) {
        super(parsed);
    }

    @Override
    public Response execute(DukeService service) {
        service.taskList.getTask(parsed.getTargetIndex().getIndex()).setCompleted(true);
        return Response.newDone(service.taskList.getTask(parsed.getTargetIndex().getIndex()));
    }
}
