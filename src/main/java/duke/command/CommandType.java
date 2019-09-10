package duke.command;

import duke.command.command.*;

public enum CommandType {
    BYE("bye", ByeCommand::new),
    DELETE("delete", DeleteCommand::new),
    DONE("done", DoneCommand::new),
    FIND("find", FindCommand::new),
    LIST("list", ListCommand::new);

    public final String keyword;
    public final CommandProducer producer;

    CommandType(String keyword, CommandProducer producer) {
        this.keyword = keyword;
        this.producer = producer;
    }
}
