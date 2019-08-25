package duke.logic;

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
