package com.tysng.duke.service.command;

import com.tysng.duke.service.DukeService;
import com.tysng.duke.ui.Response;

public interface Command {
    Response execute(DukeService service);
}
