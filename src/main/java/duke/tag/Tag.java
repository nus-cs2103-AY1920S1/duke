package duke.tag;

/**
 * Represents a simple tag to a task.
 */
public class Tag {

    /**
     * Represents the name of the tag.
     */
    private String tagName;

    /**
     * Constructs a new tag with the given tag name.
     * @param tagName the given tag name.
     */
    public Tag(String tagName) {
        if (tagName.equals("no tag")) {
            this.tagName = "";
        } else {
            this.tagName = tagName;
        }
    }

    /**
     * Edits the tag with the given tag name.
     * @param tagName the given tag name.
     */
    public void editTag(String tagName) {
        this.tagName = tagName;
    }

    /**
     * Gets the tag name.
     * @return the tag name.
     */
    public String getTagName() {
        if (this.tagName.equals("")) {
            return "no tag";
        }
        return this.tagName;
    }

    /**
     * Returns a String representation of the tag.
     * @return a String representation of the tag.
     */
    public String toString() {
        if (this.tagName.equals("")) {
            return "no tag";
        }
        return "#" + this.tagName;
    }
}
