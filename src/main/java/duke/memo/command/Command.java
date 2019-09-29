package duke.memo.command;

import duke.memo.data.RecordList;
import duke.memo.exception.DukeException;
import duke.memo.storage.Storage;
import duke.memo.message.MessageGenerator;

public abstract class Command {
    public abstract String execute(RecordList recordList, MessageGenerator msgGenerator,
                                   Storage storage)  throws DukeException;

}
