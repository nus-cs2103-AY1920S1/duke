package duke.memo.command;

import duke.memo.data.RecordList;
import duke.memo.message.MessageGenerator;
import duke.memo.record.Record;
import duke.memo.storage.Storage;

import java.util.List;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(RecordList recordList, MessageGenerator msgGenerator, Storage storage) {
        List<Record> resultTasks = recordList.stream()
                .filter(t -> t.getDescription().contains(keyword))
                .collect(Collectors.toList());
        return msgGenerator.generateResultRecordMsg(resultTasks);
    }
}