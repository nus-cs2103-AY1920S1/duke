package duke;

import commands.Command;
import commands.DoneCommand;
import commands.ExitCommand;
import commands.ListCommand;
import commands.NoteCommand;
import commands.ErrorCommand;
import commands.PostponeCommand;
import commands.HelpCommand;
import commands.DeleteCommand;
import commands.FindCommand;
import commands.AddCommand;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class Parser {

    /**
     * This method is used to parse the user command the task list from disk
     * and return a Command object for execution.
     *
     * @param fullCommand command which is read as a string by the UI.
     * @return a command which can be executed by application.
     */
    public static Command parse(String fullCommand) {
        String[] input = fullCommand.split(" ", 2);

        try {
            switch (input[0]) {  //checks the first string of user input
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "done": {
                int num = Integer.parseInt(input[1]);
                return new DoneCommand(num);
            }
            case "delete": {
                int num = Integer.parseInt(input[1]);
                return new DeleteCommand(num);
            }
            case "find":
                return new FindCommand(input[1].trim());
            case "help":
                return new HelpCommand();
            case "postpone": {
                String[] arr = input[1].trim().split(" ");
                return new PostponeCommand(arr[0], arr[1], arr[2], arr[3]);
            }
            case "note": {
                String[] arr = input[1].trim().split(" ", 2);
                return new NoteCommand(Integer.parseInt(arr[0]), arr[1]);
            }
            default:
                Task t;
                switch (input[0]) {
                case "todo":
                    t = new Todo(input[1].trim());
                    break;
                case "deadline": {
                    String[] str = input[1].trim().split("/", 2);
                    t = new Deadline(str[0], str[1].substring(3).trim());
                    break;
                }
                case "event": {
                    String[] str = input[1].trim().split("/", 2);
                    t = new Event(str[0], str[1].substring(3).trim());
                    break;
                }
                default:
                    throw new DukeException("Unknown command: " + input[0]);
                }
                assert !t.isDone();
                assert t.getDescription() != null;
                return new AddCommand(t);
            }
        } catch (DukeException e) {
            return new ErrorCommand(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            return new ErrorCommand("Wrong input format!");
        } catch (Exception e) {
            return new ErrorCommand("Me no understand!");
        }

    }
}
