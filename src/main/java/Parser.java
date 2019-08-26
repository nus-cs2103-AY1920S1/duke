import java.util.Scanner;

public class Parser {
    public Command parse(String userInput) {
        String[] separatedInputs = userInput.trim().split("\\s+");
        switch (separatedInputs[0].toLowerCase()) {
        case "bye":
            return new ExitCommand();
        case "done":
            Scanner s = new Scanner(separatedInputs[1]);
            if (separatedInputs.length > 2 || !s.hasNextInt()) {
                return new IncorrectCommand();
            }
            return new DoneCommand(s.nextInt() - 1);
        }
        return new IncorrectCommand();
    }
}
