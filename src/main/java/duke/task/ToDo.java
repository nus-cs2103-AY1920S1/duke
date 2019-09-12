package duke.task;

import duke.command.Parser;

/**
 * Represents ToDo object with a description.
 */
public class ToDo extends Task {
    /**
     * Creates an object using information from the ArrayList of the Parser.
     * @param parser parser that contains information about the ToDo object.
     */
    public ToDo(Parser parser) {
        super(parser.getList().get(0));
    }

    /**
     * Creates a Todo object with description as input.
     * @param des description of the ToDo object.
     */
    public ToDo(String des) {
        super(des);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ToDo)) {
            return false;
        } else {
            return this.getDescription().equals(((ToDo) o).getDescription());
        }
    }
}
