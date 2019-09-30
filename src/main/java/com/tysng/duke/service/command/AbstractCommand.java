package com.tysng.duke.service.command;

import com.tysng.duke.dto.ParsedCommand;

public class AbstractCommand {
    ParsedCommand parsed;
    public AbstractCommand(ParsedCommand parsed) {
        this.parsed = parsed;
    }
}
