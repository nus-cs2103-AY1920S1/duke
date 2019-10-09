package duke.command;

import duke.task.TaskList;

/**
 * ByeCommand class.
 */
public class ByeCommand extends Command {
    /**
     * {@inheritDoc}
     */
    public String[] execute(TaskList taskList) {
        //CHECKSTYLE.OFF: AvoidEscapedUnicodeCharactersCheck
        return new String[] {"\u2600 Bye! Hope to see you again soon!"};
        //CHECKSTYLE.ON: AvoidEscapedUnicodeCharactersCheck
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return true;
    }
}
