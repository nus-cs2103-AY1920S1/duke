import java.io.FileWriter;
import java.io.IOException;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getSimpleStatusIcon() {
        return (isDone ? "1" : "0"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void save(Task[] arr) {
        String file2 = "DukeList.txt";
        StringBuilder sb = new StringBuilder();
        if (arr[0] != null) {
            int x = 0;
            while (arr[x + 1] != null) {
                sb.append(arr[x].toFormattedString());
                sb.append("\n");
                x++;
            }
            sb.append(arr[x].toFormattedString());
        } else {}
        try {
            writeToFile(file2, sb.toString());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static Task readString(String s) throws UnknownInputException {
        String[] arr = s.split("~");
        boolean isDone = arr[1].equals("1");
        String description = arr[2];

        if (arr[0].equals("T")) {
            return new ToDo(description, isDone);
        } else if (arr[0].equals("E")) {
            return new Event(description, isDone, arr[3]);
        } else if (arr[0].equals("D")) {
            return new Deadline(description, isDone, arr[3]);
        } else {
            throw new UnknownInputException("Unknown task found.");
        }
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String toFormattedString() {
        return this.getSimpleStatusIcon() + "~" + this.description;
    }
}