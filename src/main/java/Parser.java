import java.util.Scanner;

public class Parser {
    public Command parse(String userInput) {
        String[] separatedInputs = userInput.trim().split("\\s+");
        switch (separatedInputs[0].toLowerCase()) {
        case "bye":
            return new ExitCommand();
        case "done":
            //fallthrough
        case "delete":
            Scanner s = new Scanner(separatedInputs[1]);
            if (separatedInputs.length > 2 || !s.hasNextInt()) {
                return new IncorrectCommand();
            }
            if (separatedInputs[0].toLowerCase().equals("done")) {
                return new DoneCommand(s.nextInt() - 1);
            } else {
                return new DeleteCommand(s.nextInt() - 1);
            }
        }

        return new IncorrectCommand();
    }
}
