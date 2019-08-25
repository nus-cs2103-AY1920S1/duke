public class Parser {

    public static Command parse(String str) throws DukeException{
        str = str.trim();
        int lastIndex = str.indexOf(' ');
        if(lastIndex < 0){
            lastIndex = str.length();
        }
        String[] temp;
        switch(str.substring(0, lastIndex)){
            case "bye":
                return new ExitCommand();
            case "delete":
                return new DeleteCommand(str.substring(lastIndex));
            case "done":
                return new DoneCommand(str.substring(lastIndex));
            case "list":
                return new ListCommand();
            case "deadline":
                temp = str.substring(8).split(" /by ");
                if (temp.length < 1 || temp[0].isBlank())
                    throw new EmptyFieldDukeException("description", "deadline");
                if (temp.length < 2 || temp[1].isBlank())
                    throw new EmptyFieldDukeException("time", "deadline");
                return new AddCommand(new Deadline(temp[0], temp[1]));
            case "event":
                temp = str.substring(5).split(" /at ");
                if (temp.length < 1 || temp[0].isBlank())
                    throw new EmptyFieldDukeException("description", "event");
                if (temp.length < 2 || temp[1].isBlank())
                    throw new EmptyFieldDukeException("time", "event");
                return new AddCommand(new Event(temp[0], temp[1]));
            case "todo":
                return new AddCommand(new Todo(str.substring(4)));
        }
        throw new InvalidCommandDukeException();
    }
}
