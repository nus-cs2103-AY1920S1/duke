package main;

import command.ByeCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.DoneCommand;
import command.EventCommand;
import command.ListCommand;
import command.TodoCommand;
import command.UnknownCommand;
import exception.DeleteException;
import exception.DoneException;
import exception.DukeException;
import exception.TaskException;

public class Parser {
    //private Scanner sc;
    public Parser() {
        //this.sc = new Scanner(System.in);
    }

    private String[] getEventDetails(String[] command) throws TaskException {
        if (command.length <= 1) {
            throw new TaskException();
        }
        String toReturn = "";
        for (int i = 1; i < command.length; i++) {
            toReturn += command[i] + " ";
        }

        return toReturn.split(" /at ");
    }

    private String[] getDeadlineDetails(String[] command) throws TaskException {
        if (command.length <= 1) {
            throw new TaskException();
        }
        String toReturn = "";
        for (int i = 1; i < command.length; i++) {
            toReturn += command[i] + " ";
        }

        return toReturn.split(" /by ");
    }



    public Command parse(String commandLine) {
        String[] nextCommand = commandLine.split(" ");
        switch (nextCommand[0]) {
        case "bye":
            return new ByeCommand();

        case "list":
            return new ListCommand();

        case "done":
            try {
                if (nextCommand.length <= 1) {
                    throw new DoneException();
                } else {
                    return new DoneCommand(Integer.valueOf(nextCommand[1]) - 1);
                }
            } catch (DukeException dukeExp) {
                System.err.println(dukeExp);
            }
            break;

        case "todo":
            try {
                if (nextCommand.length <= 1) {
                    throw new TaskException();
                }
                String todoName = "";
                for (int i = 1; i < nextCommand.length; i++) {
                    todoName += nextCommand[i] + " ";
                }

                return new TodoCommand(todoName);
            } catch (TaskException toEx) {
                System.err.println(toEx);
            }
            break;
        case "event":
            try {
                String[] eventDetails = getEventDetails(nextCommand);

                return new EventCommand(eventDetails[0], eventDetails[1]);

            } catch (TaskException evEx) {
                System.err.println(evEx);
            }
            break;

        case "deadline":
            try {
                String[] deadlineDetails = getDeadlineDetails(nextCommand);

                return new DeadlineCommand(deadlineDetails[0], deadlineDetails[1]);

            } catch (TaskException evEx) {
                System.err.println(evEx);
            }
            break;

        case "delete":
            try {
                if (nextCommand.length <= 1) {
                    throw new DeleteException();
                } else {
                    int index = Integer.parseInt(nextCommand[1]);

                    return new DeleteCommand(index);
                }
            } catch (DeleteException deEx) {
                System.err.println(deEx);
            }
            break;


        default:
            break;
        }

        return new UnknownCommand();
    }
}

