package duke;

import java.lang.StringBuilder;
import java.util.List;

/**
 * Represents a formatted message to be printed by Duke. A <code>Message</code>
 * corresponds to a list of strings to be formatted between two horizontal lines,
 * with each string concatenated with 4 white spaces at the front.
 */
public class Message {
    private List<String> strings;

    public Message(List<String> strings) {
        this.strings = strings;
    }

    /**
     * Returns string representation of a Message.
     * @return string representation of a Message.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (String string : strings) {
            if (isFirst) {
                isFirst = false;
                sb.append(string);
            } else {
                sb.append(String.format("\n%s", string));
            }
        }
        return sb.toString();
    }
}
