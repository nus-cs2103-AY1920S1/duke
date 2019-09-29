package duke.memo.data;

import duke.memo.exception.DukeException;
import duke.memo.parser.InputParser;
import duke.memo.record.Record;

import java.util.ArrayList;

public class RecordList extends ArrayList<Record> {
    public RecordList() {
        super();
    }

    /**
     * Constructor for RecordList.
     *
     * @param recordList  list of records.
     * @throws DukeException throw if there is a problem in the command.
     */
    public RecordList(ArrayList<String> recordList) throws DukeException {
        super();
        if (recordList.isEmpty() || recordList.get(0).isEmpty()) {
            return;
        }
        for (String recordString : recordList) {
            add(InputParser.parseLog(recordString));
        }
    }


}
