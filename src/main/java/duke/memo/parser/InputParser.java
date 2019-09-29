package duke.memo.parser;

import duke.memo.command.AddCommand;
import duke.memo.command.Command;
import duke.memo.command.DeleteCommand;
import duke.memo.command.DoneCommand;
import duke.memo.command.ExitCommand;
import duke.memo.command.ExpenseCommand;
import duke.memo.command.FindCommand;
import duke.memo.command.ListCommand;
import duke.memo.exception.DukeException;
import duke.memo.exception.NoDescriptionException;
import duke.memo.exception.TaskTypeError;
import duke.memo.record.Record;
import duke.memo.record.expense.Expense;
import duke.memo.record.task.Deadline;
import duke.memo.record.task.Event;
import duke.memo.record.task.ToDo;

public class InputParser {
    private static final String BYE_CMD = "bye";
    private static final String LIST_CMD = "list";
    private static final String FIND_CMD = "find";
    private static final String DONE_CMD = "done";
    private static final String DELETE_CMD = "delete";
    private static final String EXPENSE_CMD = "expense";
    private static final String SPLIT_REGEX = " ";
    public static final int SPLIT_NO = 2;

    /**
     * Static method parse for Parser.
     * To parse the log into a command.
     *
     * @param cmd  Full command in log file.
     * @return parsed command
     * @throws DukeException Throw if there is a problem in the command.
     */
    public static Command parse(String cmd) throws DukeException {
        if (cmd.equalsIgnoreCase(BYE_CMD)) {
            return new ExitCommand();
        } else if (cmd.equalsIgnoreCase(LIST_CMD)) {
            return new ListCommand();
        } else if (cmd.startsWith(FIND_CMD)) {
            examineCmd(cmd, FIND_CMD);
            return new FindCommand(cmd.substring(FIND_CMD.length() + SPLIT_REGEX.length()));
        } else if (cmd.startsWith(DONE_CMD)) {
            examineCmd(cmd, DONE_CMD);
            return new DoneCommand(cmd.substring(DONE_CMD.length() + SPLIT_REGEX.length()).trim());
        } else if (cmd.startsWith(DELETE_CMD)) {
            examineCmd(cmd, DELETE_CMD);
            return new DeleteCommand(cmd.substring(DELETE_CMD.length() + SPLIT_REGEX.length()).trim());
        } else if (cmd.startsWith(EXPENSE_CMD)) {
            examineCmd(cmd,EXPENSE_CMD);
            return new ExpenseCommand(cmd.substring(EXPENSE_CMD.length() + SPLIT_REGEX.length()).trim());
        } else {
            return new AddCommand(cmd);
        }
    }

    private static void examineCmd(String cmd, String targetCmd) throws DukeException {
        if (cmd.trim().equalsIgnoreCase(targetCmd)) {
            throw  new NoDescriptionException(targetCmd);
        } else if (!cmd.split(SPLIT_REGEX, SPLIT_NO)[0].equalsIgnoreCase(targetCmd)) {
            throw new TaskTypeError();
        }
    }


    /**
     * Convert raw log string to a task object.
     *
     * @param log  Raw log.
     * @return Task return the parsed log
     * @throws DukeException Throw if there is a problem in the log.
     */
    public static Record parseLog(String log) throws DukeException {
        String[] taskDetails = log.split(" \\| ",4);

        switch (taskDetails[0]) {
        case "T":
            return new ToDo(taskDetails);
        case "E":
            return new Event(taskDetails);
        case "D":
            return new Deadline(taskDetails);
        case "EX":
            return new Expense(taskDetails);
        default:
            return null;
        }
    }
}
