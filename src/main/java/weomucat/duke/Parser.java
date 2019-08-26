package weomucat.duke;

import java.util.*;

import static weomucat.duke.command.Command.PARAMETER_DEFAULT;

public class Parser {
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
				out.put(param, String.join(" ", line));

				// Set to next parameter.
				param = token;
				line.clear();
			} else {
				line.add(token);
			}
		}

		// Put last (param, line) into out.
		out.put(param, String.join(" ", line));

		return out;
	}
}
