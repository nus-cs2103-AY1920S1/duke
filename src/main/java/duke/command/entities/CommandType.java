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
    BYE("bye", ByeCommand::new),
    DELETE("delete", DeleteCommand::new),
    DONE("done", DoneCommand::new),
    FIND("find", FindCommand::new),
    LIST("list", ListCommand::new),
    UNDO("undo", null),
    SORT("sort", SortCommand::new),
    UPDATE("update", UpdateCommand::new);

    public final String keyword;
    public final CommandProducer producer;

    CommandType(String keyword, CommandProducer producer) {
        this.keyword = keyword;
        this.producer = producer;
    }
}
