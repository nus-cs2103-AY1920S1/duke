import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

public class Duke {
	static Scanner scanner = new Scanner(System.in);
	static Parser inputParser = new Parser();
	static TaskList taskList = new TaskList("DukeSaveFile.txt");
	static String logo = " ____        _        \n"
			+ "|  _ \\ _   _| | _____ \n"
			+ "| | | | | | | |/ / _ \\\n"
			+ "| |_| | |_| |   <  __/\n"
			+ "|____/ \\__,_|_|\\_\\___|\n";
	static String lineBreak = "___________________________________________________________";
	static boolean takingInput = true;
	
	public static void run() {
		new Output(logo).print();
		Output.setHeader(lineBreak);
		Output.setFooter(lineBreak);
		Output.setLeftBorder("    ");
		Output.setLeftIndent(1);
		new Output("Hello! I'm Duke :)", "What can I do for you?").print();
		while (takingInput) {
			executeCommand(inputParser, getUserInput());
		}
		try {
			taskList.save();
		} catch (IOException e) {
			//
		}
		new Output("Bye. Hope to see you again soon!").print();
	}

	public static String getUserInput() {
		return scanner.nextLine();
	}

	public static void executeCommand(Parser parser, String userInput) {
		try {
			Pair<Command, ArrayList<String>> pair = parser.parse(userInput);
			Command command = pair.left;
			ArrayList<String> parameter = pair.right;
			switch(command) {
				case EXIT:
					takingInput = false;
					break;
				case LIST:
					Output listOutput = new Output("Here are the tasks in your list:");
					int count = 0;
					for (Task task : taskList.list()) {
						count++;
						listOutput.addLines(count + "." + task.toString());
					}
					listOutput.print();
					break;
				case DONE:
					new Output("Nice! I've marked this task as done:",
							"  " + taskList.complete(parameter.get(0))).print();
					break;
				case DELETE:
					new Output("Alright. I've removed this task from your list:",
							"  " + taskList.delete(parameter.get(0)),
							"Now you have " + taskList.size() + " tasks in the list.").print();
					break;
				case TODO:
					new Output("Got it! I've added this task to the list:",
								"  " + taskList.addTask(new ToDo(parameter.get(0))),
								"Now you have " + taskList.size() + " tasks in the list.").print();
					break;
				case DEADLINE:;
					new Output("Got it! I've added this task to the list:",
							"  " + taskList.addTask(new Deadline(parameter.get(0), parameter.get(1))),
							"Now you have " + taskList.size() + " tasks in the list.").print();
					break;
				case EVENT:
					new Output("Got it! I've added this task to the list:",
							"  " + taskList.addTask(new Event(parameter.get(0), parameter.get(1))),
							"Now you have " + taskList.size() + " tasks in the list.").print();
					break;
				default:
					break;
			}
		} catch (DukeException except) {
			new Output("☹ OOPS!!! " + except.getMessage()).print();
		}
	}

	public static void main(String[] args) {
		run();
	}
}
