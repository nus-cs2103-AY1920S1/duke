package duke.parser;


import duke.command.*;

/**
 * This is the parser class. It parses each line of instruction and invokes the respective commands.
 */
public class Parser {

    public Parser(){}

    public static Command parse(String userInput) {

        String[] input = userInput.split(" ");
        String instruction = input[0];
        StringBuilder stringBuilder = new StringBuilder();

        switch(instruction) {
            case "todo": case "event": case "deadline":
                for (int i = 1; i < input.length; i++) {
                    stringBuilder.append(input[i]);
                    stringBuilder.append(" ");
                }
                String description = stringBuilder.toString().trim();
                return new AddTaskCommand(instruction, description);
            case "delete":
                int indexToDelete = Integer.parseInt(input[1]);
                return new DeleteCommand(indexToDelete);
            case "done":
                int indexToDone = Integer.parseInt(input[1]);
                return new DoneCommand(indexToDone);
            case "list":
                return new ListCommand();
            case "bye":
                return new ByeCommand();
            case "find":
                String word = input[1];
                return new FindCommand(word);
            default:
                return new Command();
        }
    }
}