package duke.tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TagList {
    List<Tag> tags = new ArrayList<>();

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Tag getTag(int index) {
        return getTags().get(index);
    }

    public void addTag(Tag tag) {
        getTags().add(tag);
    }

    public boolean removeTag(Tag tag) {
        return getTags().remove(tag);
    }

    public boolean contains(Tag tag) {
        return getTags().contains(tag);
    }

    @Override
    public String toString() {
        return "Tags: " + String.join("|",
                getTags().stream()
                        .map(Tag::getName)
                        .collect(Collectors.toList())
        );
    }
}
