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
     * Initialises a new Parser object.
     */
    public Parser() {
    }


    /**
     * Parses out user inputs
     *
     * @param input User input.
     * @return Command Type of command that user input in.
     * @throws DukeException If there is IOException when reading or writing from text file.
     */
    public static Command parse(String input) throws DukeException {
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
    public static Command determineInputType(String input) throws DukeException {
        if (input.contains("done")) {


            try {
                int taskNum = -1;

                taskNum = Integer.parseInt(input.substring(5));
                return new DoneCommand(taskNum);
            } catch (StringIndexOutOfBoundsException e1) {
                throw new EmptyDescException("done");
            }


        } else if (input.contains("todo")) {


            try {
                return new AddCommand("T", false, input.substring(5), null);
            } catch (StringIndexOutOfBoundsException e) {
                throw new EmptyDescException("todo");
            }


        } else if (input.contains("deadline")) {

            try {

                String[] parts = input.split("/by");

                return new AddCommand("D", false, parts[0].substring(9), parts[1]);
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                throw new EmptyDescException("deadline");
            }

        } else if (input.contains("event")) {
            try {

                String[] parts = input.split("/at");

                return new AddCommand("E", false, parts[0].substring(5), parts[1]);
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                throw new EmptyDescException("event");
            }
        } else if (input.contains("delete")) {

            int taskNum;
            try {
                taskNum = Integer.parseInt(input.substring(7));
                return new DeleteCommand(taskNum);

            } catch (StringIndexOutOfBoundsException e) {
                throw new EmptyDescException("delete");
            }


        } else {
            throw new InvalidArgumentException();
        }
    }


}






