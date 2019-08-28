import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Parser {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HHmm");

    public static Command parse(String command) throws DukeException{
            switch (command) {
                case ("list"):
                    return new ListCommand();

                case ("bye"):
                    return new ExitCommand();

                case ("done"):
                    return new DoneCommand();

                case ("delete"):
                    return new DeleteCommand();

                case ("deadline"):
                    return new DeadlineCommand();

                case ("todo"):
                    return new TodoCommand();

                case ("event"):
                    return new EventCommand();

                default:
                    throw new DukeException("☹OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
}
