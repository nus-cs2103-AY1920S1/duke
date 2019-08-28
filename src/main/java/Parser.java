import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Parser class for parsing strings into arguments.
 */
public class Parser {
    public static String[] checkRegex = {
            "bye", // bye
            "list", // list
            "todo .+", // todo
            "deadline .+? /by .+?", // deadline
            "event .+? /at .+?", // event
    };

    /**
     * Parse string into arguments
     * @param input String
     * @return arguments
     */
    public static String[] returnArgs(String input) {
        Scanner sc = new Scanner(input).useDelimiter("(?<= )");
        List<String> ar = new ArrayList<>();
        String first = sc.next().trim();
        ar.add(first);
        switch (first){
        case "deadline":
            sc.useDelimiter(" /by ");
            break;
        case "event":
            sc.useDelimiter(" /at ");
            break;
        default:
            sc.useDelimiter("^$");
            break;
        }
        while(sc.hasNext()){
            ar.add(sc.next());
        }
        String[] rtv = new String[ar.size()];
        ar.toArray(rtv);
        return rtv;
    }

    /**
     * Check if command is valid
     * @param input command
     * @return if command is valid
     * @throws DukeException
     */
    @Deprecated
    public static boolean checkValidCommand(String input) throws DukeException {
        for(String regex: checkRegex){
            if(input.matches(regex)){
                return true;
            }
        }
        return false;
    }
}