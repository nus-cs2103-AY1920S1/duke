package com.leeyiyuan.command.format;


import com.leeyiyuan.command.Command;
import java.util.ArrayList;

public class Parser {

    protected ArrayList<CommandFormatter> commandFormatters;

    public Parser() {
        this.commandFormatters = new ArrayList<CommandFormatter>();
        this.commandFormatters.add(new AddDeadlineTaskCommandFormatter());
        this.commandFormatters.add(new AddEventTaskCommandFormatter());
        this.commandFormatters.add(new AddTodoTaskCommandFormatter());
        this.commandFormatters.add(new DeleteTaskCommandFormatter());
        this.commandFormatters.add(new ExitCommandFormatter());
        this.commandFormatters.add(new ListTasksCommandFormatter());
        this.commandFormatters.add(new TaskDoneCommandFormatter());
    }

    public Command parse(String text) throws CommandParseException {
        Command command = null;

        for (CommandFormatter commandFormatter : this.commandFormatters) {
            try {
                command = commandFormatter.parse(text);
                break;
            } catch (UnsupportedCommandException e) {

            } catch (CommandParseException e) {
                throw e;
            }
        }

        if (command == null) {
            throw new UnsupportedCommandException("Unknown command.");
        }

        return command;
    }
}
