package bot.duke.command;

import bot.duke.command.add.AddDeadlineCommand;
import bot.duke.command.add.AddEventCommand;
import bot.duke.command.add.AddTodoCommand;
import bot.duke.command.flow.ExitCommand;
import bot.duke.command.list.FindCommand;
import bot.duke.command.list.ListCommand;
import bot.duke.command.update.DeleteTaskCommand;
import bot.duke.command.update.MakeDoneCommand;
import bot.duke.exception.DukeDeadlineException;
import bot.duke.exception.DukeEventException;
import bot.duke.exception.DukeException;
import bot.duke.exception.DukeFindException;
import bot.duke.exception.DukeTodoException;
import bot.duke.exception.DukeUnknownCommandException;

public class Parser {

    /**
     * Parses a given user input.
     *
     * @param input Given user input
     * @return Command object
     * @throws DukeException If the inputs are incorrect in any way
     */
    public static Command parse(String input) throws DukeException {
        assert !input.equals("");
        if (input.equals("list")) {
            return new ListCommand();
        } else if (input.matches("find.*")) {
            if (input.equals("find")) {
                throw new DukeFindException(DukeFindException.EMPTY_DETAILS_ERROR_MSG);
            } else if (input.charAt(4) == ' ') {
                String detail = input.substring(5);
                if (detail.length() < 1) {
                    throw new DukeFindException(DukeFindException.EMPTY_DETAILS_ERROR_MSG);
                } else {
                    return new FindCommand(detail);
                }
            } else {
                throw new DukeUnknownCommandException();
            }

        } else if (input.matches("todo.*")) {
            if (input.equals("todo")) {
                throw new DukeTodoException();
            } else if (input.charAt(4) == ' ') {
                String detail = input.substring(5);
                if (detail.length() < 1) {
                    throw new DukeTodoException();
                } else {
                    return new AddTodoCommand(detail);
                }
            } else {
                throw new DukeUnknownCommandException();
            }

        } else if (input.matches("deadline.*")) {
            if (input.equals("deadline")) {
                throw new DukeDeadlineException(DukeDeadlineException.EMPTY_DETAILS_ERROR_MSG);
            } else if (input.charAt(8) == ' ') {
                String detail = input.substring(9);
                if (detail.length() < 1) {
                    throw new DukeDeadlineException(DukeDeadlineException.EMPTY_DETAILS_ERROR_MSG);
                } else if (!detail.contains("/by")) {
                    throw new DukeDeadlineException(DukeDeadlineException.FORMAT_ERROR_MSG);
                } else {
                    String[] detailSplit = detail.split(" /by ");
                    return new AddDeadlineCommand(detailSplit[0], detailSplit[1]);
                }
            } else {
                throw new DukeUnknownCommandException();
            }
        } else if (input.matches("event.*")) {
            if (input.equals("event")) {
                throw new DukeEventException(DukeEventException.EMPTY_DETAILS_ERROR_MSG);
            } else if (input.charAt(5) == ' ') {
                String detail = input.substring(6);
                if (detail.length() < 1) {
                    throw new DukeEventException(DukeEventException.EMPTY_DETAILS_ERROR_MSG);
                } else if (!detail.contains("/at") || !detail.contains(" - ")) {
                    throw new DukeEventException(DukeEventException.FORMAT_ERROR_MSG);
                } else {
                    String[] detailSplit = detail.split(" /at ");
                    String[] dateSplit = detailSplit[1].split(" - ");
                    return new AddEventCommand(detailSplit[0], dateSplit[0], dateSplit[1]);
                }
            } else {
                throw new DukeUnknownCommandException();
            }
        } else if (input.matches("done\\s\\d+")) {
            int chosenTaskNo = Integer.parseInt(input.substring(5));
            return new MakeDoneCommand(chosenTaskNo);
        } else if (input.matches("delete\\s\\d+")) {
            int chosenTaskNo = Integer.parseInt(input.substring(7));
            return new DeleteTaskCommand(chosenTaskNo);
        } else if (input.equals("bye")) {
            return new ExitCommand();
        } else {
            throw new DukeUnknownCommandException();
        }
    }

}
