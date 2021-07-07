import java.util.Arrays;
import java.util.Objects;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


    @Override
    public int hashCode() {
        return Objects.hash(this.description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Todo test = (Todo) o;
        return this.description.equals(test.description);
    }
}
