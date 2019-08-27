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
            case "list":
                return new ListCommand();
            case "bye":
                return new ByeCommand();
            default:
                return new Command();
        }
    }
}