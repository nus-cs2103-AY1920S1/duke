import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Duke {
	private static final String taskListArchivalPath = "duke.txt";

    public static void main(String[] args) {
    	//print out the current tasks in the archival file
    	File taskListArchivalFile = new File(taskListArchivalPath);
		ArrayList<Task> taskArrayList = new ArrayList<>();

    	if (taskListArchivalFile.exists()) {
			readAndPrintArchivedList(taskListArchivalFile);
			readAndUpdateArchivalList(taskArrayList,taskListArchivalFile);
		} else {
    		//create new file at archivalpath
			try {
				taskListArchivalFile.createNewFile();
			} catch (IOException ex) {
				System.out.println(ex);
			}
		}

		Scanner scanner = new Scanner(System.in);
		String userInput = scanner.nextLine();
		String command = new Scanner(userInput).next();

		while (!command.equals("bye")) {
			try {
				processInput(userInput, taskArrayList, taskListArchivalFile);
			} catch (DukeException e) {
				System.out.println(e.getMessage());
			}
			userInput = scanner.nextLine();
			command = new Scanner(userInput).next();
		}
	}

	private static void readAndUpdateArchivalList(ArrayList<Task> taskArrayList, File file) {
		Scanner fileScanner;
		try {
			fileScanner = new Scanner(file);
			while(fileScanner.hasNextLine()) {
				readAndAddArchivalEvent(fileScanner.nextLine(), taskArrayList);
			}
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
	}

	private static void readAndAddArchivalEvent(String eventString, ArrayList<Task> taskArrayList) {
		Task taskToBeAdded;
		try {
			taskToBeAdded = decryptEventString(eventString);
			taskArrayList.add(taskToBeAdded);
		} catch (DukeException ex) {
			//bad practice should continue to throw up
			System.out.println(ex);
		}
	}

	private static Task decryptEventString(String eventString) throws DukeException {
    	String[] taskDetails = eventString.split("|");
    	Task taskToReturn;
    	String taskType = taskDetails[0];
    	Boolean isCompleted = taskDetails[1].equals("1");
    	switch(taskType) {
		case "T":
			taskToReturn = new ToDo(taskDetails[2].trim());
			updateTaskCompletionStatus(isCompleted, taskToReturn);
			return taskToReturn;
		case "E":
			taskToReturn = new Event(taskDetails[2].trim(), taskDetails[3].trim());
			updateTaskCompletionStatus(isCompleted, taskToReturn);
			return taskToReturn;
		case "D":
			taskToReturn = new DeadLine(taskDetails[2].trim(), taskDetails[3].trim());
			updateTaskCompletionStatus(isCompleted, taskToReturn);
			return taskToReturn;
		default:
			throw new DukeException("Event Type is not recognised");
		}
	}

	private static void updateTaskCompletionStatus(boolean status, Task task) {
    	if (status) {
    		task.markCompleted();
		} else {
    		task.markIncomplete();
		}
	}

	private static void readAndPrintArchivedList(File file) {
    	Scanner fileScanner;
    	try {
			fileScanner = new Scanner(file);
			while (fileScanner.hasNextLine()) {
				System.out.println(fileScanner.nextLine());
			}
		} catch (FileNotFoundException ex) {
    		System.out.println(ex.getMessage());
		}
	}

	private static void processInput(String userInput, ArrayList<Task> taskArrayList, File taskListArchivalFile) throws DukeException {
		Scanner inLineScanner = new Scanner(userInput);
		String command = inLineScanner.next();
		if (userInput.equals("list")) {
			printTaskList(taskArrayList);
		} else if (command.equals("done")) {
			Integer taskNumber = Integer.parseInt(inLineScanner.next()) - 1;
			Task task = taskArrayList.get(taskNumber);
			task.markCompleted();
			StringBuilder sb = new StringBuilder("Nice! I've marked this task as done:\n");
			sb.append("    ");
			sb.append(task.toString());
			System.out.println(sb.toString());
		} else if (command.equals("bye")) {
			System.out.print("Bye. Hope to see you again soon!");
		} else if (command.equals("todo")) {
			if(!inLineScanner.hasNext()) {
				throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
			} else {
				String taskDescription = inLineScanner.nextLine().trim();
				ToDo todo = new ToDo(taskDescription);
				addToTaskList(todo, taskArrayList, taskListArchivalFile);
			}
		} else if (command.equals("deadline")) {
			if (!inLineScanner.hasNext()) {
				//check for deadline description
				throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
			}
			StringBuilder deadLineName = new StringBuilder();
			StringBuilder deadLineDate = new StringBuilder();
			String currentWord;
			Boolean reachedDatePortion = false;
			while(inLineScanner.hasNext()) {
				currentWord = inLineScanner.next();
				if (currentWord.equals("/by")) {
					//set switch to true but do not append
					reachedDatePortion = true;
				} else if (reachedDatePortion == false) {
					deadLineName.append(currentWord);
					deadLineName.append(" ");
				} else {
					deadLineDate.append(currentWord);
					deadLineDate.append(" ");
				}
			}
			if (deadLineDate.length() == 0) {
				throw new DukeException("no date provided");
			}
			DeadLine deadline = new DeadLine(deadLineName.toString().trim(), deadLineDate.toString().trim());
			addToTaskList(deadline, taskArrayList, taskListArchivalFile);
		} else if (command.equals("event")) {
			if (!inLineScanner.hasNext()) {
				//check for deadline description
				throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
			}
			StringBuilder eventName = new StringBuilder();
			StringBuilder eventDate = new StringBuilder();
			String currentWord;
			Boolean reachedDatePortion = false;
			while(inLineScanner.hasNext()) {
				currentWord = inLineScanner.next();
				if (currentWord.equals("/at")) {
					//set switch to true but do not append
					reachedDatePortion = true;
				} else if (reachedDatePortion == false) {
					eventName.append(currentWord);
					eventName.append(" ");
				} else {
					eventDate.append(currentWord);
					eventDate.append(" ");
				}
			}
			if (eventDate.length() == 0) {
				throw new DukeException("no date provided");
			}
			Event event = new Event(eventName.toString().trim(), eventDate.toString().trim());
			addToTaskList(event, taskArrayList, taskListArchivalFile);
		} else if (command.equals("delete")) {
			if (!inLineScanner.hasNext()) {
				throw new DukeException("no number provided");
			}
			Integer taskToDeleteIndex = Integer.parseInt(inLineScanner.next());
			Task taskToBeDeleted;
			try {
				taskToBeDeleted = taskArrayList.remove(taskToDeleteIndex - 1);
			} catch (Exception e) {
				throw new DukeException(e.getMessage());
			}
			System.out.println("Noted. I've removed this task:");
			System.out.println("    " + taskToBeDeleted);
			System.out.printf("Now you have %d tasks in the list.\n", taskArrayList.size());
		} else {
				//command not found
				throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
		}
    }

    private static void addToTaskList(Task taskToAdd, ArrayList<Task> taskArrayList, File archivalFile) {
		taskArrayList.add(taskToAdd);
		updateArchivalFile(archivalFile, taskArrayList);
		alertLatestTaskAdded(taskArrayList);
	}

	private static void updateArchivalFile(File file, ArrayList<Task> taskArrayList) {
    	FileWriter fileWriter;
    	try {
			fileWriter = new FileWriter(file);
			for (Task task : taskArrayList) {
				fileWriter.write(task.getArchivalText() + "\n");
			}
			fileWriter.close();
		} catch (IOException ex) {
    		System.out.println(ex.getMessage());
		}
	}

    private static void alertLatestTaskAdded(ArrayList<Task> taskArrayList) {
		System.out.println("Got it. I've added this task: ");
		System.out.println(taskArrayList.get(taskArrayList.size() -1));
		System.out.printf("Now you have %d tasks in the list.\n", taskArrayList.size());
	}

    private static void printTaskList(ArrayList<Task> taskArrayList) {
    	for (int i = 0; i < taskArrayList.size(); i++) {
    		System.out.printf("%d.%s\n", i + 1, taskArrayList.get(i).toString());
		}
	}
}

