package duke.command;

import duke.command.add.AddDeadlineCommand;
import duke.command.add.AddEventCommand;
import duke.command.add.AddTodoCommand;
import duke.command.flow.ExitCommand;
import duke.command.list.ListCommand;
import duke.command.update.DeleteTaskCommand;
import duke.command.update.MakeDoneCommand;
import duke.exception.*;

public class Parser {

    public static Command parse(String input) throws DukeException {
        if (input.equals("list")) {
            return new ListCommand();
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
                throw new DukeDeadlineException("☹ OOPS!!! The description of a deadline cannot be empty.");
            } else if (input.charAt(8) == ' ') {
                String detail = input.substring(9);
                if (detail.length() < 1) {
                    throw new DukeDeadlineException("☹ OOPS!!! The description of a deadline cannot be empty.");
                } else if (!detail.contains("/by")) {
                    throw new DukeDeadlineException("☹ OOPS!!! The format of deadline is wrong!");
                } else {
                    String[] detailSplit = detail.split(" /by ");
                    return new AddDeadlineCommand(detailSplit[0], detailSplit[1]);
                }
            } else {
                throw new DukeUnknownCommandException();
            }
        } else if (input.matches("event.*")) {
            if (input.equals("event")) {
                throw new DukeEventException("☹ OOPS!!! The description of a event cannot be empty.");
            } else if (input.charAt(5) == ' ') {
                String detail = input.substring(6);
                if (detail.length() < 1) {
                    throw new DukeEventException("☹ OOPS!!! The description of a event cannot be empty.");
                } else if (!detail.contains("/at") || !detail.contains(" - ")) {
                    throw new DukeEventException("☹ OOPS!!! The format of event is wrong!");
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
