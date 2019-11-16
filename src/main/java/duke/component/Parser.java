package duke.component;

//imports

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.EmptyDescException;
import duke.exception.InvalidArgumentException;

/**
 * Represents a Parser object that parses user inputs.
 * The 'Parser' class supports operators (i) parse out user inputs
 * and (ii) determine the input type, which helps parse out user inputs.
 */
public class Parser {


    /**
     * Parses out user inputs.
     *
     * @param input User input.
     * @return Command Type of command that user input in.
     * @throws DukeException If there is IOException when reading or writing from text file.
     */
    public Command parse(String input) throws DukeException {
        switch (input) {

        case "bye":
            return new ExitCommand();


        case "list":
            return new ListCommand();


        default:
            return determineInputType(input);

        }
    }

    /**
     * Returns Command object based on user input.
     * Helper method to parse(String input) method.
     *
     * @param input User input.
     * @return Command Type o comand summoned by user.
     * @throws DukeException If there is IOException when reading or writing from text file.
     */
    private Command determineInputType(String input) throws DukeException {
        String[] wordArray = this.splitBySpace(input);

        switch (wordArray[0]) {

        case "done":

            return createDoneCommand(input);

        case "todo":

            return createTodoCommand(input);

        case "deadline":

            return createDeadlineCommand(input);

        case "event":

            return createEventCommand(input);

        case "delete":

            return createDeleteCommand(input);

        case "find":

            return createFindCommand(input);


        default:
            throw new InvalidArgumentException();

        }
    }


    private String[] splitBySpace(String input) {
        return input.split("\\s+", -1);
    }


    private DoneCommand createDoneCommand(String input) throws EmptyDescException {
        try {

            int taskNum = Integer.parseInt(input.substring(5));
            DoneCommand resultCommand = new DoneCommand(taskNum);

            //Assert that result is an instance of DoneCommand
            assert resultCommand instanceof DoneCommand : "Result is not an instance of DoneCommand";

            return resultCommand;

        } catch (StringIndexOutOfBoundsException e1) {
            throw new EmptyDescException("done");
        }
    }


    private AddCommand createTodoCommand(String input) throws EmptyDescException {
        try {
            AddCommand resultCommand = new AddCommand("T", false, input.substring(5), null);

            //Assert that result is an instance of DoneCommand
            assert resultCommand instanceof AddCommand : "Result is not an instance of AddCommand";

            return resultCommand;

        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyDescException("todo");
        }

    }


    private AddCommand createDeadlineCommand(String input) throws EmptyDescException {
        try {

            String[] parts = input.split("/by");

            AddCommand resultCommand = new AddCommand("D", false, parts[0].substring(9), parts[1]);

            //Assert that result is an instance of DoneCommand
            assert resultCommand instanceof AddCommand : "Result is not an instance of AddCommand";

            return resultCommand;
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            throw new EmptyDescException("deadline");

        }

    }


    private AddCommand createEventCommand(String input) throws EmptyDescException {
        try {

            String[] parts = input.split("/at");

            AddCommand resultCommand = new AddCommand("E", false, parts[0].substring(5), parts[1]);
            //Assert that result is an instance of DoneCommand
            assert resultCommand instanceof AddCommand : "Result is not an instance of AddCommand";

            return resultCommand;
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            throw new EmptyDescException("event");
        }
    }


    private DeleteCommand createDeleteCommand(String input) throws EmptyDescException {
        int taskNum;
        try {
            taskNum = Integer.parseInt(input.substring(7));
            DeleteCommand resultCommand = new DeleteCommand(taskNum);

            //Assert that result is an instance of DoneCommand
            assert resultCommand instanceof DeleteCommand : "Result is not an instance of AddCommand";

            return resultCommand;

        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyDescException("delete");
        }


    }


    private FindCommand createFindCommand(String input) throws EmptyDescException {
        String keyword;
        try {
            keyword = input.substring(5);
            return new FindCommand(keyword);
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyDescException("find");
        }
    }


}






