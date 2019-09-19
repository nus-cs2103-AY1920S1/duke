package duke.tag;

import java.util.Objects;

public class Tag {
    private String name;

    public Tag(String name) {
        this.name = name;
    }

    public String getTagName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("#%s", name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Tag)) {
            return false;
        }

        return hashCode() == o.hashCode();
    }
}
