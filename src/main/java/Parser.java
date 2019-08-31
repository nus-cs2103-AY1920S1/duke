import java.util.Date;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private CommandType commandType;
    private boolean isDone;
    private String description;
    private String date;
    // include a /s* after first \s*
    private Pattern Command_format= Pattern.compile("(?<commandWord>\\w+)"
            +" (?:(\\[))?(?<arguments>(?:[\\w+])?)(?:(\\])?)"
            +"(?<description>([\\w\\s]+))"
            +" (?:(/by|/at))?(?<date>(?:[\\w\\s\\d+]+)?)");
    public Parser(){
    }

    public Parser parse (String fullCommand){
        Matcher matcher = Command_format.matcher(fullCommand);
        if(!matcher.find()){
            System.out.println("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                    "    ____________________________________________________________");
        }
        switch(matcher.group("commandWord")){
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

        }


    }
}