package seedu.duke;

public class Parser {

    public static String[] parseCommand(String input) {
        String[] inputs = input.split(" ", 2);
        return inputs;
    }

}
