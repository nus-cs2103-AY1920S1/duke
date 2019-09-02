import java.util.Date;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private CommandType commandType;
    private boolean isDone;
    private String description;
    private String date;

    public Parser(String fullCommand) {
        System.out.println(fullCommand);
        Pattern command_format = Pattern.compile("(?<commandWord>\\w+)"
                + "\\s*(?<completionstatus>(\\[[✗✓]\\])?)"
                + " (?<description>([\\w\\s\\d+]+)?)"
                + "(?:(/by|/at))?(?<date>([\\w\\s\\d+]+)?)");
        Matcher matcher = command_format.matcher(fullCommand);
        if (!matcher.find()) {
            System.out.println("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                    "    ____________________________________________________________");
        }
        System.out.println(matcher.group("commandWord")+matcher.group("completionstatus")+matcher.group("description"));
        switch (matcher.group("commandWord")) {
        case "list":
            commandType = CommandType.LIST;
            break;
        case "todo":
            commandType = CommandType.ADDTODO;
            break;
        case "deadline":
            commandType = CommandType.ADDDEADLINE;
            break;
        case "event":
            commandType = CommandType.ADDEVENT;
            break;
        case "delete":
            commandType = CommandType.DELETE;
            break;
        case "done":
            commandType = CommandType.COMPLETE;
            break;
        default:
            System.out.println("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                    "    ____________________________________________________________");
        }
        isDone = matcher.group("completionstatus").equals("[✓]");
        description = matcher.group("description").trim();
        if (!matcher.group("date").isEmpty()) {
            date = matcher.group("date").trim();
        }

    }

    public CommandType getCommandType() {
        return commandType;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }
}

