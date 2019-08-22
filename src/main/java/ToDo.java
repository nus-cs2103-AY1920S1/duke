import java.lang.StringBuilder;

public class ToDo extends Task{
    
    public ToDo(String description) {
        super(description);
    }

    public static ToDo createToDo(String [] tokens) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < tokens.length - 1 ; i++) {
            builder.append(tokens[i]);
            builder.append(" ");
        }
        builder.append(tokens[tokens.length-1]);
        return new ToDo(builder.toString());
    }

    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), getDescription());
    }

}