import java.util.Scanner;

public class Deadline extends Task {
    public Deadline(String description, String date) {
        super("[D]", description, date);
    }

    public String toString() {
        return String.format("%s%s %s(by:%s)", this.type,
                this.getStatusIcon(),
                this.description,
                this.date);
    }
}
