package duke.memo.command;

import duke.memo.data.RecordList;
import duke.memo.storage.Storage;
import duke.memo.message.MessageGenerator;

public class ListCommand extends Command {
    @Override
    public String  execute(RecordList recordList, MessageGenerator msgGenerator, Storage storage) {
        return msgGenerator.generateListRecordMsg(recordList);
    }
}
