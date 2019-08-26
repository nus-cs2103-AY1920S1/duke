public class Parser {
    public static Command parse(String rawCommand) throws DukeException {
        String[] words = rawCommand.split(" ");
        switch (words[0]) {
        case "todo":
            return new AddCommand("todo", rawCommand.substring(5));
        case "deadline":
        case "event":
            StringBuilder argumentBuilder = new StringBuilder();
            String option = "";
            StringBuilder optionArgumentBuilder = new StringBuilder();
            boolean isOptionArgument = false;
            for (int i = 1; i < words.length; i++) {
                if (words[i].charAt(0) == '/') {
                    option = words[i].substring(1);
                    isOptionArgument = true;
                } else if (!isOptionArgument) {
                    argumentBuilder.append((argumentBuilder.length() == 0 ? "" : " ") + words[i]);
                } else {
                    optionArgumentBuilder.append((optionArgumentBuilder.length() == 0 ? "" : " ") + words[i]);
                }
            }
            return new AddCommand(words[0], argumentBuilder.toString(), option, optionArgumentBuilder.toString());
        case "list":
            return new ListCommand();
        case "delete":
            return new DeleteCommand(Integer.parseInt(rawCommand.substring(7)));
        case "done":
            return new DoneCommand(Integer.parseInt(rawCommand.substring(5)));
        case "bye":
            return new ExitCommand();
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
