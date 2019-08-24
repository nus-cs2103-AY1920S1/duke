package com.leeyiyuan.command.format;

import java.util.ArrayList;

import com.leeyiyuan.command.Command;
import com.leeyiyuan.command.format.AddDeadlineTaskCommandFormatter;
import com.leeyiyuan.command.format.AddEventTaskCommandFormatter;
import com.leeyiyuan.command.format.AddTodoTaskCommandFormatter;
import com.leeyiyuan.command.format.CommandFormatter;
import com.leeyiyuan.command.format.CommandParseException;
import com.leeyiyuan.command.format.DeleteTaskCommandFormatter;
import com.leeyiyuan.command.format.ExitCommandFormatter;
import com.leeyiyuan.command.format.ListTasksCommandFormatter;
import com.leeyiyuan.command.format.TaskDoneCommandFormatter;

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
            } catch (CommandParseException e) {
                
            }
        }

        if (command == null) {
            throw new CommandParseException("Unknown command.");
        }

        return command;
    }

}
