import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Todo extends Task {
    private static Pattern PAT = Pattern.compile("(.*)");
    
    //@@author Parcly-Taxel
    /**
     * Initialises a Todo from its description.
     */
    public Todo(String desc) {
        super(desc);
    }
    
    /**
     * Parses the given line and returns a Todo constructed from it.
     */
    public static Todo parse(String line) {
        Matcher m = PAT.matcher(line);
        if (!m.matches()) {
            return null;
        }
        return new Todo(m.group(1));
    }
    
    /**
     * Returns a string representation of this Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
