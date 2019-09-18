package duke.directprocessor;

import duke.KeyWord;
import duke.DukeException;
import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FakeCommand;
import duke.commands.FindCommand;
import duke.commands.FinishCommand;
import duke.commands.ListCommand;
import duke.commands.SlotCommand;
import duke.commands.SpecifyCommand;
import duke.tasks.TaskType;

/**
 * This is a class that recognizes the user's input and calls the corresponding command.
 * It only recognizes the first word of the user input and key words like "/at", "/by".
 * All of its methods are static, so there is no necessity to initialize this class.
 */
public class Parser {

    /**
     * This method recognizes the user input and generate the corresponding commands.
     *
     * @param s The user's input as a string.
     * @return The recognized command.
     * @throws DukeException If the command is invalid.
     */
    public static Command parse(String s) throws DukeException {
        s = s.trim();
        String[] splitInput = s.split("\\s+", 2);
        if (splitInput.length == 0) {
            throw new DukeException("The command cannot be empty.");
        }
        KeyWord keyWord;
        try {
            keyWord = KeyWord.valueOf(splitInput[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            return fakeCommand();
        }
        try {
            switch (keyWord) {
            case LIST: return listCommand();
            case BYE: return exitCommand();
            case TODO: return todoCommand(splitInput[1]);
            case EVENT: return eventCommand(splitInput[1]);
            case DEADLINE: return deadlineCommand(splitInput[1]);
            case DONE: return finishCommand(splitInput[1]);
            case DELETE: return deleteCommand(splitInput[1]);
            case FIND: return findCommand(splitInput[1]);
            case SLOT: return slotCommand(splitInput[1]);
            case SPECIFY: return specifyCommand(splitInput[1]);
            default: return fakeCommand();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("This keyword requires further specification.");
        }
    }

    private static Command listCommand() {
        return new ListCommand();
    }

    private static Command exitCommand() {
        return new ExitCommand();
    }

    private static Command todoCommand(String taskName) {
        return new AddCommand(TaskType.T, taskName.trim());
    }

    private static Command eventCommand(String eventInfo) throws DukeException {
        String[] nameAndTime = eventInfo.split("/at");
        if (nameAndTime.length == 1) {
            throw new DukeException("Please indicate the event time.");
        }
        if (nameAndTime.length > 2) {
            throw new DukeException("Please do not indicate multiple event times.");
        }
        String eventName = nameAndTime[0].trim();
        String eventTime = nameAndTime[1].trim();
        return new AddCommand(TaskType.E, eventName, eventTime);
    }

    private static Command deadlineCommand(String deadlineInfo) throws DukeException {
        String[] nameAndTime = deadlineInfo.split("/by");
        if (nameAndTime.length == 1) {
            throw new DukeException("Please specify the deadline of the task.");
        }
        if (nameAndTime.length > 2) {
            throw new DukeException("Please do not specify multiple deadlines.");
        }
        String deadlineName = nameAndTime[0].trim();
        String deadlineTime = nameAndTime[1].trim();
        return new AddCommand(TaskType.D, deadlineName, deadlineTime);
    }

    private static Command finishCommand(String finishPosition) {
        return new FinishCommand(Integer.parseInt(finishPosition));
    }

    private static Command deleteCommand(String deletePos) {
        return new DeleteCommand(Integer.parseInt(deletePos));
    }

    private static Command findCommand(String targetString) {
        return new FindCommand(targetString);
    }

    private static Command slotCommand(String slotInfo) throws DukeException {
        String[] splitSlotInfo = slotInfo.split("\\s+", 2);
        try {
            splitSlotInfo[0] = splitSlotInfo[0].trim();
            splitSlotInfo[1] = splitSlotInfo[1].trim();
            int slotPosition = Integer.parseInt(splitSlotInfo[0]);
            String newSlot = splitSlotInfo[1];
            return new SlotCommand(slotPosition, newSlot);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please specify which event do you want to add a new slot.");
        }
    }

    private static Command specifyCommand(String specifyInfo) throws DukeException {
        String[] splitSpecifyInfo = specifyInfo.split("\\s+", 2);
        try {
            splitSpecifyInfo[0] = splitSpecifyInfo[0].trim();
            splitSpecifyInfo[1] = splitSpecifyInfo[1].trim();
            int specifyPosition = Integer.parseInt(splitSpecifyInfo[0]);
            String specifiedSlot = splitSpecifyInfo[1];
            return new SpecifyCommand(specifyPosition, specifiedSlot);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please specify which event do you want to specify the slot.");
        }
    }

    private static Command fakeCommand() {
        return new FakeCommand();
    }
}
