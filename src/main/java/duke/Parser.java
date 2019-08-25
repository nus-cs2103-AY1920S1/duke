package duke;

class Parser {
    static void validateTaskDescription(String description)
            throws DukeInvalidArgumentException {

        if (description.length() == 0) {
            throw new DukeInvalidArgumentException(
                    "User specified description of task is empty",
                    " \u2639 OOPS!!! The description of a task cannot be empty.");
        }
    }
}
