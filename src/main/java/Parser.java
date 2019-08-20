import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Parser {
	
	public static String parseDateTime(String dateTimeString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
				"dd/MM/yyyy HHmm");
		LocalDateTime dateAndTime = LocalDateTime.parse(dateTimeString, formatter);
		int day = dateAndTime.getDayOfMonth();
		String month = dateAndTime.getMonth().toString();
		int year = dateAndTime.getYear();
		int hour = dateAndTime.getHour();
		int minute = dateAndTime.getMinute();
		StringBuffer dateTime = new StringBuffer();
		dateTime.append(getIntegerOrdinal(day));
		dateTime.append(" of ");
		dateTime.append(month);
		dateTime.append(" ");
		dateTime.append(year);
		dateTime.append(", ");
		if (hour >= 12) {
			hour = hour - 12;
		}
		dateTime.append(hour);
		if (minute != 0) {
			dateTime.append(":");
			dateTime.append(minute);
		}
		if (hour < 12) {
			dateTime.append(" a.m.");
		} else {
			dateTime.append(" p.m.");
		}
		return dateTime.toString();
	}

	public static String getIntegerOrdinal(int integer) {
		int remainderHundred = integer%100;
		if (remainderHundred > 9 && remainderHundred < 21) {
			return integer + "th";
		} else {
			int remainderTen = integer % 10;
			switch (remainderTen) {
				case 1:
					return integer + "st";
				case 2:
					return integer + "nd";
				case 3:
					return integer + "rd";
				default:
					return integer + "tg";
			}
		}
	}
	
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
