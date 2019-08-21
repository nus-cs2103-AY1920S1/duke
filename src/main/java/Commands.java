public enum Commands {
    LIST("list"), DONE("done"), DELETE("delete"), TODO("todo"), DEADLINE("deadline"), EVENT("event");

    private String name;

    private Commands(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Commands getByName(String keyword) throws DukeException{
        for(Commands command : values()){
            if(command.getName().equals(keyword)){
                return command;
            }
        }

        throw new DukeException((new Border()) + "\n     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + (new Border()) + "\n");
    }
}
