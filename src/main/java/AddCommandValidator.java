public class AddCommandValidator {

    public AddCommandValidator() {

    }

    public void validateDetail(String[] detail) throws InvalidDukeCommandException {
        if (detail.length == 0) {
            throw new InvalidDukeCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (! detail[0].equals("todo") && ! detail[0].equals("event") && ! detail[0].equals("deadline")) {
            throw new InvalidDukeCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (detail.length == 1) {
            throw new InvalidDukeCommandException("OOPS!!! The description of a " + detail[0] + " cannot be empty.");
        }
    }

    public void validateDeadlineDetails(String[] detail) throws InvalidDeadlineSyntaxException {
        if (detail.length != 2) {
            throw new InvalidDeadlineSyntaxException("OOPS!!! The due date of a deadline must be specified.");
        }
    }

    public void validateEventDetails(String[] detail) throws InvalidEventSyntaxException {
        if (detail.length != 2) {
            throw new InvalidEventSyntaxException("OOPS!!! The timeline of an event must be specified.");
        }
    }
}