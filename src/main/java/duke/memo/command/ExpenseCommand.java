package duke.memo.command;

import duke.memo.data.RecordList;
import duke.memo.exception.DukeException;
import duke.memo.exception.NoDescriptionException;
import duke.memo.exception.NotNumberException;
import duke.memo.message.MessageGenerator;
import duke.memo.record.expense.Expense;
import duke.memo.storage.Storage;

public class ExpenseCommand extends Command {
    private String cmd;

    public ExpenseCommand(String cmd) {
        super();
        this.cmd = cmd;
    }

    @Override
    public String execute(RecordList recordList, MessageGenerator msgGenerator, Storage storage) throws DukeException {
        String[] expenseDetails = cmd.split(" / ", 2);
        try {
            Expense curExpense = new Expense(expenseDetails[0], expenseDetails[1]);
            recordList.add(curExpense);
            storage.store(recordList);
            return msgGenerator.generateAddedRecordMsg(curExpense.toString(), recordList.size());
        } catch (IndexOutOfBoundsException e) {
            throw new NoDescriptionException("expense");
        } catch (NumberFormatException e) {
            throw new NotNumberException();
        }
    }
}
