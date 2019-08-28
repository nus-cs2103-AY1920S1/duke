package seedu.duke;

public class Parser {

    public static Command parse(String str) throws DukeException {
        String full = str;
        String[] arr = full.split(" ", 2);
        String s = arr[0];
        Command c = null;

        validateInput(full);

        if (s.equals("done")) {
            c = new DoneCommand(Integer.valueOf(arr[1]) - 1);
        }

        else if (s.equals("delete")) {
            c = new DeleteCommand(Integer.valueOf(arr[1]) - 1);
        }

        else if (s.equals("todo") || s.equals("deadline") || s.equals("event")) {
            c = new AddCommand(s, arr[1].trim());
        }

        else if (s.toLowerCase().equals("list")) {
            c = new ListCommand();
        }

        else {
            if (s.equals("bye")) {
                c = new ExitCommand();
            }
        }

        return c;
    }

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
                throw new DukeException("Empty Description", "The description of a " + first + " cannot be empty.");
            }

            //if done or delete are not followed by a number
            else if (first.equals("done") || first.equals("delete")) {
                throw new DukeException("Missing Task", "Please specify a task.");
            }

            //if it is not a single-worded valid input
            else if (!first.equals("bye") && !first.equals("list")) {
                throw new DukeException("Invalid Input", "I'm sorry, but I don't know what that means :-(");
            }
        } else {
            //if it is an invalid input containing multiple words
            if (!task && !first.equals("done") && !first.equals("delete")) {
                throw new DukeException("Invalid Input", "I'm sorry, but I don't know what that means :-(");
            }

            //if event or deadline do not have details
            else if ((first.equals("event") && !input.contains("/at")) ||
                    (first.equals("deadline") && !input.contains("/by"))) {
                throw new DukeException("Missing Details", "The details of a " + first + " cannot be missing.");
            }
        }
    }
}
