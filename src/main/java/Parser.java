import java.io.FileWriter;
import java.io.IOException;

public class Parser {

    String input;

    public Parser(String input) {
        this.input = input;
    }

    public String getCommand() {
        String[] inputStringArr = input.split(" ");
        String command = inputStringArr[0];
        return command;
    }
}
