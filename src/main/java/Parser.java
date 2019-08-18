import java.util.ArrayList;

public class Parser {
	public Pair<Command, ArrayList<String>> parse(String input) throws DukeException {
		String[] split = input.split(" ", 2);
		Command command = Command.fromString(split[0]); // throws exception if not a valid command

		// if the command is a valid command, try to extract parameters from input
		ArrayList<String> parameters = new ArrayList<>();
		if (command.parametersExpected == 0) {
			return new Pair<Command, ArrayList<String>>(command, parameters);
		} else try {
			for (String delim : command.delimiters) { // for each delimiter, try to extract the parameter before it
				split = split[1].split(delim, 2); // throws ArrayIndexOOB exception if parameter is not provided
				String param;
				if ((param = split[0].trim()).length() > 0) {
					parameters.add(param);
				} else {
					throw new DukeMissingParameterException(command);
				}
			}
			if (split[1].trim().length() > 0) {
				parameters.add(split[1].trim());
				// return the pair if no errors in input
				return new Pair<Command, ArrayList<String>>(command, parameters);
			} else {
				throw new DukeMissingParameterException(command);
			}
		} catch (ArrayIndexOutOfBoundsException ex) {
			throw new DukeMissingParameterException(command);
		}

	}
}
