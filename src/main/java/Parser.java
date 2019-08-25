import java.text.ParseException;
import java.util.Scanner;

class Parser {

    /**
     * parsing the statement by the user.
     * @param fullCommand input by user.
     * @return a command
     * @throws DukeException when input does not follow required format.
     * @throws ParseException when input format is different from required.
     */
    static Command parse(String fullCommand) throws DukeException, ParseException {
        Scanner sc = new Scanner(fullCommand);
        String command = sc.next();

        switch(command) {
        case "done": {
            if (!sc.hasNextInt()) {
                throw new NoTaskException();
            }
            int index = sc.nextInt() - 1;
            return new DoneCommand(index);
        }

        case "delete": {
            if (!sc.hasNextInt()) {
                throw new NoTaskException();
            }
            int index = sc.nextInt() - 1;
            return new DeleteCommand(index);
        }

        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();

        default:
            Task t;
            String words = "";
            boolean emptyCommand = !sc.hasNext();
            if (!emptyCommand) {
                words = sc.nextLine();
            }

            switch (command) {
            case "todo": {
                if (emptyCommand) {
                    throw new EmptyCommandException("todo");
                }
                t = new Todo(words.trim());
                break;
            }
            case "event": {
                if (emptyCommand) {
                    throw new EmptyCommandException("event");
                }
                String[] details = words.split(" /at ");
                if (details.length == 1) {
                    throw new NoDateException("event");
                }
                t = new Event(details[0].trim(), details[1].trim());
                break;
            }
            case "deadline": {
                if (emptyCommand) {
                    throw new EmptyCommandException("deadline");
                }
                String[] details = words.split(" /by ");
                if (details.length == 1) {
                    throw new NoDateException("deadline");
                }
                t = new Deadline(details[0].trim(), details[1].trim());
                break;
            }
            default:
                throw new UnknownCommandExeption();
            }
            return new AddCommand(t);
        }
    }
}

