package jermi.command;

import static jermi.misc.Constant.COMMAND_HELP_MESSAGE;
import static jermi.misc.Constant.FORMATTER_ADD_BLANK_LINE;
import static jermi.misc.Constant.HELP_COMMAND;
import static jermi.misc.Constant.HELP_MESSAGE_CLEAR;
import static jermi.misc.Constant.HELP_MESSAGE_DEADLINE;
import static jermi.misc.Constant.HELP_MESSAGE_DELETE;
import static jermi.misc.Constant.HELP_MESSAGE_DONE;
import static jermi.misc.Constant.HELP_MESSAGE_EVENT;
import static jermi.misc.Constant.HELP_MESSAGE_EXIT;
import static jermi.misc.Constant.HELP_MESSAGE_FIND;
import static jermi.misc.Constant.HELP_MESSAGE_LIST;
import static jermi.misc.Constant.HELP_MESSAGE_TODO;

import java.util.Objects;

import jermi.component.Formatter;
import jermi.component.Storage;
import jermi.component.TaskList;
import jermi.exception.JermiException;

public class HelpCommand extends Command {
    @Override
    public String execute(TaskList taskList, Formatter formatter, Storage storage) throws JermiException {
        return formatter.echo(COMMAND_HELP_MESSAGE,
                FORMATTER_ADD_BLANK_LINE,
                HELP_MESSAGE_LIST,
                FORMATTER_ADD_BLANK_LINE,
                HELP_MESSAGE_TODO,
                FORMATTER_ADD_BLANK_LINE,
                HELP_MESSAGE_DEADLINE,
                FORMATTER_ADD_BLANK_LINE,
                HELP_MESSAGE_EVENT,
                FORMATTER_ADD_BLANK_LINE,
                HELP_MESSAGE_FIND,
                FORMATTER_ADD_BLANK_LINE,
                HELP_MESSAGE_DONE,
                FORMATTER_ADD_BLANK_LINE,
                HELP_MESSAGE_DELETE,
                FORMATTER_ADD_BLANK_LINE,
                HELP_MESSAGE_CLEAR,
                FORMATTER_ADD_BLANK_LINE,
                HELP_MESSAGE_EXIT);
    }

    @Override
    public boolean shouldExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof HelpCommand;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(HELP_COMMAND);
    }
}
