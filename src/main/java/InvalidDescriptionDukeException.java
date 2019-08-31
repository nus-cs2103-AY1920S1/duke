public class InvalidDescriptionDukeException extends DukeException {
    String command;
    String description;

    public InvalidDescriptionDukeException(String command, String description) {
        super("command:" + command + " description: " + description);
        this.command = command;
        this.description = description;
    }

    @Override
    public String toString() {
        return "OOPS!!! invalid description '" + this.description + "' for command '" + this.command + "'";
    }

}
