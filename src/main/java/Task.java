import java.text.SimpleDateFormat;

public abstract class Task {
    String name;
    String type;
    Boolean done;
    SimpleDateFormat formatter;

    public Task(String name, SimpleDateFormat formatter) {
        this.name = name;
        this.done = false;
        this.formatter = formatter;
    }

    public abstract String saveText();
}
