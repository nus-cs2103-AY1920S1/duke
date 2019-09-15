package duke.parser;

import duke.command.ListCommand;
import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.DeleteCommand;
import duke.command.CommandNotFoundException;
import duke.command.FindCommand;
import duke.command.DoneCommand;
import duke.command.CommandType;
import duke.command.Command;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a parser to parse user input.
 * Has parse functions for user input and file.
 */
public class Parser {
	private static ArrayList<String> commandList;
	
	/**
	 * Initialize the parser,
	 * by preparing command list to cross check if command input by user/file
	 * is valid.
	 */
	public static void initialize() {
		commandList = new ArrayList<>();
		commandList.add("DEADLINE");
		commandList.add("TODO");
		commandList.add("EVENT");
		commandList.add("DELETE");
		commandList.add("DONE");
		commandList.add("LIST");
		commandList.add("BYE");
		commandList.add("FIND");
	}
	
	/**
	 * Checks if user input follows format of: command description time
	 * Throws CommandNotFoundException if command is not found.
	 * Return user command in command object.
	 *
	 * @param input String input of command from user.
	 * @return Command object of the user.
	 * @throws CommandNotFoundException If command entered by user is not found.
	 */
	public static Command parse(String input) throws CommandNotFoundException, IncorrectNumberOfArgumentsException {
		boolean isCommandFound = isCommandValid(input);
		if (!isCommandFound) {
			throw new CommandNotFoundException();
		}
		String commandName = getCommandName(input);
		String commandType = classifyCommand(commandName);
		CommandType type = CommandType.valueOf(commandType);
		
		Task task;
		Command command;
		
		boolean isCommandTypeAdd = (type == CommandType.ADD);
		boolean isCommandTypeBye = (type == CommandType.BYE);
		boolean isCommandTypeDelete = (type == CommandType.DELETE);
		boolean isCommandTypeDone = (type == CommandType.DONE);
		boolean isCommandTypeList = (type == CommandType.LIST);
		boolean isCommandTypeFind = (type == CommandType.FIND);
		
		if (isCommandTypeAdd) {
			boolean isCommandToDo = commandName.equals("TODO");
			boolean isCommandDeadline = commandName.equals("DEADLINE");
			boolean isCommandEvent = commandName.equals("EVENT");
			
			if (isCommandToDo) {
				char firstCharOfCommand = commandName.toUpperCase().charAt(0);
				String taskDescription = input.substring("Todo".length()).trim();
				
				// Empty task description
				if(taskDescription.equals("")){
					throw new IncorrectNumberOfArgumentsException();
				}
				task = new ToDo(firstCharOfCommand, taskDescription, false);
				
			} else if (isCommandDeadline) {
				ArrayList<String> taskInfo = parseTaskInformationAndDate(input, commandName, "by ");
				task = new Deadline(commandName.toUpperCase().charAt(0), taskInfo.get(1), false, taskInfo.get(0));
				
			} else if (isCommandEvent) {
				ArrayList<String> taskInfo = parseTaskInformationAndDate(input, commandName, "at ");
				task = new Event(commandName.toUpperCase().charAt(0), taskInfo.get(1), false, taskInfo.get(0));
				
			} else {
				throw new CommandNotFoundException();
			}
			command = new AddCommand(commandType, task);
			
		} else if (isCommandTypeBye) {
			task = new Task();
			command = new ByeCommand(commandName, task);
			
		} else if (isCommandTypeDelete) {
			String subsequent = input.substring("Delete ".length()).trim();
			int numberToDelete = Integer.parseInt(subsequent);
			task = new Task();
			command = new DeleteCommand(commandName, task, numberToDelete);
			
		} else if (isCommandTypeDone) {
			String subsequent = input.substring("Done ".length()).trim();
			int numberDone = Integer.parseInt(subsequent);
			task = new Task();
			command = new DoneCommand(commandName, task, numberDone);
			
		} else if (isCommandTypeList) {
			command = new ListCommand(commandName);
			
		} else if (isCommandTypeFind) {
			boolean isNoKeywordToFind = input.toLowerCase().equals("find");
			if (isNoKeywordToFind) {
				throw new IncorrectNumberOfArgumentsException();
			}
			
			String keyword = input.substring("Find ".length()).trim();
			boolean isKeywordBlank = keyword.equals("");
			if (isKeywordBlank) {
				throw new IncorrectNumberOfArgumentsException();
			}
			command = new FindCommand(commandName, keyword, new Task());
			
		} else {
			
			throw new CommandNotFoundException();
		}
		
		return command;
	}
	
	/**
	 * Checks if a given user input command is valid.
	 *
	 * @param userInput String containing user input of the command.
	 * @return boolean indicating true if command is valid, else false.
	 */
	private static boolean isCommandValid(String userInput) {
		boolean isCommandValid = false;
		for (String s : commandList) {
			if (userInput.toUpperCase().startsWith(s)) {
				isCommandValid = true;
			}
		}
		return isCommandValid;
	}
	
