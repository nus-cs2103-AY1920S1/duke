package duke;

public class PreParser {
    private CommandMap commandMap;
    public PreParser() {
        String[] dummyArgs = new String[0];
        commandMap = new CommandMap();
        commandMap.register(new duke.commands.Bye(dummyArgs));
        commandMap.register(new duke.commands.List(dummyArgs));
        commandMap.register(new duke.commands.Done(dummyArgs));
        commandMap.register(new duke.commands.Todo(dummyArgs));
        commandMap.register(new duke.commands.Deadline(dummyArgs));
        commandMap.register(new duke.commands.Event(dummyArgs));
        commandMap.register(new duke.commands.Delete(dummyArgs));
    }
    public Command parse(String fullCommand) throws DukeException {
        String[] args = fullCommand.split(" ");
        try {
            Class<? extends Command> cls = commandMap.get(args[0]);
            Command command = cls.getConstructor(String[].class).newInstance(new Object[]{args});
            return command;
        } catch(Exception e) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
