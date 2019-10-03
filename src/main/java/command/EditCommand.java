package command;

import converter.StringDateConverter;
import exception.DukeException;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;
import ui.Ui;

import java.text.ParseException;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class EditCommand extends Command {
    private int editIdx;
    private EditField editField;
    private String newContent;

    /**
     * Initializes EditCommand with index of identified task, the field and the new content to be edited.
     *
     * @param editIdx the index of identified task
     * @param editDetail the field and new content of the edit
     */
    public EditCommand(int editIdx, String editDetail) {
        this.editIdx = editIdx;
        Scanner scanner = new Scanner(editDetail);
        String strEditField = scanner.next().toLowerCase();
        switch (strEditField) {
        case "description":
            this.editField = EditField.DESCRIPTION;
            break;
        case "date":
            this.editField = EditField.DATE;
            break;
        default:
            throw new DukeException(DukeException.ErrorType.INVALID_EDIT_FIELD);
        }
        try {
            this.newContent = scanner.nextLine().trim();
        } catch (NoSuchElementException e) {
            throw new DukeException(DukeException.ErrorType.EMPTY_EDIT_FIELD);
        }
    }

    /**
     * Documents the type of field to be edited.
     */
    public enum EditField {
        DESCRIPTION, DATE
    }

    /**
     * Edits description or date of the identified task.
     * Prints messages to notify user that identified task is edited.
     *
     * @param tasks contains task list
     * @param ui deals with interaction with users
     * @param storage deals with loading and saving of task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.getTasks().get(this.editIdx);
            if ((this.editField == EditField.DATE && (task instanceof ToDo))) {
                throw new DukeException(DukeException.ErrorType.INVALID_TODO_EDIT_FIELD);
            }
            Date date = null;
            if (this.editField == EditField.DATE) {
                StringDateConverter converter = new StringDateConverter();
                date = converter.convertStringToDate(newContent);
            }
            if (task instanceof Deadline && this.editField == EditField.DATE) {
                assert date != null;
                ((Deadline) task).setBy(date);
            } else if (task instanceof Event && this.editField == EditField.DATE) {
                assert date != null;
                ((Event) task).setAt(date);
            } else {
                task.setDescription(newContent);
            }
            ui.showEditCommand(task);
        } catch (IndexOutOfBoundsException e) {
            ui.showLoadingError("Please enter a valid task number.");
        } catch (ParseException e) {
            ui.showLoadingError("Please enter a valid date according to dd/MM/yyyy HHmm pattern."
                    + " Time is in 24-hour format. E.g 11:30pm is 2330 and 12:30am is 0030.");
        }
    }
}
