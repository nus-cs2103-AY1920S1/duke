import java.io.IOException;
import java.util.ArrayList;

public class Parser {

	Ui ui;
	TaskList tasks;
	DukeReadFile rf;
	DukeWriteFile wf;
	boolean isEndLoop;


	public Parser(Ui ui, TaskList tasks, DukeReadFile rf, DukeWriteFile wf) {

		this.ui = ui;
		this.tasks = tasks;
		this.rf = rf;
		this.wf = wf;
		isEndLoop = false;
	}


	/**
	 * Main bullk of the program where evaluation of the input
	 * by the user takes place.
	 *
	 * @param input Input of the tasks of the user.
	 */
	public void evaluate(String input) {
		int counter = 0;
		if (input.equals("bye")) {
			System.out.println(ui.OUTRO);
			isEndLoop = true;
		} else if (input.equals("list")) {
			int itemNumber = 1;
			counter = tasks.getCounter();
			if (counter == 0) {
				System.out.println(ui.NO_TASK);
			} else {
				System.out.println(ui.OUTPUT_TASK_LIST);
				for (int i = 0; i < counter; i++) {
					System.out.println(itemNumber + "." + tasks.getTask(i).toString());
					itemNumber++;
				}
				System.out.println(ui.BORDER);
			}

		} else if (input.startsWith("done")) {
			try {
				String[] taskDone = input.split(" ");
				if (taskDone.length == 1) {
					throw new DukeException(ui.WRONG_OP);
				}
				if (taskDone.length > 2) {
					throw new DukeInvalidArgumentException(ui.DONE_FORMAT, input);
				}

				int taskIndex = Integer.parseInt(taskDone[1]);
				tasks.getTask(taskIndex - 1).markAsDone();
				System.out.println(ui.MARK_DONE);
				System.out.println(tasks.getTask(taskIndex - 1).toString() + "\n" + ui.BORDER);
				wf.writeToFile(Duke.writeFile(tasks.getTaskList()));

			} catch (DukeInvalidArgumentException e) {

				System.out.println(ui.BORDER + "\n" + e + "\n" + ui.BORDER);

			} catch (DukeException e) {

				System.out.println(e);

			} catch (IndexOutOfBoundsException e) {

				System.out.println(ui.BORDER + "\nTask number not found! Try again!\n" + ui.BORDER);
			} catch (NumberFormatException e) {

				System.out.println(ui.BORDER + "\nOOPS!! Please input a task number!\n" + ui.BORDER);
			} catch (IOException e) {

				System.out.println(ui.BORDER + "\n" + e + "\n" + ui.BORDER);
			}
		} else if (input.startsWith("deadline")) {
			try {
				if (input.length() < 9) {
					throw new DukeException(ui.EMPTY_INPUT);
				}

				String[] deadLineDate = input.substring(9).split(" /by ");
				if (deadLineDate.length != 2) {
					throw new DukeInvalidArgumentException(ui.DEADLINE_FORMAT,
							input);
				}
				String taskD = deadLineDate[0];
				String dateD = deadLineDate[1];
				Deadline newDeadLine = new Deadline(taskD, dateD);
				tasks.addTask(newDeadLine);
				counter = tasks.getCounter();
				System.out.println(ui.BORDER + "\nGot it. I've added this task:");
				System.out.println(newDeadLine.toString());
				System.out.println("Now you have " + counter + " tasks in the list.\n" + ui.BORDER);
				wf.appendToFile("D/" + newDeadLine.getStatus() + "/" +
						newDeadLine.getDescription() + "/" + newDeadLine.getDeadline() + "\n");

			} catch (DukeInvalidArgumentException e) {

				System.out.println(ui.BORDER + "\n" + e + "\n" + ui.BORDER);

			} catch (DukeException e) {

				System.out.println(ui.BORDER + "\n" + e + "\n" + ui.BORDER);
			} catch (IOException e) {

				System.out.println(ui.BORDER + "\n" + e + "\n" + ui.BORDER);
			}

		} else if (input.startsWith("event")) {

			try {
				if (input.length() < 6) {
					throw new DukeException(ui.EMPTY_INPUT);
				}

				String[] eventDate = input.substring(6).split(" /at ");
				if (eventDate.length != 2) {
					throw new DukeInvalidArgumentException("OOPS!! Wrong format! Format: event [Task] /at [time]",
							input);
				}
				String taskE = eventDate[0];
				String dateE = eventDate[1];
				Event newEvent = new Event(taskE, dateE);
				tasks.addTask(newEvent);
				counter = tasks.getCounter();
				System.out.println(ui.BORDER + "\nGot it. I've added this task:");
				System.out.println(newEvent.toString());
				System.out.println("Now you have " + counter + " tasks in the list.\n" + ui.BORDER);
				wf.appendToFile("E/" + newEvent.getStatus() + "/" +
						newEvent.getDescription() + "/" + newEvent.getVenue() + "\n");

			} catch (DukeInvalidArgumentException e) {

				System.out.println(ui.BORDER + "\n" + e + "\n" + ui.BORDER);

			} catch (DukeException e) {

				System.out.println(ui.BORDER + "\n" + e + "\n" + ui.BORDER);
			} catch (IOException e) {

				System.out.println(ui.BORDER + "\n" + e + "\n" + ui.BORDER);
			}

		} else if (input.startsWith("todo")) {
			try {
				if (input.length() < 5) {
					throw new DukeException(ui.EMPTY_INPUT);
				}

				String taskToDo = input.substring(5);
				Todo newToDo = new Todo(taskToDo);
				tasks.addTask(newToDo);
				counter = tasks.getCounter();
				System.out.println(ui.BORDER + "\nGot it. I've added this task:");
				System.out.println(newToDo.toString());
				System.out.println("Now you have " + counter + " tasks in the list.\n" + ui.BORDER);
				wf.appendToFile("T/" + newToDo.getStatus() + "/" +
						newToDo.getDescription() + "\n");

			} catch (DukeException e) {
				System.out.println(ui.BORDER + "\n" + e + "\n" + ui.BORDER);
			} catch (IOException e) {

				System.out.println(ui.BORDER + "\n" + e + "\n" + ui.BORDER);
			}

		} else if (input.startsWith("delete")) {
			try {
				String[] taskDelete = input.split(" ");
				if (taskDelete.length == 1) {
					throw new DukeException("☹ OOPS!!! Which task would you like to delete?");
				}

				if (taskDelete.length > 2) {
					throw new DukeInvalidArgumentException(ui.DELETE_FORMAT, input);
				}

				int taskIndex = Integer.parseInt(taskDelete[1]);
				String removedTask = tasks.getTask(taskIndex - 1).toString();
				tasks.deleteTask(taskIndex - 1);
				System.out.println(ui.BORDER + "\nNoted. I've removed this task:");
				System.out.println(removedTask);

				counter = tasks.getCounter();
				System.out.println("Now you have " + counter + " tasks in the list.\n" + ui.BORDER);
				wf.writeToFile(Duke.writeFile(tasks.getTaskList()));

			} catch (DukeInvalidArgumentException e) {

				System.out.println(ui.BORDER + "\n" + e + "\n" + ui.BORDER);

			} catch (NumberFormatException e) {

				System.out.println(ui.BORDER + "\nOOPS!! Please input a task number!\n" + ui.BORDER);

			} catch (IndexOutOfBoundsException e) {

				System.out.println(ui.BORDER + "\nTask number not found! Try again!\n" + ui.BORDER);

			} catch (DukeException e) {

				System.out.println(ui.BORDER + "\n" + e + "\n" + ui.BORDER);

			} catch (IOException e) {

				System.out.println(ui.BORDER + "\n" + e + "\n" + ui.BORDER);

			}
		} else {
			try {
				throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
			} catch (DukeException e) {
				System.out.println(ui.BORDER + "\n" + e + "\n" + ui.BORDER);
			}
		}
	}

	/**
	 * Returns a boolean true when the user has entered
	 * an input 'bye'.
	 *
	 * @return Boolean
	 */
	public boolean isExit() {
		
		return isEndLoop;
	}
}
