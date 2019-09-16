package duke.command.factory;

import duke.command.Command;
import duke.command.entities.CommandType;
import duke.command.entities.UndoAction;
import duke.task.TasksController;
import error.command.CommandCreationException;
import error.ui.UiException;
import ui.UiController;
import util.command.CommandUtils;

import java.util.Optional;
import java.util.Stack;

public class UndoCommandFactory {
    private UiController ui;
    private TasksController tasksController;

    public UndoCommandFactory(UiController ui, TasksController tasksController) {
        this.ui = ui;
        this.tasksController = tasksController;
    }


    public Optional<Command> getUndoCommand(String input, Stack<Command> history) throws UiException, CommandCreationException {
        if (!CommandUtils.getArgumentsAsString(input).equals("")) {
            ui.displayOutput("☹ OOPS!!! I'm sorry, undo doesn't accept arguments :-(");
            return Optional.empty();
        }

        UndoAction nextUndo = null;

        while (!history.isEmpty()) {
            Command prevCommand = history.pop();
            if (prevCommand.getUndoAction().isPresent()) {
                nextUndo = prevCommand.getUndoAction().get();
                break;
            }
        }

        if (nextUndo == null) {
            ui.displayOutput("☹ OOPS!!! There is no previous action to undo! :-(");
            return Optional.empty();
        }

        final UndoAction finalUndo = nextUndo;

        Command undoCommand = new Command(CommandType.UNDO, ui, tasksController) {
            private UndoAction undo = finalUndo;
            @Override
            public void execute() throws UiException {
                undo.undo();
            }

            @Override
            public Optional<UndoAction> getUndoAction() {
                return Optional.empty();
            }
        };

        return Optional.of(undoCommand);
    }
}
