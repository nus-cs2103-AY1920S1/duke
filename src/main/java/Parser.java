/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Parser {

    /**
     * Returns the appropriate command for later execution of the command
     * @param fullCommand single line of string from user-input
     * @return appropriate command based on the user-input
     */
    public static Command parse(String fullCommand){
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (fullCommand.substring(0, 4).equals("done")) {
            int do_Index = Integer.parseInt(fullCommand.substring(5));
            return new DoneCommand(do_Index);
        } else if (fullCommand.length() >= 6 && fullCommand.substring(0, 6).equals("delete")) {
            int delete_Index = Integer.parseInt(fullCommand.substring(7));
            return new DeleteCommand(delete_Index);
        } else {
            return new AddCommand(fullCommand);
        }
    }
}
