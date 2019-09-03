public class Parser {
    private String[] inputArr;

    public Parser(String input) {
        this.inputArr = input.split(" ", 2);
    }

    public String[] getInputArr() {
        return inputArr;
    }

    public String getCommand() {
        return inputArr[0];
    }

    public int getPointer() {
        return Integer.parseInt(inputArr[1]);
    }

    public String getDetail() {
        if(inputArr.length > 1) {
            return inputArr[1];
        } else {
            return "";
        }
    }

    public static Command parse(String command, String commandDetails, String INDENT) {
        if(command.equals("bye")) {
            return new ExitCommand(command, commandDetails, INDENT);
        } else if(command.equals("list")) {
            return new ListCommand(command, commandDetails, INDENT);
        } else if(command.equals("done")) {
            return new DoneCommand(command, commandDetails, INDENT);
        } else if(command.equals("delete")) {
            return new DeleteCommand(command, commandDetails, INDENT);
        } else {
            return new AddCommand(command, commandDetails, INDENT);
        }
    }
}
