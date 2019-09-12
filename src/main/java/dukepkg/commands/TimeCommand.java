package dukepkg.commands;

import dukepkg.*;
import dukepkg.exceptions.FormatException;

import java.io.IOException;
import java.sql.Time;

public class TimeCommand extends TodoCommand {

    /**
     * Instantiates a new Add task command.
     *
     * @param t the task that is going to be added by the command.
     */
    TimeCommand(Task t) {
        super(t);
    }

}
