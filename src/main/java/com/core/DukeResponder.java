package com.core;

import com.core.savedata.SaveFile;
import com.util.Printer;

public class DukeResponder {
    private State state;

    public DukeResponder() {
        state = new State(SaveFile.loadTasks());
    }
    public String getResponse(String input) {
        for (Response r : Response.values()) {
            if (r.call(input, state)) {
                break;
            }
        }
        return Printer.flush();
    }
}
