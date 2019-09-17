package seedu.duke;

/**
 * Handles the parsing of user input.
 */
public class Parser {
    private static boolean isTriviaAnswer;

    /**
     * Parses user input and returns a command corresponding to user input.
     *
     * @param str String containing user input.
     * @return Command corresponding to user input.
     * @throws DukeException if user input is invalid or does not correspond to any existing command.
     */
    public static Command parse(String str) throws DukeException {
        String full = str;
        String[] arr = full.split(" ", 2);
        String s = arr[0];
        Command c = null;

        validateInput(full);

        if (isTriviaAnswer) {
            isTriviaAnswer = false;
            c = new AnswerCommand(full);
        } else if (s.equals("done")) {
            c = new DoneCommand(Integer.valueOf(arr[1]) - 1);
        } else if (s.equals("delete")) {
            c = new DeleteCommand(Integer.valueOf(arr[1]) - 1);
        } else if (s.equals("edit")) {
            String information = arr[1].trim();
            String[] split = information.split(" ", 2);
            c = new EditCommand(Integer.valueOf(split[0]) - 1, split[1]);
        } else if (s.equals("trivia")) {
            isTriviaAnswer = true;
            c = new TriviaCommand();
        } else if (s.equals("todo") || s.equals("deadline") || s.equals("event")) {
            c = new AddCommand(s, arr[1].trim());
        } else if (s.toLowerCase().equals("list")) {
            c = new ListCommand();
        } else if (s.equals("find")) {
            c = new FindCommand(arr[1].trim());
        } else if (s.equals("bye")) {
                c = new ExitCommand();
        }

        return c;
    }

    /**
     * Parses user input and detects if it corresponds to existing commands.
     *
     * @param input String containing user input.
     * @throws DukeException if user input is invalid.
     */
    public static void validateInput(String input) throws DukeException {
        String[] array = input.trim().split(" ");
        String first = array[0];
        Boolean task = false;

        if (first.equals("todo") || first.equals("deadline") || first.equals("event")) {
            task = true;
        }

        if (array.length == 1) {
            //if to do, event or deadline are missing a description
            if (task) {
                throw new DukeException("The description of a " + first + " cannot be empty.");
            } else if (first.equals("done") || first.equals("delete") || first.equals("find")) {
                //if done, delete or find are not followed by a number
                throw new DukeException("Please specify a task.");
            } else if (!first.equals("bye") && !first.equals("list") && !first.equals("trivia") && !isTriviaAnswer) {
                //if it is not a single-worded valid input
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } else {
            //if it is an invalid input containing multiple words
            if (!task && !first.equals("done") && !first.equals("delete") && !first.equals("find")
                    && !first.equals("edit") && !isTriviaAnswer) {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            } else if ((first.equals("event") && !input.contains("/at"))
                    || (first.equals("deadline") && !input.contains("/by"))) {
                //if event or deadline do not have details
                throw new DukeException("The details of a " + first + " cannot be missing.");
            }
        }
    }
}
