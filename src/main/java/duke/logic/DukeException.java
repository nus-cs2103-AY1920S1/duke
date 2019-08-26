package duke.logic;

/**
 * Exception to be thrown in Duke application.
 */

public class DukeException extends Exception {

	private String mesg;

	public DukeException(String mesg) {

		super(mesg);
		this.mesg = mesg;
	}

	public String getMessage() {
		return this.mesg;
	}
}
