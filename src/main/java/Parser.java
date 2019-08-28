import java.util.Date;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class Parser {
    private CommandType commandType;
    private boolean isDone;
    private String description;
    private String date;
    private Pattern Command_format= Pattern.compile("(?<commandWord>\\w+)"
            +" (?:(\\[))?(?<arguments>(?:[\\w+])?)(?:(\\])?)"
            +"(?<description>([\\w\\s]+))"
            +" (?:(/by|/at))?(?<date>(?:[\\w\\s\\d+]+)?)");
    public Parser(){
    }

    public Parser parse (String fullCommand){
        if

    }
}