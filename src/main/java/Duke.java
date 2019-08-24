import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
		printLogo();
		greetUser();

		ArrayList<Task> tasks = new ArrayList<Task>();
		Scanner scanner = new Scanner(System.in);

		readAndExecuteCommands(scanner, tasks);
	}

	public static void readAndExecuteCommands(Scanner scanner, ArrayList<Task> tasks) {
        while (scanner.hasNextLine()) {
            try {
                String command = scanner.nextLine().replaceAll("\\s+", " ");;
                if (command.equals("bye")) {
                    exit();
                    scanner.close();
                    break;
                } else if (command.equals("list")) {
                    list(tasks);
                } else if (command.startsWith("delete")) {
                    String[] commandSplit = command.split(" ");
                    if (commandSplit.length < 2) {
                        throw new DukeException("\u2639 OOPS!!! Please specify a task to delete.");
                    }
                    int index = Integer.parseInt(commandSplit[1]);
                    if (index > tasks.size()) {
                    	throw new DukeException("\u2639 OOPS!!! There is no such task in the list to delete.");
					}
                    delete(index, tasks);
                } else if (command.startsWith("done")) {
                    String[] commandSplit = command.split(" ");
                    if (commandSplit.length < 2) {
                        throw new DukeException("\u2639 OOPS!!! Please specify the task that has been done.");
                    }
                    int index = Integer.parseInt(commandSplit[1]);
                    if (index > tasks.size()) {
                    	throw new DukeException("\u2639 OOPS!!! There is no such task in the list to mark as done.");
					}
                    done(index, tasks);
                } else {
                    addTask(command, tasks);
                }
            } catch (DukeException exception) {
				printExceptionMessage(exception);
            }
        }
    }

    public static void printLogo() {
		String logo = " ____        _        \n"
				+ "|  _ \\ _   _| | _____ \n"
				+ "| | | | | | | |/ / _ \\\n"
				+ "| |_| | |_| |   <  __/\n"
				+ "|____/ \\__,_|_|\\_\\___|\n";
		System.out.println("Hello from\n" + logo);
	}

    public static void greetUser() {
        printLine();
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?");
        printLine();
    }

    public static void list(ArrayList<Task> tasks) {
        printLine();
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println("\t " + i + ". " + tasks.get(i - 1));
        }
        printLine();
    }

    public static void delete(int index, ArrayList<Task> tasks) {
        Task deletedTask = tasks.get(index - 1);
        tasks.remove(index - 1);
        printLine();
        System.out.println("\t Noted. I've removed this task:");
        System.out.print("\t \t");
        System.out.println(deletedTask);
        System.out.println("\t Now you have " + tasks.size() + (tasks.size() == 1 ? " task " : " tasks ")
                + "in the " + "list.");
        printLine();
    }

    public static void done(int index, ArrayList<Task> tasks) {
        Task doneTask = tasks.get(index - 1);
        doneTask.markAsDone();
        printLine();
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.print("\t \t");
        System.out.println(doneTask);
        printLine();
    }

    public static void addTask(String command, ArrayList<Task> tasks) throws DukeException {
        Task newTask;
        if (command.startsWith("todo")) {
            String taskDetailsString = command.replaceFirst("todo", "");
            if (taskDetailsString.trim().length() == 0) {
                throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
            }
            newTask = new Todo(taskDetailsString);
        } else {
            String[] taskDetails;
            if (command.startsWith("deadline")) {
                String taskDetailsString = command.replaceFirst("deadline", "");
				taskDetails = taskDetailsString.split("/by");
                if (taskDetailsString.length() == 0 || taskDetails[0].trim().length() == 0) {
                    throw new DukeException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
                }
                if (taskDetails.length < 2 || taskDetails[1].trim().length() == 0) {
                    throw new DukeException("\u2639 OOPS!!! The date/time of a deadline cannot be empty.");
                }
                String taskDescription = taskDetails[0].trim();
                String[] taskSpecifics = taskDetails[1].trim().split(" ");
                String rawTaskDate = taskSpecifics[0];
                String rawTaskTime;
                if (taskSpecifics.length < 2 || taskSpecifics[1].trim().length() == 0) {
                	rawTaskTime = null;
				} else {
                	rawTaskTime = taskSpecifics[1];
				}
                Date taskDate = new Date(rawTaskDate);
                Time taskTime = new Time(rawTaskTime);
                newTask = new Deadline(taskDescription, taskDate, taskTime);
            } else if (command.startsWith("event")){
                String taskDetailsString = command.replaceFirst("event", "");
				taskDetails = taskDetailsString.split("/at");
                if (taskDetailsString.length() == 0 || taskDetails[0].trim().length() == 0) {
					throw new DukeException("\u2639 OOPS!!! The description of an event cannot be empty.");
				}
                if (taskDetails.length < 2 || taskDetails[1].trim().length() == 0) {
                    throw new DukeException("\u2639 OOPS!!! The date/time of an event cannot be empty.");
                }
				String taskDescription = taskDetails[0].trim();
				String[] taskSpecifics = taskDetails[1].trim().split("/to");
				if (taskSpecifics[0].trim().length() == 0) {
					throw new DukeException("\u2639 OOPS!!! The starting date/time of an event cannot be empty.");
				} else {
					String[] rawStarts = taskSpecifics[0].trim().split(" ");
					String rawStartDate = rawStarts[0];
					String rawStartTime = null;
					if (rawStarts.length >= 2 && rawStarts[1].trim().length() != 0) {
						rawStartTime = rawStarts[1];
					}
					String rawEndDate = null;
					String rawEndTime = null;
					if (taskSpecifics.length > 1) {
						String[] rawEnds = taskSpecifics[1].trim().split(" ");
						rawEndDate = rawEnds[0];
						if (rawEnds.length >= 2 && rawEnds[1].trim().length() != 0) {
							rawEndTime = rawEnds[1];
						}
					}
					Date startDate = new Date(rawStartDate);
					Time startTime = new Time(rawStartTime);
					Date endDate = new Date(rawEndDate);
					Time endTime = new Time(rawEndTime);
					newTask = new Event(taskDescription, startDate, startTime, endDate, endTime);
				}
            } else {
                throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        tasks.add(newTask);
		printLine();
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t \t " + newTask);
        System.out.println("\t Now you have " + tasks.size() + (tasks.size() == 1 ? " task " : " tasks ")
                + "in the " + "list.");
        printLine();
    }

    public static void printExceptionMessage(DukeException exception) {
		StringBuilder sb = new StringBuilder();
		sb.append("\t ---------------------------------------------------------- \n");
		sb.append("\t " + exception.getMessage() + "\n");
		sb.append("\t ---------------------------------------------------------- \n");
		System.out.print(sb.toString());
	}

    public static void exit() {
        printLine();
        System.out.println("\t Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printLine() {
        System.out.println("\t ----------------------------------------------------------");
    }

}
