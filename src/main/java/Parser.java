import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Parser {

    Scanner sc = new Scanner(System.in);

    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand.equals("bye")) {
            return new CommandExit(fullCommand);
        } else if (fullCommand.split(" ")[0].equals("delete")) {
            return new CommandDel(Integer.parseInt(fullCommand.split(" ")[1]));
        } else if (fullCommand.split(" ")[0].equals("list")) {
            return new CommandList();
        } else if (fullCommand.split(" ")[0].equals("done")) {
            return new CommandDone(Integer.parseInt(fullCommand.split(" ")[1]));
        } else if (fullCommand.split(" ")[0].equals("todo") ||
                    fullCommand.split(" ")[0].equals("deadline") ||
                    fullCommand.split(" ")[0].equals("event")) {
            return new CommandAdd(fullCommand);
        } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}


// IDEA IS LIKE
// 1. Load from storage
// 2. UI reads lines
// 3. command = parse(string)
// 4. command.execute()
// 5. calls other classes to do the changes as well as print, save