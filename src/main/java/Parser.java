public class Parser {
    public static Command parse(String[] inputParts) {
        switch (inputParts[0]) {
        case "bye":
            return new Command(inputParts, 0);
        case "list":
            return new Command(inputParts, 1);
        case "done":
            return new Command(inputParts, 2);
        case "delete":
            return new Command(inputParts, 3);
        case "find":
            return new Command(inputParts, 4);
        }
        return new Command(inputParts, 5);
    }
}
