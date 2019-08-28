public class Parser {

    public Command parse(String command) {
        String commandType = command.split(" ")[0];
        String instruction = "";
        String date = "";

        if(commandType.equals("todo") || commandType.equals("delete") || commandType.equals("done")) {
            instruction = command.substring(command.indexOf(" ") + 1);
        } else if(commandType.equals("deadline") || commandType.equals("event")) {
            if(command.contains("/")) {
                instruction = command.substring(command.indexOf(" ") + 1, command.indexOf("/") - 1);
                date = command.substring(command.indexOf("/") + 4);
            }
        }

        return new Command(commandType, instruction, date);
    }
}
