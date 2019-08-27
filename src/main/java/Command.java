import java.time.format.DateTimeFormatter;
import java.util.Scanner;

abstract public class Command {
	protected boolean isExit;
	protected Scanner inFullCommandScanner;
	protected static final DateTimeFormatter dateTimeFormatter
		= DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

	public Command() {}

	public Command(Scanner inFullCommandScanner) {
		this.inFullCommandScanner = inFullCommandScanner;
	}

	public boolean isExit() {
		return this.isExit;
	}

	abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
