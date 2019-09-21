package duke.command.entities;

import duke.command.command.ByeCommand;
import duke.command.command.DeleteCommand;
import duke.command.command.DoneCommand;
import duke.command.command.FindCommand;
import duke.command.command.ListCommand;
import duke.command.command.SortCommand;
import duke.command.command.UpdateCommand;

/**
 * Enum encapsulating the different commands and their keywords.
 */
public enum CommandType {
    BYE("bye", (s, ui, tasksController) -> {
        return new ByeCommand(s, ui, tasksController);
    }),
    DELETE("delete", (argument, ui, tasksController) -> {
        return new DeleteCommand(argument, ui, tasksController);
    }),
    DONE("done", (doneIndex, ui, tasksController) -> {
        return new DoneCommand(doneIndex, ui, tasksController);
    }),
    FIND("find", (arguments, ui, tasksController) -> {
        return new FindCommand(arguments, ui, tasksController);
    }),
    LIST("list", (s, ui, tasksController) -> {
        return new ListCommand(s, ui, tasksController);
    }),
    UNDO("undo", null),
    SORT("sort", (arguments, ui, tasksController) -> {
        return new SortCommand(arguments, ui, tasksController);
    }),
    UPDATE("update", (arguments, ui, tasksController) -> {
        return new UpdateCommand(arguments, ui, tasksController);
    });

    public final String keyword;
    public final CommandProducer producer;

    CommandType(String keyword, CommandProducer producer) {
        this.keyword = keyword;
        this.producer = producer;
    }
}
