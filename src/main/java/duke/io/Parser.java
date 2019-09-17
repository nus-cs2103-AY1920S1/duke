package duke.io;

import duke.actionstack.DukeActionStack;
import duke.command.AddEvent;
import duke.command.Clear;
import duke.command.Delete;
import duke.command.AddDeadline;
import duke.command.AddTodo;
import duke.command.ListTasks;
import duke.command.Done;
import duke.command.Find;
import duke.exception.DukeDuplicateTaskException;
import duke.exception.DukeIllegalActionException;
import duke.exception.DukeIllegalDescriptionException;

import java.io.FileNotFoundException;


/**
 * Makes sense of user's input and react in accordance.
 */
public class Parser {

    /**
     * Parses user command.
     * @param act     keyword indicating the intended operation from user input
     * @param storage storage object to interact with
     * @throws FileNotFoundException FileNotFoundException
     * @throws DukeIllegalDescriptionException DukeIllegalDescriptionException
     * @throws DukeIllegalActionException DukeIllegalActionException
     */
    public static String parse(String act, Storage storage) throws FileNotFoundException,
            DukeIllegalDescriptionException, DukeIllegalActionException, DukeDuplicateTaskException {
        String response = "";
        if (DuplicateChecker.checkDuplication(act)) {
            throw new DukeDuplicateTaskException();
        }
        try {
            switch (Action.valueOf(act.split(" ")[0].toUpperCase())) {
            case LIST:
                response = ListTasks.listTasks();
                break;
            case DONE:
                response = Done.done(act, storage);
                break;
            case TODO:
                response = AddTodo.addTodo(act, storage);
                break;
            case DEADLINE:
                response = AddDeadline.addDeadline(act, storage);
                break;
            case EVENT:
                response = AddEvent.addEvent(act, storage);
                break;
            case DELETE:
                response = Delete.delete(act, storage);
                break;
            case FIND:
                response = Find.find(act, storage);
                break;
            case CLEAR:
                response = Clear.clear();
                break;
            default:
                response = "No command detected!";
                break;
            }
            DukeActionStack.pushAction(act);
        } catch (IllegalArgumentException e) {
            throw new DukeIllegalActionException();
        }
        return response;
    }
}

