package weomucat.duke;

import java.util.*;

public class Parser {
    // Default parameter is ""
    public static final String PARAMETER_DEFAULT = "";

    private Scanner scanner;

    public Parser(String line) {
        this.scanner = new Scanner(line);
    }

    public String nextCommand() {
        if (this.scanner.hasNext()) {
            return this.scanner.next();
        } else {
            return "";
        }
    }

    public HashMap<String, String> nextParameters(String... parameters) {
        // Put parameters into a HashSet for O(1) lookup
        HashSet<String> in = new HashSet<>(Arrays.asList(parameters));
        HashMap<String, String> out = new HashMap<>();

        // Current parameter
        String param = PARAMETER_DEFAULT;
        ArrayList<String> line = new ArrayList<>();

        while (this.scanner.hasNext()) {
            String token = this.scanner.next();

            // Put (param, line) into out, if current token is a parameter
            if (in.contains(token)) {

                // Ensure line has at least one word.
                if (line.size() != 0) {
                    out.put(param, String.join(" ", line));
                }

                // Set to next parameter.
                param = token;
                line.clear();
            } else {
                line.add(token);
            }
        }

        // Put last (param, line) into out.
        // Ensure line has at least one word.
        if (line.size() != 0) {
            out.put(param, String.join(" ", line));
        }

        return out;
    }
}
