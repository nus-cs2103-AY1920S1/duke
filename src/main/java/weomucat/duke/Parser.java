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
        return this.scanner.next();
    }

    public int nextInt() {
        return this.scanner.nextInt();
    }

    public HashMap<String, String> nextParameters(String... parameters) {
        // Put parameters into a HashSet for O(1) lookup
        HashSet<String> in = new HashSet<>(Arrays.asList(parameters));
        HashMap<String, String> out = new HashMap<>();

        String param = PARAMETER_DEFAULT;
        ArrayList<String> line = new ArrayList<>();
        while (this.scanner.hasNext()) {
            String token = this.scanner.next();

            // Put (param, line) to out if current token is a parameter
            if (in.contains(token)) {
                out.put(param, String.join(" ", line));
                param = token;
                line.clear();
            } else {
                line.add(token);
            }
        }

        out.put(param, String.join(" ", line));
        return out;
    }
}
