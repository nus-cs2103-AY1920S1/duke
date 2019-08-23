import java.util.Map;
import java.util.HashMap;
import java.util.regex.Pattern;
public class Command {
	public final String type;
	public final String arguments;
	public final Map<String,String> namedArguments;

	public Command(String type, String arguments, Map<String, String> namedArguments) {
		this.type = type;
		this.arguments = arguments;
		this.namedArguments = namedArguments;
	}

	private static final Pattern switchBoundary = Pattern.compile("\\s+/");
	private static final Pattern cmdBoundary = Pattern.compile("\\s+");

	public static Command parse(String input) {
		input = input.trim();

		//Split on the presence of any switches
		String[] splits = switchBoundary.split(input);
		
		//First split value is "cmd-type args..."
		String[] mainArgs = cmdBoundary.split(splits[0], 2);
		if(mainArgs[0].isEmpty()) {
			return null;
		}

		String type = mainArgs[0];
		//If no arguments, leave as empty string
		String arguments = mainArgs.length > 1 ? mainArgs[1] : "";
		Map<String, String> namedArguments = new HashMap<>();

		if(splits.length > 1) {
			//Each split value is "switch-name args..."
			//with no preceding slash
			for(int i=1;i<splits.length;i++) {
				if(splits[i].isEmpty()) {
					continue;
				}
				//Split at first whitespace
				String[] switchArgs = cmdBoundary.split(splits[i], 2);
				String switchName = switchArgs[0];
				//If no arguments, leave as empty string
				String switchArguments = switchArgs.length > 1 ? switchArgs[1] : "";
				namedArguments.put(switchName, switchArguments);
			}
		}
		return new Command(type, arguments, namedArguments);
	}
}
