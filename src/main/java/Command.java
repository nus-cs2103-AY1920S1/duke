import java.io.IOException;
import java.text.ParseException;

public abstract class Command {

	public abstract void execute(TaskList tasks, Storage storage) throws EmptyDescriptionException, InvalidInputException, InvalidDescriptionException, IOException;
	
	public abstract boolean isExit();
	
	public static void handleException(Exception e) {
		if (e instanceof InvalidInputException) {
			System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
		} else if (e instanceof EmptyDescriptionException) {
			System.out.println(String.format("OOPS!!! The description of a %s cannot be empty.", e.getMessage()));
		} else if (e instanceof InvalidDescriptionException) {
			System.out.println(String.format("OOPS!!! Invalid input! Make sure your %s has a description and required data after /at for Event or /by for Deadline.\n", e.getMessage()));
		} else if (e instanceof ParseException) {
			System.out.println(String.format("Please write your deadline/event date in this format: dd/MM/yyyy HH:mm, example: 02/08/2019 14:30\n", e.getMessage()));
		} else {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public String toString() {
		return "Command ";
	}
}
