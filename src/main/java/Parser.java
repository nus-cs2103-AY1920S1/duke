import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
	public String getCommand(String rawInput) {
		if (rawInput.toLowerCase().equals("bye")) {
			return "bye";
		} else if (rawInput.toLowerCase().equals("list")) {
			return "list";
		} else if (rawInput.toLowerCase().startsWith("todo")) {
			return "todo";
		} else if (rawInput.toLowerCase().startsWith("done")) {
			return "done";
		} else if (rawInput.toLowerCase().startsWith("event")) {
			return "event";
		} else if (rawInput.toLowerCase().startsWith("delete")) {
			return "delete";
		} else if (rawInput.toLowerCase().startsWith("deadline")) {
			return "deadline";
		} else {
			return "none";
		}
	}

	public int processDone(String rawInput) throws DukeException {
		try {
			return Integer.parseInt(rawInput.split(" ")[1]) - 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new DukeException("Please indicate which task is done.");
		}
	}
	
	public int processDelete(String rawInput) throws DukeException {
		try {
			return Integer.parseInt(rawInput.split(" ")[1]) - 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new DukeException("Please indicate which task to delete.");
		}
	}
	
	public String deadlineDesc(String rawInput) throws DukeException {
		try {
			return rawInput.substring(9).split("/by")[0].trim();
		} catch (StringIndexOutOfBoundsException e) {
			throw new DukeException("The description of a deadline cannot be empty.");
		}
	}
	
	public Date deadlineTime(String rawInput) throws DukeException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
			return sdf.parse(rawInput.substring(9).split("/by")[1].trim());
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new DukeException("The timing of a deadline cannot be empty.");
		} catch (ParseException e) {
			throw new DukeException("The timing of the event is invalid.");
		}
	}
	
	public String eventDesc(String rawInput) throws DukeException {
		try {
			return rawInput.substring(9).split("/at")[0].trim();
		} catch (StringIndexOutOfBoundsException e) {
			throw new DukeException("The description of an event cannot be empty.");
		}
	}

	public Date eventTime(String rawInput) throws DukeException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
			return sdf.parse(rawInput.substring(9).split("/at")[1].trim());
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new DukeException("The timing of an event cannot be empty.");
		} catch (ParseException e) {
			throw new DukeException("The timing of the event is invalid.");
		}
	}

	public String todoDesc(String rawInput) throws DukeException {
		try {
			return rawInput.substring(5);
		} catch (StringIndexOutOfBoundsException e) {
			throw new DukeException("The description of a todo cannot be empty.");
		}
	}
}