package task;

public class Parser {

    /**
     * Parse the inputs from the user.
     * 
     * @param textInput Inputs from the user.
     * @return Formatted string of the input after parsing.
     */
    public static String parse(String textInput) {
        try {
            return parseCommands(textInput);
        } catch (DukeException e) {
            return Ui.printException(e);
        }
    }

    private static String parseTasks(String textInput) throws DukeException {
        if (textInput.startsWith("todo")) {
            if (isInvalidCommand(textInput, "todo")) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            String description = textInput.replaceFirst("todo ", "");
            return TaskList.addTask(new Todo(description));
        } else if (textInput.startsWith("deadline")) {
            if (isInvalidCommand(textInput, "deadline")) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
            String removeTaskWord = textInput.replaceFirst("deadline ", "");
            String[] taskSplit = removeTaskWord.split(" /by ");
            return TaskList.addTask(new Deadline(taskSplit[0], taskSplit[1]));
        } else if (textInput.startsWith("event")) {
            if (isInvalidCommand(textInput, "event")) {
                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
            }

            String removeTaskWord = textInput.replaceFirst("event ", "");
            String[] taskSplit = removeTaskWord.split(" /at ");
            return TaskList.addTask(new Event(taskSplit[0], taskSplit[1]));
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static String parseCommands(String textInput) throws DukeException {
        if (textInput.equals("list")) {
            return Ui.printList();
        } else if (textInput.startsWith("done")) {
            if (isInvalidCommand(textInput, "done")) {
                throw new DukeException("OOPS!!! Index required.");
            }

            int completedIndex = Integer.parseInt(textInput.replaceFirst("done ", "")) - 1;
            if (isInvalidIndex(completedIndex)) {
                throw new DukeException("OOPS!!! Index not found.");
            }

            return TaskList.doneTask(completedIndex);
        } else if (textInput.startsWith("delete")) {
            if (isInvalidCommand(textInput, "delete")) {
                throw new DukeException("OOPS!!! Index required.");
            }

            int deletedIndex = Integer.parseInt(textInput.replaceFirst("delete ", "")) - 1;
            if (isInvalidIndex(deletedIndex)) {
                throw new DukeException("OOPS!!! Index not found.");
            }

            return TaskList.deleteTask(deletedIndex);
        } else if (textInput.startsWith("find")) {
            if (isInvalidCommand(textInput, "find")) {
                throw new DukeException("OOPS!!! Keyword required!");
            }

            String keyWord = textInput.replaceFirst("find ", "");
            return TaskList.findTask(keyWord);
        } else if (textInput.equals("bye")) {
            return Ui.endOfInteractions();
        } else {
            return parseTasks(textInput);
        }
    }

    private static boolean isInvalidCommand(String textInput, String command) {
        return textInput.equals(command) || textInput.equals(command + " ");
    }

    private static boolean isInvalidIndex(int index) {
        return index < 0 || index >= TaskList.getCounter();
    }
}