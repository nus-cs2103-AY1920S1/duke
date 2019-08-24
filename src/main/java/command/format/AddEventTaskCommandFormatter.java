package com.leeyiyuan.command.format;


import com.leeyiyuan.command.AddEventTaskCommand;
import com.leeyiyuan.command.Command;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class AddEventTaskCommandFormatter extends CommandFormatter {

    @Override
    public Command parse(String text) throws CommandParseException {
        if (Pattern.matches("^event ?/at.*$", text)) {
            throw new CommandParseException("Description of event cannot be empty.");
        } else if (Pattern.matches("^event .+ /at ?$", text)) {
            throw new CommandParseException("Time of event cannot be empty.");
        } else if (Pattern.matches("^event .+ /at .+$", text)) {
            String[] data = text.split("event ", 2)[1].split(" /at ", 2);
            String title = data[0];
            LocalDateTime at = null;
            try {
                at = LocalDateTime.parse(data[1], DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            } catch (DateTimeParseException e) {
                throw new CommandParseException("At value of deadline invalid");
            }
            return new AddEventTaskCommand(title, at);
        } else {
            throw new CommandParseException("Unhandled command.");
        }
    }
}
