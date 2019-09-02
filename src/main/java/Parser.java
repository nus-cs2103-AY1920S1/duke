import java.text.ParseException;

public class Parser {

    public Parser() {

    }

    // parse the command string keyed in by the user
    public static Command parse (String input) {
        String[] userInput = input.split(" ", 2);
        String firstWord = userInput[0]; //firstword of the user input
        switch(firstWord) {
            case "bye" :
                return new ExitCommand();
            case "list" :
                return new ListCommand();
            case "done" :
                int indexDone = Integer.parseInt(userInput[1]);
                return new DoneCommand(indexDone);
            case "delete" :
                int indexDelete = Integer.parseInt(userInput[1]);
                return new DeleteCommand(indexDelete);
            case "todo" :
                String descTodo = userInput[1];
                if (descTodo.isEmpty()) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                ToDo todo = new ToDo(descTodo);
                return new AddCommand(todo);
            case "deadline" :
                String descDeadline = userInput[1];
                if (descDeadline.isEmpty()) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                String[] deadlineInfo = descDeadline.split("/by");
                if (deadlineInfo.length < 2) {
                    throw new DukeException("☹ OOPS!!! There is no due date specified.");
                }
                String actionD = deadlineInfo[0]; // the action of the deadline
                String dueDateD = deadlineInfo[1]; // the due date of the deadline
                Deadline deadline = new Deadline(actionD);
                try {
                    deadline.parseTime(dueDateD);
                } catch (ParseException e) {
                    throw new DukeException ("Incorrect format for due date of deadline!");
                }
                return new AddCommand(deadline);
            case "event" :
                String descEvent = userInput[1];
                if (descEvent.isEmpty()) {
                    throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                }
                String[] eventInfo = descEvent.split("/at");
                if (eventInfo.length < 2) {
                    throw new DukeException("☹ OOPS!!! There is no due date specified.");
                }
                String actionE = eventInfo[0]; // the action of the event
                String dueDateE = eventInfo[1];
                Event event = new Event(actionE);
                try {
                    event.parseTime(dueDateE);
                } catch (ParseException e) {
                    throw new DukeException ("Incorrect format for due date of Event!!");
                }
                return new AddCommand(event);
        }
    }
}
