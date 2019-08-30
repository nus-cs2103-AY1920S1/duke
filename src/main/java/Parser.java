package duke.component;

import duke.exception.*;
import duke.command.*;

public class Parser {


    public Parser() {
    }


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
                return new TodoCommand(false, input.substring(5));
            } catch (StringIndexOutOfBoundsException e) {
                throw new EmptyDescException("todo");
            }


        } else if (input.contains("deadline")) {

            try {

                String[] parts = input.split("/by");

                return new DeadlineCommand(false, parts[0].substring(9), parts[1]);
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                throw new EmptyDescException("deadline");
            }

        } else if (input.contains("event")) {
            try {

                String[] parts = input.split("/at");

                return new EventCommand(false, parts[0].substring(5), parts[1]);
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






