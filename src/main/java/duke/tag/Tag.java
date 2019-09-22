package duke.tag;

/**
 * Tag object tagged with a task.
 */
public class Tag {
    private String tagName;

    /**
     * Initializes a tag instance with its name.
     * @param tagName name of the tag.
     */
    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    @Override
    public String toString() {
        return tagName;
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return this == o
                || (o instanceof Tag
                && tagName.equals(((Tag) o).getTagName()));
    }
}
