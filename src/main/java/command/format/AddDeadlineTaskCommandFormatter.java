package com.leeyiyuan.command.format;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import com.leeyiyuan.command.AddDeadlineTaskCommand;
import com.leeyiyuan.command.Command;
import com.leeyiyuan.command.format.CommandFormatter;
import com.leeyiyuan.command.format.CommandParseException;

public class AddDeadlineTaskCommandFormatter extends CommandFormatter {

    @Override
    public Command parse(String text) throws CommandParseException {
        if (Pattern.matches("^deadline ?/by.*$", text)) {
            throw new CommandParseException("Title of deadline cannot be empty.");
        } else if (Pattern.matches("^deadline .+ /by ?$", text)) {
            throw new CommandParseException("By value of deadline cannot be empty.");
        } else if (Pattern.matches("^deadline .+ /by .+$", text)) {
            String[] data = text.split("deadline ", 2)[1].split(" /by ", 2);
            String title = data[0];
            LocalDateTime by = null;
            try {
                by = LocalDateTime.parse(data[1], DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            } catch (DateTimeParseException e) {
                throw new CommandParseException("By value of deadline invalid");
            }
            return new AddDeadlineTaskCommand(title, by);
        } else {
            throw new CommandParseException("Unhandled command.");
        }
    }

}
