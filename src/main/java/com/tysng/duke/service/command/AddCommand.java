package com.tysng.duke.service.command;

import com.tysng.duke.service.DukeService;
import com.tysng.duke.dto.ParsedCommand;
import com.tysng.duke.ui.Response;

public class AddCommand extends AbstractCommand implements Command {
    public AddCommand(ParsedCommand parsed) {
        super(parsed);
    }

    @Override
    public Response execute(DukeService service) {
        service.taskList.addTask(parsed.getAddedTask());
        return Response.newAdded(parsed.getAddedTask(), service.taskList.getTaskList().size());
    }
}
