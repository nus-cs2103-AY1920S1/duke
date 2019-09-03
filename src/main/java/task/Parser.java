package task;

public class Parser {

    /**
     * Parse the inputs from the user.
     * 
     * @param TaskList Current TaskList that contains an ArrayList of tasks and
     *                 number of tasks.
     * @return New TaskList that contains an ArrayList of tasks and number of tasks.
     */
    public static String parse(String textInput) {
        try {
            if (textInput.equals("list")) {
                return Ui.printList();
            } else if (textInput.startsWith("done")) {
                if (textInput.equals("done") || textInput.equals("done ")) {
                    throw new DukeException("OOPS!!! Index required.");
                }

                int completedIndex = Integer.parseInt(textInput.replaceFirst("done ", "")) - 1;
                if (completedIndex < 0 || completedIndex >= TaskList.getCounter()) {
                    throw new DukeException("OOPS!!! Index not found.");
                }

                return TaskList.doneTask(completedIndex);
            } else if (textInput.startsWith("todo")) {
                if (textInput.equals("todo") || textInput.equals("todo ")) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                String description = textInput.replaceFirst("todo ", "");
                return TaskList.addTask(new Todo(description));
            } else if (textInput.startsWith("deadline")) {
                if (textInput.equals("deadline") || textInput.equals("deadline ")) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                }
                String removeTaskWord = textInput.replaceFirst("deadline ", "");
                String[] taskSplit = removeTaskWord.split(" /by ");
                return TaskList.addTask(new Deadline(taskSplit[0], taskSplit[1]));
            } else if (textInput.startsWith("event")) {
                if (textInput.equals("event") || textInput.equals("event ")) {
                    throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                }

                String removeTaskWord = textInput.replaceFirst("event ", "");
                String[] taskSplit = removeTaskWord.split(" /at ");
                return TaskList.addTask(new Event(taskSplit[0], taskSplit[1]));
            } else if (textInput.startsWith("delete")) {
                if (textInput.equals("delete") || textInput.equals("delete ")) {
                    throw new DukeException("OOPS!!! Index required.");
                }

                int deletedIndex = Integer.parseInt(textInput.replaceFirst("delete ", "")) - 1;
                if (deletedIndex < 0 || deletedIndex >= TaskList.getCounter()) {
                    throw new DukeException("OOPS!!! Index not found.");
                }

                return TaskList.deleteTask(deletedIndex);
            } else if (textInput.startsWith("find")) {
                if (textInput.equals("find") || textInput.equals("find ")) {
                    throw new DukeException("OOPS!!! Keyword required!");
                }

                String keyWord = textInput.replaceFirst("find ", "");
                return TaskList.findTask(keyWord);
            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            return Ui.printException(e);
        }
    }
}