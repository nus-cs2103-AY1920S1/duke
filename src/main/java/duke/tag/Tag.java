package duke.tag;

public class Tag {
    private String tagName;

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public void editTag(String tagName) {
        this.tagName = tagName;
    }

    public String toString() {
        return "#" + this.tagName;
    }
}
