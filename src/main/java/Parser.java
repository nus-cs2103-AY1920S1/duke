import java.text.ParseException;
import java.util.Date;

class Parser {
    static String extractCommand(String input) {
        return input.split(" ", 2)[0];
    }

    static String extractId(String input) {
        return input.split(" ", 2)[1];
    }

    /**
     * Extracts the query from an input string.
     * @param input Input string
     * @return Query extracted
     */
    static String extractQuery(String input) {
        return Parser.extractId(input);
    }

    static Task parseTask(String input) throws DukeException {
        try {
            String[] tokens = input.split(" ", 2);
            String command = tokens[0];
            String data = tokens[1];

            Task task;

            if (command.equals("todo")) {
                task = new Todo(data);
                return task;
            }

            if (command.equals("deadline")) {
                tokens = data.split(" /by ");
            } else {
                tokens = data.split(" /at ");
            }

            String description = tokens[0];
            String dateStr = tokens[1];
            Date date = Parser.parseDate(dateStr);

            if (command.equals("deadline")) {
                task = new Deadline(description, date);
            } else {
                task = new Event(description, date);
            }
            return task;
        } catch (Exception e) {
            throw new DukeException("Failed to parse task.");
        }
    }

    static Task parseSavedTask(String input) throws DukeException {
        String[] tokens = input.split(" \\| ");

        String type = tokens[0];
        boolean isDone = tokens[1].equals("1");
        String description = tokens[2];

        Task task;

        switch (type) {
        case "T": {
            task = new Todo(description, isDone);
            break;
        }

        case "D": {
            Date date = Parser.parseDate(tokens[3]);
            task = new Deadline(description, isDone, date);
            break;
        }

        case "E": {
            Date date = Parser.parseDate(tokens[3]);
            task = new Event(description, isDone, date);
            break;
        }

        default: {
            throw new DukeException("Invalid task type found in save file.");
        }
        }

        return task;
    }

    static Date parseDate(String dateStr) throws DukeException {
        try {
            return Duke.dateFormatter.parse(dateStr);
        } catch (ParseException e) {
            throw new DukeException("Failed to parse date.");
        }
    }
}
