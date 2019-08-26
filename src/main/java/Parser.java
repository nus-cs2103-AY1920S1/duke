public class Parser {

    public static Command parse(String input) throws DukeException {
        String[] inputArr = input.split(" ");

        //Check what type of command is it
        String cmd = inputArr[0];
        Command nextCommand = new ExitCommand(cmd);

        try {
            if (cmd.equals("list")) {
                nextCommand = new ShowCommand(cmd);
            } else if (cmd.equals("todo")) {
                String desc = getDesc(inputArr);
                nextCommand = new AddCommand(cmd, desc);
            } else if (cmd.equals("deadline") | cmd.equals("event")) {
                String desc = getDesc(inputArr);
                String dateTime = getDateTime(inputArr);
                nextCommand = new AddCommand(cmd, desc, dateTime);
            } else if (cmd.equals("done")) {
                String index = inputArr[1];
                nextCommand = new DoneCommand(cmd, Integer.parseInt(index));
            } else if (cmd.equals("delete")) {
                String index = inputArr[1];
                nextCommand = new DeleteCommand(cmd, Integer.parseInt(index));
            } else if (cmd.equals("bye")) {
                //go with default command
            } else {
                //if command can't be recognised
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (IndexOutOfBoundsException e) {
            //catch anything wrong with input fields
            throw new DukeException("☹ Something wrong with input fields! Please try again.");
        }

        //return exit command
        return nextCommand;
    }

    private static String getDesc(String[] inputArr) {
        String desc = "";
        for (int i = 1; i < inputArr.length; i++) {
            if (i == inputArr.length - 1 || inputArr[i + 1].charAt(0) == '/') {
                desc += inputArr[i];
                break;
            } else {
                desc += inputArr[i] + " ";
            }
        }
        return desc;
    }

    private static String getDateTime(String[] inputArr) {
        String dateTime = inputArr[inputArr.length - 2] + " " + inputArr[inputArr.length - 1];
        return dateTime;
    }
}
