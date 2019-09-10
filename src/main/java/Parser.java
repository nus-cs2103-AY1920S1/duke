public class Parser {

    /**
     * Returns the command given by the user.
     * Accepts the string command given and create a new Comment object
     * depending on the command type.
     * @param command refers to the input by the user
     * @return a new Command object
     */
    public Command parse(String command) {
        String commandType = command.split(" ")[0];
        String instruction = "";
        String date = "";

        if (commandType.equals("todo") || commandType.equals("delete") || commandType.equals("done")
                || commandType.equals("find")) {
            instruction = command.substring(command.indexOf(" ") + 1);
        } else if (commandType.equals("deadline") || commandType.equals("event")) {
            if (command.contains("/")) {
                instruction = command.substring(command.indexOf(" ") + 1, command.indexOf("/") - 1);
                assert command.length() > command.indexOf("/") + 4 : "Incomplete date";
                date = command.substring(command.indexOf("/") + 4);
            }
        }

        return new Command(commandType, instruction, date);
    }
}
