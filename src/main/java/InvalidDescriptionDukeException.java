public class InvalidDescriptionDukeException extends DukeException {
    String command;
    String descrption;
    public InvalidDescriptionDukeException(String command, String description) {
        super("command:" + command + " description: " + description);
        this.command = command;
        this.descrption = description;
    }

    @Override
    public String toString() {
        return "OOPS!!! invalid description '" + this.descrption + "' for command '" + this.command + "'";
    }

}