	/**
	 * Classifies the given command in string format to the program defined command object name.
	 *
	 * @param userInput String containing input of the command.
	 * @return String variable of the name of command in program defined command objects.
	 */
	private static String getCommandName(String userInput) {
		String commandName = "";
		for (String s : commandList) {
			if (userInput.toUpperCase().startsWith(s)) {
				commandName = s;
				break;
			}
		}
		return commandName;
	}
	
	/**
	 * Classifies the given command in string format to the program defined command object name.
	 *
	 * @param command String containing input of the command.
	 * @return String variable of the name of command in program defined command objects.
	 */
	private static String classifyCommand(String command) {
		boolean isAddTypeCommand = (command.equals("TODO") || command.equals("DEADLINE") || command.equals("EVENT"));
		if (isAddTypeCommand) {
			return "ADD";
		}
		return command;
	}
	
	/**
	 * Parse a string of task description and date time into separate strings.
	 *
	 * @param userInput                String containing input of the task description, command name and separator.
	 * @param commandName              String variable containing the name of command.
	 * @param separatorAfterFirstSlash String containing the separator of the time portion.
	 * @return ArrayList of Strings containing the date, then task description.
	 */
	private static ArrayList<String> parseTaskInformationAndDate(String userInput, String commandName,
																 String separatorAfterFirstSlash) {
		ArrayList<String> taskInformation = new ArrayList<>();
		
		// Get substring after the task Description in the user input
		String stringAfterTaskDescription = userInput.substring(commandName.length());
		int firstIndexSlash = stringAfterTaskDescription.indexOf('/');
		assert firstIndexSlash != -1 : "slash should exist in the string";
		assert firstIndexSlash < stringAfterTaskDescription.length() : "index of slash should be in string";
		
		String date = stringAfterTaskDescription.substring(firstIndexSlash + separatorAfterFirstSlash.length() + 1);
		String taskDescription = stringAfterTaskDescription.substring(0, firstIndexSlash).trim();
		
		taskInformation.add(date);
		taskInformation.add(taskDescription);
		return taskInformation;
	}
	
	/**
	 * Checks if file input follows format of: command description time
	 * Throws IncorrectFileFormatException if command is not found.
	 * Return task in task object.
	 *
	 * @param input String input of command from user.
	 * @return task object of the file.
	 * @throws IncorrectFileFormatException If input entered by user is not recognized.
	 */
	public static Task parseFromFile(String input, Ui ui) throws IncorrectFileFormatException {
		Task task = new Task();
		boolean isTaskDone = false;
		
		boolean isStartWithChar = input.startsWith("[D]") || input.startsWith("[E]") || input.startsWith("[T]");
		if (!isStartWithChar) {
			throw new IncorrectFileFormatException(ui.getLoadingError());
		}
		
		String subsequentString = input.substring("[X]".length());
		try {
			isTaskDone = isTaskDone(subsequentString, ui);
		} catch (IncorrectFileFormatException e) {
			throw new IncorrectFileFormatException("Error");
		}
		
		// Move to after cross/tick and space
		subsequentString = subsequentString.substring(4).trim();
		
		boolean isStartWithT = input.startsWith("[T]");
		boolean isStartWithDOrE = input.startsWith("[D]") || input.startsWith("[E]");
		
		if (isStartWithT) {
			task = new ToDo('T', subsequentString, isTaskDone);
		} else if (isStartWithDOrE) {
			int slashIndex = subsequentString.indexOf('/');
			if (slashIndex == -1) {
				throw new IncorrectFileFormatException(ui.getLoadingError());
			}
			
			String taskDescription = subsequentString.substring(0, slashIndex).trim();
			String date = subsequentString.substring(slashIndex + "xx ".length() + 1);
			
			boolean isStartWithD = input.startsWith("[D]");
			boolean isStartWithE = input.startsWith("[E]");
			if (isStartWithD) {
				task = new Deadline('D', taskDescription, isTaskDone, date);
			} else if (isStartWithE) {
				task = new Event('E', taskDescription, isTaskDone, date);
			} else {
				throw new IncorrectFileFormatException(ui.getLoadingError());
			}
		}
		return task;
	}
	
	
	/**
	 * Checks if file input follows format of: command description time
	 * Throws IncorrectFileFormatException if command is not found.
	 * Return task in task object.
	 *
	 * @param subsequentString String input from file to check if task is indicated as done.
	 * @param ui               UI interface to show error message if incorrect format.
	 * @return Boolean indicating true if input indicates true, else false.
	 * @throws IncorrectFileFormatException If input entered by user is not recognized.
	 */
	private static boolean isTaskDone(String subsequentString, Ui ui) throws IncorrectFileFormatException {
		boolean isTaskDone = false;
		
		if (subsequentString.startsWith("[1]")) {
			isTaskDone = true;
		} else if (subsequentString.startsWith("[0]")) {
			isTaskDone = false;
		} else {
			throw new IncorrectFileFormatException(ui.getLoadingError());
		}
		return isTaskDone;
	}
}
