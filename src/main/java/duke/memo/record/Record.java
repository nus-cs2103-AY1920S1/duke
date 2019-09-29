package duke.memo.record;

import duke.memo.exception.DukeException;
import duke.memo.exception.NoDescriptionException;

public abstract class Record {
    protected String description;
    protected boolean isDone;

    public Record() {
        isDone = false;
    }

    public Record(String description) {
        this.description = description;
    }

    /**
     * Constructor for Expense.
     * Throw error if there is no amount description or wrong format.
     *
     * @param desc  Description for the Record.
     * @param recordType Type for the record.
     * @throws DukeException  If details missing or in wrong format.
     */
    public Record(String desc, String recordType) throws DukeException {
        if (desc.isBlank()) {
            throw new NoDescriptionException(recordType);
        } else {
            description = desc;
            isDone = false;
        }
    }

    public Record taskDone() {
        isDone = true;
        return this;
    }

    public abstract String getPrintableMsg();

    public String getDescription() {
        return description;
    }
}
