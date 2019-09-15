package com.commands;

public class ExitCommand extends Command {

    public ExitCommand() {
        super("bye");
    }

    public void execute(Duke duke) {
        Ui ui = duke.getUi();
        ui.showGoodbye();
    }
}

