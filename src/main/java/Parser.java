import java.util.ArrayList;

/**
 * Represents a class for understanding the input.
 */
public class Parser {

    /**
     * Extracts a command from input.
     * @param input a line of user input
     * @return a Command object
     * @throws DukeException if the input doesn't fit the format of a standard command
     */
    static Command parse(String input) throws DukeException {
        if (input.equals("bye")) { // exit
            return new Bye();
        } else if (input.equals("list")) { // show all tasks
            return new PrintList();
        } else if (input.split(" ")[0].equals("done")) {
            try {
                ArrayList<Integer> nums = parseNumbers(input);
                return new Done(nums.toArray(new Integer[0]));
            } catch (NumberFormatException ex) {
                throw new DukeException("Task number should be integer.");
            }
        } else if (input.split(" ")[0].equals("delete")) { // delete a specific task
            try {
                ArrayList<Integer> nums = parseNumbers(input);
                return new Delete(nums.toArray(new Integer[0]));
            } catch (NumberFormatException ex) {
                throw new DukeException("Task number should be integer.");
            }
        } else if (input.split(" ")[0].equals("find")) { // find a task
            if (input.split(" ").length == 1) {
                throw new DukeException("Item to be find should not be empty.");
            }
            return new Find(input.substring(input.indexOf(" ") + 1));
        } else {
            if (input.split(" ").length == 1) {
                String type = input.split(" ")[0];
                switch (type) {
                case "todo":
                    throw new DukeException("Sorry Mirana, the description of a todo cannot be empty.");
                case "event":
                    throw new DukeException("Sorry Mirana, the description of an event cannot be empty.");
                case "deadline":
                    throw new DukeException("Sorry Mirana, the description of a deadline cannot be empty.");
                default:
                    throw new DukeException("Sorry Mirana, but I don't know what that means :-(");
                }
            }
            String type = input.substring(0, input.indexOf(" "));
            String description = input.substring(input.indexOf(" ") + 1);
            switch (type) {
            case "todo":
                return new Add("todo", description);
            case "event":
                return new Add("event", description);
            case "deadline":
                return new Add("deadline", description);
            default:
                throw new DukeException("Sorry Mirana, but I don't know what that means :-(");
            }
        }
    }

    private static ArrayList<Integer> parseNumbers(String input) {
        String[] words = input.split(" ");
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 1; i < words.length; i++) {
            int num = Integer.parseInt(words[i]);
            nums.add(num);
        }
        return nums;
    }
}