import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public static ArrayList<Task> itemsLst = new ArrayList<Task>();

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    // Reading and Storing data from the hard disk
    public static void initItemsLst(Scanner sc) throws DukeException {
        while (sc.hasNext()) {
            String s = sc.nextLine();
            String line = s.substring(8);
            if (s.charAt(0) == 'T') {
                itemsLst.add(new ToDo(line, s.charAt(4) == '1' ? true: false));
            } else if (s.charAt(0) == 'D') {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '|') {
                        itemsLst.add(new Deadline(line.substring(0, i - 1),
                            LocalDateTime.parse(line.substring(i + 2), DateTimeFormatter.ofPattern("d MMMM yyyy, ha")),
                                s.charAt(4) == '1' ? true : false));
                    }
                }
            } else if (s.charAt(0) == 'E') {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '|') {
                        itemsLst.add(new Event(line.substring(0, i - 1),
                            LocalDateTime.parse(line.substring(i + 2), DateTimeFormatter.ofPattern("d MMMM yyyy, ha")),
                                s.charAt(4) == '1' ? true : false));
                    }
                }
            } else {
                throw new DukeException("Invalid file format.");
            }
        }
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String fileString() {
        return " | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }

}