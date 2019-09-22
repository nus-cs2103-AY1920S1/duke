package com.core;

import com.core.savedata.SaveFile;
import com.util.Printer;
import java.util.Arrays;

public class DukeResponder {
    private State state;

    /**
     * Composed with a state object and makes Dukes responses with it.
     */
    public DukeResponder() {
        state = new State(SaveFile.loadTasks());
    }

    /**
     * Get a response given an input command.
     * @param input input command
     * @return      string response
     */
    public String getResponse(String input) {
        Arrays.stream(Response.values())
                .reduce(false, (acc, val) -> acc || val.call(input, state), Boolean::logicalAnd);
        if (state.toExit) {
            System.exit(0);
        }
        return Printer.flush();
    }
}
