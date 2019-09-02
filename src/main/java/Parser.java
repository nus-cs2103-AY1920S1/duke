class Parser {

    static Command parse(String fullCommand) throws DukeException {
        String[] commandWords = fullCommand.split(" ");
        String action = commandWords[0];
        try {
            switch (action) {
            case "done":
                int doneItemNo = Integer.parseInt(commandWords[1]);
                return new DoneCommand(doneItemNo);
            case "delete":
                int deletedItemNo = Integer.parseInt(commandWords[1]);
                return new DeleteCommand(deletedItemNo);
            case "list":
                return new ListCommand();
            case "bye":
                return new ExitCommand();
            case "event":
                String[] eventComponents = fullCommand.substring(6).split(" /at ");
                String eventDescription = eventComponents[0];
                String eventTime = eventComponents[1];
                return new AddCommand(action, eventDescription, eventTime);
            case "todo":
                String todoDescription = fullCommand.substring(5);
                return new AddCommand(todoDescription);
            case "deadline":
                String[] deadlineComponents = fullCommand.substring(9).split(" /by ");
                String deadlineDescription = deadlineComponents[0];
                String deadlineTime = deadlineComponents[1];
                return new AddCommand(action, deadlineDescription, deadlineTime);
            default:
                throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("     Please enter the description!");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("     Please enter the time/date!!");
        } catch(Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
}
