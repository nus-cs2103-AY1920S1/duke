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
    /**
     * Update command action that performs updating the task in the task list.
     * @param taskList list of tasks (Array List).
     * @param input raw user input after the command action word 'update'.
     * @param storage Storage unit which handles saving and loading the task list from duke.txt.
     * @throws DukeException handles catching empty description/time.
     */
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
            boolean isEmpty = checkEmpty(inputs);
            if (isValidIndex && !isEmpty) {
                //validate Todo or Deadline/Event
                boolean isTodo = checkIfTodo(index, taskList);
                boolean isEvent = checkIfEvent(index, taskList);
                if (isTodo) {
                    taskList = handleTodoUpdate(inputs, index, taskList);
                } else if (isEvent) {
                    taskList = handleEventUpdate(inputs, index, taskList);
                } else {
                    taskList = handleDeadlineUpdate(inputs, index, taskList);
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
            throws ParseException, DukeException {
        String[] descAndTime = inputs[1].split(" /at ", 2);
        int arrayLength = descAndTime.length;
        if (arrayLength == 1) {
            //update description only
            String newDescription = descAndTime[0];
            Event updatedEvent = (Event) taskList.get(index);
            updatedEvent.setDescription(newDescription);
            taskList.set(index, updatedEvent);
        } else if (arrayLength == 2) {
            //update both desc and time
            String updatedDescription = descAndTime[0];
            String updatedTime = descAndTime[1];
            taskList.set(index, new Event(updatedDescription, updatedTime));
        } else {
            throw new DukeException("You entered empty update fields.. "
                    + "please enter description/time to update!");
        }
        return taskList;
    }

    private List<Task> handleDeadlineUpdate(String[] inputs, int index, List<Task> taskList)
            throws ParseException, DukeException {
        String[] descAndTime = inputs[1].split(" /by ", 2);
        int arrayLength = descAndTime.length;
        if (arrayLength == 1) {
            //only updates description.
            String newDescription = descAndTime[0];
            Deadline deadline = (Deadline) taskList.get(index);
            deadline.setDescription(newDescription);
            taskList.set(index, deadline);
        } else if (arrayLength == 2) {
            String description = descAndTime[0];
            String time = descAndTime[1];
            taskList.set(index, new Deadline(description, time));
        } else {
            throw new DukeException("You entered empty update fields.. "
                    + "please enter description/time to update!");
        }
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

    private boolean checkEmpty(String[] inputs) {
        return inputs.length == 1;
    }
}
