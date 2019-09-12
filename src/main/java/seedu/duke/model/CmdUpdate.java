package seedu.duke.model;

import seedu.duke.core.Storage;
import seedu.duke.exception.DukeException;
import seedu.duke.model.dto.Deadline;
import seedu.duke.model.dto.Event;
import seedu.duke.model.dto.Task;
import seedu.duke.model.dto.Todo;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class CmdUpdate extends Cmd {
    public CmdUpdate(List<Task> taskList, String input, Storage storage)
            throws DukeException {
        if (processInput(input, taskList, storage)) {
            this.setMsg("Update successful!");
        }
    }

    private boolean processInput(String input, List<Task> taskList, Storage storage)
            throws DukeException {
        String[] inputs = input.split(" ", 2);
        boolean isUpdated = false;
        try {
            int index = Integer.valueOf(inputs[0]) - 1;
            boolean isValidIndex = validateIndex(taskList, index);
            if (isValidIndex) {
                //validate Todo or Deadline/Event
                boolean isTodo = checkIfTodo(index, taskList);
                boolean isEvent = checkIfEvent(index, taskList);
                if (isTodo) {
                    taskList = handleTodoUpdate(inputs, index, taskList);
                } else if (isEvent) {
                    taskList = handleEventUpdate(inputs, index, taskList);
                }
                storage.saveTask(taskList);
                isUpdated = true;
            } else {
                throw new DukeException("Invalid index! please enter valid index..");
            }

        } catch (NumberFormatException | IOException | ParseException e) {
            this.setMsg(e.toString());
        }
        return isUpdated;
    }

    private List<Task> handleTodoUpdate(String[] inputs, int index, List<Task> taskList) {
        String description = inputs[1];
        taskList.set(index, new Todo(description));

        return taskList;
    }

    private List<Task> handleEventUpdate(String[] inputs, int index, List<Task> taskList)
            throws ParseException {
        String[] descAndTime = inputs[1].split(" /at ", 2);
        String description = descAndTime[0];
        String time = descAndTime[1];
        taskList.set(index, new Event(description, time));

        return taskList;
    }

    private List<Task> handleDeadlineUpdate(String[] inputs, int index, List<Task> taskList)
            throws ParseException {
        String[] descAndTime = inputs[1].split(" /by ", 2);
        String description = descAndTime[0];
        String time = descAndTime[1];
        taskList.set(index, new Deadline(description, time));

        return taskList;
    }

    private boolean checkIfTodo(int index, List<Task> taskList) {
        return taskList.get(index) instanceof Todo;
    }

    private boolean checkIfEvent(int index, List<Task> taskList) {
        return taskList.get(index) instanceof Event;
    }

    private boolean validateIndex(List<Task> taskList, int index) {
        return index >= 0 && index <= taskList.size() - 1;
    }
}
