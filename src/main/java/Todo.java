/**
 * Represents a Todo to be completed.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Todo)) {
            return false;
        }

        Todo other = (Todo) o;
        return this.description == other.description;
    }
}
