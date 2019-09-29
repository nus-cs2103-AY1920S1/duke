package duke.memo.command;

import duke.memo.data.RecordList;
import duke.memo.exception.DukeException;
import duke.memo.exception.NoDescriptionException;
import duke.memo.exception.TaskTypeError;
import duke.memo.storage.Storage;
import duke.memo.record.task.Deadline;
import duke.memo.record.task.Event;
import duke.memo.record.task.Task;
import duke.memo.record.task.ToDo;
import duke.memo.message.MessageGenerator;

public class AddCommand extends Command {
    private String cmd;

    public AddCommand(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String execute(RecordList recordList, MessageGenerator msgGenerator, Storage storage) throws DukeException {
        String[] curTaskDetails = cmd.split(" ", 2);

        try {
            Task curTask;
            switch (curTaskDetails[0]) {
            case "todo":
                examineTaskDetails(curTaskDetails);
                curTask = new ToDo(curTaskDetails[1]);
                break;
            case "deadline":
                examineTaskDetails(curTaskDetails);
                curTask = new Deadline(curTaskDetails[1]);
                break;
            case "event":
                examineTaskDetails(curTaskDetails);
                curTask = new Event(curTaskDetails[1]);
                break;
            default:
                throw new TaskTypeError();
            }
            recordList.add(curTask);
            storage.store(recordList);
            return msgGenerator.generateAddedRecordMsg(curTask.toString(), recordList.size());
        } catch (DukeException e) {
            throw e;
        }

    }

    private void examineTaskDetails(String[] curTaskDetails) throws DukeException {
        try {
            if (curTaskDetails[1].trim().isEmpty()) {
                throw new NoDescriptionException(curTaskDetails[0]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoDescriptionException(curTaskDetails[0]);
        }
    }
}
