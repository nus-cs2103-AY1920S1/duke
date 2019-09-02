import javafx.application.Platform;

/**
 * Encapsulates a parser that parses input.
 */
public class Parser {

    /**
     * Static method. Parses the user input into relevant commands which are then returned.
     * @param input user input
     * @return relevant command parsed from user input
     */
    public static Command parse(String input) {
        String[] inputSplit = input.split(" ");
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new DisplayListCommand();
        } else if (inputSplit[0].equals("delete")) {
            int inputIndex = Integer.parseInt(inputSplit[1]);
            int actualIndex = inputIndex - 1;
            DeleteCommand delete = new DeleteCommand();
            delete.setIndexToRemove(actualIndex);
            return delete;
        } else if (inputSplit[0].equals("done")) {
            int inputIndex = Integer.parseInt(inputSplit[1]);
            int actualIndex = inputIndex - 1;
            DoneCommand done = new DoneCommand();
            done.setIndexToMarkDone(actualIndex);
            return done;
        } else if (inputSplit[0].equals("todo")) {
            AddTodoCommand addTodo = new AddTodoCommand();
            addTodo.setInputTodo(input);
            return addTodo;
        } else if (inputSplit[0].equals("event")) {
            AddEventCommand addEvent = new AddEventCommand();
            addEvent.setInputEvent(input);
            return addEvent;
        } else if (inputSplit[0].equals("deadline")) {
            AddDeadlineCommand addDeadline = new AddDeadlineCommand();
            addDeadline.setInputDeadline(input);
            return addDeadline;
        } else if (inputSplit[0].equals("find")) {
            String keyword = input.substring(5);
            FindCommand find = new FindCommand();
            find.setKeyword(keyword);
            return find;
        } else {
            return new UnrecognisedInputCommand();
        }
    }
}
