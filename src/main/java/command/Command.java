package duke.command;

import java.util.ArrayList;

public class Command {
    public Type type;
    public String[] parameters;

    public Command(Type type, String... parameters) {
        this.type = type;
        this.parameters = parameters;
    }
}





