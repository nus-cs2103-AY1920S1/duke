package com.tysng.duke.service.command;

import com.tysng.duke.domain.Task;
import com.tysng.duke.dto.ParsedCommand;
import com.tysng.duke.service.DukeService;
import com.tysng.duke.ui.Response;

public class ArchiveCommand extends AbstractCommand implements Command {
    public ArchiveCommand(ParsedCommand parsed) {
        super(parsed);
    }

    @Override
    public Response execute(DukeService service) {
        Task removed = service.taskList.getTaskList().remove(parsed.getTargetIndex().getIndex());
        service.archiveList.addTask(removed);
        return Response.newArchive(removed);
    }
}
