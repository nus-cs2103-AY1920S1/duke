@SuppressWarnings("serial")
public class DukeNoCommandException extends DukeException {
    public DukeNoCommandException() {
        super("No command issued", "");
    }

    @Override
    public String toString() {
        String template = "[DukeNoCommandException] %s";
        return String.format(template, this.getMessage()); 
    }
}
