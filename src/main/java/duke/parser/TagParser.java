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

    private boolean isInvalidIndexGiven(String ind){
        try {
            Integer.parseInt(ind);
        } catch (Exception e) {
            return true;
        }

        return false;
    }

    private boolean isValidTagGiven(String[] data) {
        return data.length > 2;
    }

    private boolean isIndexNotGiven(String[] data) {
        return data.length < 1;
    }

    private boolean isTagNotGiven(String[] data) {
        return data.length == 1;
    }

    public void createTag(String[] tagData) throws InvalidTagException, InvalidTaskIndexException {
        if (isIndexNotGiven(tagData)) {
            throw new InvalidTagException("Please key in an index!");
        } else if (isTagNotGiven(tagData)) {
            throw new InvalidTagException("Please key in a tag!");
        } else if (isValidTagGiven(tagData)) {
            throw new InvalidTagException("Please key in only one tag!");
        } else if (isInvalidIndexGiven(tagData[0])) {
            throw new InvalidTagException("Please key in a valid index!");
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

    public int getIndex() {
        return this.index;
    }

    public Tag getTag() {
        return this.tag;
    }
}
