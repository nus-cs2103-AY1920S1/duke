import java.text.SimpleDateFormat;
import java.text.ParseException;

class Ui {
	private static SimpleDateFormat formatterIn = new SimpleDateFormat("d/M/yyyy HHmm");
	private static SimpleDateFormat formatterOut = new SimpleDateFormat("d MMM yyyy ha");

	private Scanner scanner;

	public Ui() {
		this.scanner = new Scanner(System.in);
	}

	public void showWelcome() {
		String logo = " ____        _        \n"
				+ "|  _ \\ _   _| | _____ \n"
				+ "| | | | | | | |/ / _ \\\n"
				+ "| |_| | |_| |   <  __/\n"
				+ "|____/ \\__,_|_|\\_\\___|\n";
		
		System.out.println("Hello! I'm Duke\nWhat can I do for you?");
	}

	public String readCommand() {
		return scanner.next(); // no longer need nextline because adding comes with type of task
	}

	public void showLine() {
		System.out.println("_______");
	}
}