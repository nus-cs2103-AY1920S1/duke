package duke.memo.command;

import duke.memo.data.RecordList;
import duke.memo.exception.DukeException;
import duke.memo.exception.NotNumberException;
import duke.memo.exception.TaskNotExistException;
import duke.memo.record.Record;
import duke.memo.storage.Storage;
import duke.memo.message.MessageGenerator;

public class DoneCommand extends Command {
    private int targetTaskNo;

    /**
     * Constructor for DoneCommand.
     * Throw error if no task index no enter or in wrong format.
     *
     * @param targetTaskNo  The target task index no.
     * @throws DukeException  If the input cannot be format to number.
     */
    public DoneCommand(String targetTaskNo) throws DukeException {
        try {
            this.targetTaskNo = Integer.parseInt(targetTaskNo) - 1;
        } catch (NumberFormatException e) {
            throw new NotNumberException();
        }
    }

    @Override
    public String execute(RecordList recordList, MessageGenerator msgGenerator, Storage storage) throws DukeException {
        try {
            Record doneTask = recordList.get(targetTaskNo).taskDone();
            storage.store(recordList);
            return msgGenerator.generateDoneTaskMsg(doneTask.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotExistException();
        }

    }
}
