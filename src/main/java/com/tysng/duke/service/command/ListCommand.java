package com.tysng.duke.service.command;

import com.tysng.duke.dto.ParsedCommand;
import com.tysng.duke.service.DukeService;
import com.tysng.duke.ui.Response;

public class ListCommand extends AbstractCommand implements Command {
    public ListCommand(ParsedCommand parsed) {
        super(parsed);
    }

    @Override
    public Response execute(DukeService service) {
        String arg = parsed.getKeyword();
        if (arg == null) {
            return Response.newListing(service.taskList.getTaskList());
        } else if (arg.equals("archive")) {
            return Response.newListing(service.archiveList.getTaskList());
        } else {
            return Response.newEcho(arg);
        }
    }

}
