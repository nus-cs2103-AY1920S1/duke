package parser;

import command.*;
import exception.DukeException;

public class Parser {

    public static Command parse(String instruction) throws DukeException {
        String[] word_Arr = instruction.split(" ", 2);
        FullCommand command = FullCommand.getByName(word_Arr[0]);
        try {
            switch (command) {
                case LIST:
                    return new ListCommand();
                case BYE:
                    return new ExitCommand();
                case DONE:
                    return new EditCommand(Integer.parseInt(word_Arr[1]) - 1);
                case DELETE:
                    return new DeleteCommand(Integer.parseInt(word_Arr[1]) - 1);
                default:
                    if (word_Arr[1].equals("")){
                        throw new DukeException("");
                    }
                    return new AddCommand(command, word_Arr[1]);
            }
        } catch (IndexOutOfBoundsException | DukeException e) {
            throw new DukeException("Give instructions in the format: (instruction type) (details)");
        }
    }
}
