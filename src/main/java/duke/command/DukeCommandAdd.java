package duke.command;

import duke.util.DukeStorage;
import duke.util.DukeTaskList;
import duke.util.DukeUi;
import duke.util.DukeParser;
import duke.task.DukeTaskDeadline;
import duke.task.DukeTaskEvent;
import duke.task.DukeTaskToDo;
import java.time.format.DateTimeParseException;

public class DukeCommandAdd extends DukeCommand {

    String[] inputTokens;

    /**
     * Constructor that takes in the user input split by the " " delimiter into a String[].
     * @param inputTokens User entered line split by a space delimiter.
     */
    public DukeCommandAdd(String[] inputTokens) {
        this.inputTokens = inputTokens;
    }

    /**
     * This method will handle adding of a {@link DukeTaskToDo} into {@link DukeTaskList} list of {@link duke.task.DukeTask}.
     * @param tasks Instance of {@link DukeTaskList} which contains an existing list of {@link duke.task.DukeTask}.
     * @param ui Instance of {@link DukeUi} which will show output to the user.
     * @param storage Instance of {@link DukeStorage} which will save the {@link DukeTaskList} to the hard disk.
     */
    private void handleTodo(DukeTaskList tasks, DukeUi ui, DukeStorage storage) {
        String todoTaskName = DukeParser.concatStringTokens(inputTokens, 1, (inputTokens.length - 1));
        DukeTaskToDo dukeToDo = new DukeTaskToDo(todoTaskName);
        tasks.addToDukeTasks(dukeToDo, ui, storage);
    }

    /**
     * This method will handle adding of a {@link DukeTaskDeadline} into {@link DukeTaskList} list of {@link duke.task.DukeTask}.
     * @param tasks Instance of {@link DukeTaskList} which contains an existing list of {@link duke.task.DukeTask}.
     * @param ui Instance of {@link DukeUi} which will show output to the user.
     * @param storage Instance of {@link DukeStorage} which will save the {@link DukeTaskList} to the hard disk.
     */
    private void handleDeadline(DukeTaskList tasks, DukeUi ui, DukeStorage storage) {
        int deadlineParameterIndex = DukeParser.getInputFlagParameterStartingIndex(inputTokens, "/by");
        if (deadlineParameterIndex == -1 || deadlineParameterIndex == inputTokens.length) {
            ui.displayMissingDeadlineParam();
        } else {
            String deadlineTaskName = DukeParser.concatStringTokens(inputTokens, 1,
                    (deadlineParameterIndex - 2));
            String deadlineParameterString = DukeParser.concatStringTokens(inputTokens, deadlineParameterIndex,
                    (inputTokens.length - 1));
            try {
                DukeTaskDeadline dukeDeadline = new DukeTaskDeadline(deadlineTaskName,
                        DukeParser.formatDate(deadlineParameterString));
                tasks.addToDukeTasks(dukeDeadline, ui, storage);
            } catch (DateTimeParseException ex) {
                ui.displayInvalidDateFormat();
            }
        }
    }

    /**
     * This method will handle adding of a {@link DukeTaskEvent} into {@link DukeTaskList} list of {@link duke.task.DukeTask}.
     * @param tasks Instance of {@link DukeTaskList} which contains an existing list of {@link duke.task.DukeTask}.
     * @param ui Instance of {@link DukeUi} which will show output to the user.
     * @param storage Instance of {@link DukeStorage} which will save the {@link DukeTaskList} to the hard disk.
     */
    private void handleEvent(DukeTaskList tasks, DukeUi ui, DukeStorage storage) {
        int eventParameterIndex = DukeParser.getInputFlagParameterStartingIndex(inputTokens, "/at");
        if (eventParameterIndex == -1 || eventParameterIndex == inputTokens.length) {
            ui.displayMissingEventParam();
        } else {
            String eventTaskName = DukeParser.concatStringTokens(inputTokens, 1,
                    (eventParameterIndex - 2));
            String eventParameterString = DukeParser.concatStringTokens(inputTokens, eventParameterIndex,
                    (inputTokens.length - 1));
            DukeTaskEvent dukeEvent = new DukeTaskEvent(eventTaskName, eventParameterString);
            tasks.addToDukeTasks(dukeEvent, ui, storage);
        }
    }

    /**
     * This method will add {@link duke.task.DukeTask} according to the command name.
     * @param tasks Instance of {@link DukeTaskList} which contains an existing list of {@link duke.task.DukeTask}.
     * @param ui Instance of {@link DukeUi} which will show output to the user.
     * @param storage Instance of {@link DukeStorage} which will save the {@link DukeTaskList} to the hard disk.
     */
    @Override
    public void execute(DukeTaskList tasks, DukeUi ui, DukeStorage storage) {
        if (inputTokens.length == 1) {
            ui.displayEmptyDescriptionError();
        } else {
            if (inputTokens[0].equals("todo")) {
                handleTodo(tasks, ui, storage);
            } else if (inputTokens[0].equals("deadline")) {
                handleDeadline(tasks, ui, storage);
            } else if (inputTokens[0].equals("event")) {
                handleEvent(tasks, ui, storage);
            }
        }
    }
}
