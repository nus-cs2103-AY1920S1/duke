package duke.command;

import duke.util.exception.DukeException;
import duke.util.exception.ExceptionType;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

public abstract class AddTaskCommand implements Command {
    Scanner s;
    private String description;
    private Date deadline;
    private String deadlineString;
    // todo: move dateFormatter to somewhere else that makes more sense
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");

    AddTaskCommand(String fullCommand) {
        super();
        this.s = new Scanner(fullCommand);
        s.next(); // ignore command
    }

    String getDescription() {
        return this.description;
    }

    Date getDeadline() {
        return this.deadline;
    }

    void setDescription() throws DukeException {
        try {
            this.description = this.s.next().strip();
        } catch (NoSuchElementException e) {
            // user input after task type is blank
            throw new DukeException(ExceptionType.DESCRIPTION_BLANK);
        }
    }

    void setDeadlineString() throws DukeException {
        try {
            this.deadlineString = this.s.next().strip();
        } catch (NoSuchElementException e) {
            // no deadline entered
            throw new DukeException(ExceptionType.DEADLINE_BLANK);
        }
    }

    void setDeadline() throws DukeException {
        try {
            this.deadline = this.dateFormatter.parse(this.deadlineString);
        } catch (ParseException e) {
            // deadline entered in wrong format
            throw new DukeException(ExceptionType.INVALID_DATE);
        }
    };

    abstract Task createTask();

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = createTask();
        tasks.add(newTask);
    }
}
