/*
This class deals with making sense of the user command.
 */
public class Parser {

    public Parser() {}

    /*
    This method generates the corresponding command according to the entire String that has been taken in.
     */
    public static Command parse(String c) throws DukeException {
        String[] comm = c.split(" ");
        String key = comm[0];
        if (key.equals("delete")) {
            //assumption: after delete it is always numeric
            return new DeleteCommand(Integer.parseInt(comm[1]));
        } else if (key.equals("done")) {
            //assumption: after delete it is always numeric
            return new DoneCommand(Integer.parseInt(comm[1]));
        } else if (key.equals("list")) {
            return new ListCommand();
        } else if (key.equals("bye")) {
            return new ExitCommand();
        } else if (comm[0].equals("todo") || comm[0].equals("deadline") || comm[0].equals("event")) {
            return new AddCommand(comm);
        } else {
            throw new DukeException("Oops! I'm sorry, but I don't know what that means :(");
        }
    }
}
