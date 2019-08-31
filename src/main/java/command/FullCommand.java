package command;

import exception.DukeException;

public enum FullCommand {
    LIST("list"), DONE("done"), DELETE("delete"), TODO("todo"), DEADLINE("deadline")
        , EVENT("event"), BYE("bye");

    private String name;

    private FullCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static FullCommand getByName(String keyword) throws DukeException {
        for(FullCommand command : values()){
            if(command.getName().equals(keyword)){
                return command;
            }
        }

        throw new DukeException("no such command");
    }
}

