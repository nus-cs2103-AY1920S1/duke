import java.util.Stream;
import java.util.function.Consumer;
import java.util.ArrayList;


class TaskList {
	private static Stream stringStream;

	protected ArrayList<Task> list;

	public TaskList() {
		this.list = new ArrayList<Task>();
	}

	public TaskList(Stream stringStream) {
		this.stringStream = stringStream;
		this.list = new ArrayList<>();

		stringStream.forEach(x -> Parser.parse.accept(x));
	}

	private Consumer<String> parse = (line) -> {
		String[] parts = line.split(" ");
		boolean isDone;
		String type = parts[0];
		int binary = Integer.valueOf(parts[1]);
		if (binary == 1) {
			isDone = true;
		} else {
			isDone = false;
		}
		String description = parts[2];
		loadLine(type, isDone, description);
	};

	private void loadLine(String type, boolean isDone, String description) throws DukeException {
		switch(type) {
		case "todo":
		    loadTodo(isDone, description);
		    break;

		case "deadline":
		    loadDeadline(isDone, description);
		    break;

		case "event":
		    loadEvent(isDone, description);
		    break;
		}
	}

	private Task loadTodo(boolean isDone, String description) {
		Todo todo = new Todo(isDone, description);
		list.add(todo);
		return todo;
	}

	private Task loadDeadline (boolean isDone, String description) throws DukeException {
		int indexBy = description.indexOf("/"); // potential source of error
		if (indexBy == -1) {
			throw new DukeException("Put / before by!");
		}
		String taskDesc = description.substring(0, indexBy - 1); // start after space, end before space before /

		String dateInString = line.substring(indexBy + 4);
		Date date = formatterIn.parse(dateInString);
		String by = formatterOut.format(date);

		Deadline dead = new Deadline(isDone, taskDesc, by);
		list.add(dead);
		return dead;
	}

	private Task loadEvent(boolean isDone, String description) throws DukeException {
		int indexAt = description.indexOf("/");
		if (indexAt == -1) {
			throw new DukeException("Put / before at!");
		}
		String taskDesc = description.substring(0, indexAt - 1); // start after space, end before space before /

		String dateInString = line.substring(indexAt + 4);
		Date date = formatterIn.parse(dateInString);
		String at = formatterOut.format(date);

		Event event = new Event(isDone, taskDesc, at);
		list.add(event);
		return event;
	}
}