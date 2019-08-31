public class AddDeadlineCommand extends AddCommand {

    public AddDeadlineCommand(String line) {
        super(line);
        try {
            String[] arr = super.line.split(" /by ");
            super.task = new Deadline(arr[0], arr[1]);
        } catch(ArrayIndexOutOfBoundsException aioobe) {
            throw new InvalidParameterException(line.isBlank() ? null : line);
        }
    }

    public boolean isExit() {
        return false;
    }

}
