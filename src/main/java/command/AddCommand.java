package command;

import task.Deadline;
import task.Event;
import task.TaskList;
import task.ToDo;
import util.Storage;
import util.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddCommand extends Command {

    public AddCommand(String command) {
        this.inputCommand = command;
    }

    @Override
    public String executeCommand(TaskList taskList, Storage storage) {
        String desc;
        String msg;
        assert (!inputCommand.isEmpty()) : "Input inputCommand cannot empty";
        String op = inputCommand.split(" ")[0];
        if (op.equals("todo")) {
            if (inputCommand.substring(4).isEmpty()) {
                msg = Ui.emptyTaskMsg("todo");
            } else {
                desc = inputCommand.substring(5);
                msg = taskList.addTask(new ToDo(desc));
            }
        } else if (op.equals("deadline")) {
            if (inputCommand.substring(8).isEmpty()) {
                msg = Ui.emptyTaskMsg("deadline");
            } else if (!inputCommand.contains("/by")) {
                msg = Ui.missingDeadlineMsg();
            } else {
                int dateStartIndex = inputCommand.lastIndexOf("/by");
                desc = inputCommand.substring(9, dateStartIndex - 1);
                String ddl = convertDate(inputCommand.substring(dateStartIndex + 4));
                msg = taskList.addTask(new Deadline(desc, ddl));
            }
        } else if (op.equals("event")) {
            if (inputCommand.substring(5).isEmpty()) {
                msg = Ui.emptyTaskMsg("event");
            } else if (!inputCommand.contains("/at")) {
                msg = Ui.missingEventMsg();
            } else {
                int dateStartIndex = inputCommand.indexOf("/at");
                desc = inputCommand.substring(6, dateStartIndex - 1);
                String date = convertDate(inputCommand.substring(dateStartIndex + 4));
                msg = taskList.addTask(new Event(desc, date));
            }
        } else {
            msg = Ui.unknownMsg();
        }
        return msg;
    }

    /**
     * For level8 to transform a given string to a date
     * Supported format: dd/MM/yyyy Hmm or dd/MM/yyyy or d/MM/yyyy Hmm or d/MM/yyyy
     * e.g. 1/12/2019, 1/12/2019 1845, 10/12/2019, 10/12/2019 1845
     *
     * @param date given string
     * @return converted date
     */
    private String convertDate(String date) {
        if (date.indexOf('/') > 1) {
            if (date.indexOf(' ') > -1) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy Hmm");
                LocalDateTime newDate = LocalDateTime.parse(date, formatter);
                return newDate.format(DateTimeFormatter.ofPattern("MMM dd H:mma, yyyy"));
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate newDate = LocalDate.parse(date, formatter);
                return newDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
            }
        } else if (date.indexOf('/') <= 1 && date.indexOf('/') > -1) {
            if (date.indexOf(' ') > -1) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy Hmm");
                LocalDateTime newDate = LocalDateTime.parse(date, formatter);
                return newDate.format(DateTimeFormatter.ofPattern("MMM dd H:mma, yyyy"));
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                LocalDate newDate = LocalDate.parse(date, formatter);
                return newDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
            }
        }
        return date;
    }
}
