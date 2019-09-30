package com.tysng.duke.service.command;

import com.tysng.duke.dto.ParsedCommand;
import com.tysng.duke.service.DukeService;
import com.tysng.duke.ui.Response;

public class ExitCommand extends AbstractCommand implements Command {
    public ExitCommand(ParsedCommand parsed) {
        super(parsed);
    }

    @Override
    public Response execute(DukeService service) {
        return Response.newFarewell();
    }
}
