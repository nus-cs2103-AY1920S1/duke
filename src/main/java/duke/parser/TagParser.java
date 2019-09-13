package duke.parser;

import duke.exception.InvalidTagException;
import duke.exception.InvalidTaskIndexException;
import duke.tag.Tag;
import duke.task.TaskList;

/**
 * Represents a Tag Parser to parse in the input to return the tag present.
 */
public class TagParser {

    private int index;
    private Tag tag;

    /**
     * Checks if the index given is of an integer or not.
     * @param ind the index given.
     * @return true if the index given is not an integer.
     */
    private boolean isInvalidIndexGiven(String ind) {
        try {
            Integer.parseInt(ind);
        } catch (Exception e) {
            return true;
        }

        int index = Integer.parseInt(ind);
        return (index < 1 || index > TaskList.getNumberOfTasks());
    }

    /**
     * Checks if the tag given is valid.
     * @param data the array containing index and tag values.
     * @return false if more than one tag value is given.
     */
    private boolean isValidTagGiven(String[] data) {
        return data.length > 2;
    }

    /**
     * Checks if the index is given.
     * @param data the array containing index and tag values.
     * @return false if no index value is given.
     */
    private boolean isIndexNotGiven(String[] data) {
        return data.length < 1;
    }


    private boolean isTagNotGiven(String[] data) {
        return data.length == 1;
    }

    /**
     * Creates the index and tag for the tag to be inserted into the task.
     * @param tagData the array containing tag and index values.
     * @throws InvalidTagException if the tag is not given or invalid.
     * @throws InvalidTaskIndexException if the task index is not given or valid.
     */
    public void createTag(String[] tagData) throws InvalidTagException, InvalidTaskIndexException {
        if (isIndexNotGiven(tagData)) {
            throw new InvalidTagException("Please key in an index!");
        } else if (isInvalidIndexGiven(tagData[0])) {
            throw new InvalidTagException("Please key in a valid index!");
        } else if (isTagNotGiven(tagData)) {
            throw new InvalidTagException("Please key in a tag!");
        } else if (isValidTagGiven(tagData)) {
            throw new InvalidTagException("Please key in only one tag!");
        } else {
            this.index = Integer.parseInt(tagData[0]) - 1;
            if (isIndexOutOfBounds()) {
                throw new InvalidTaskIndexException();
            }
            this.tag = makeTag(tagData[1]);
        }
    }

    private boolean isIndexOutOfBounds() {
        return this.index < 0 || this.index > TaskList.getNumberOfTasks();
    }

    /**
     * Takes in a string an returns the tag, if valid.
     * @param input the input entered by the user.
     * @return a tag for the specified task.
     */
    private Tag makeTag(String input) {
        return new Tag(input);
    }

    /**
     * Returns the index of the task to be given the tag to.
     * @return the index of the task.
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Retruns the tag of the task.
     * @return the tag of the task.
     */
    public Tag getTag() {
        return this.tag;
    }
}
