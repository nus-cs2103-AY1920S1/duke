package duke.ui;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a Ui
 * Contains functions to interact with user.
 */
public class Ui {
	
	/**
	 * Generate welcome message upon start up of Duke.
	 *
	 * @return String with welcome message.
	 */
	public String getWelcome() {
		StringBuilder welcomeMessage = new StringBuilder("Hello! I'm Duke, what can I do for you?\n");
		welcomeMessage.append("\n");
		welcomeMessage.append("For help, please enter \"HELP\" for commands.\n");
		welcomeMessage.append("For help on commands, please enter \"HELP <COMMAND>\"\n");
		return welcomeMessage.toString();
	}
	
	/**
	 * Generate goodbye message after Duke shuts down.
	 *
	 * @return String with bye message.
	 */
	public String getBye() {
		return "Bye. Hope to see you again soon!";
	}
	
	/**
	 * Generate message when task is added to task list.
	 *
	 * @return String with message with information of added task and number of tasks in list.
	 */
	public String getAddedTaskMessage(char firstAlphabet, boolean isDone, String taskDescription, int numberOfItems) {
		StringBuilder addedTaskMessage = new StringBuilder("Got it. I've added this task: \n");
		addedTaskMessage.append("\t[").append(firstAlphabet).append("] ");
		addedTaskMessage.append(taskDescription).append("\n");
		addedTaskMessage.append("Now you have ").append(numberOfItems).append(" tasks in the list.\n");
		return addedTaskMessage.toString();
	}
	
	/**
	 * Generate message to print list of tasks in task list.
	 *
	 * @return String with list of tasks in task list.
	 */
	public String getList(ArrayList<String> listToPrint) {
		String output = "";
		
		boolean isListSizePositive = listToPrint.size() > 0;
		
		if (!isListSizePositive) {
			output = "There are currently no items in the list.\n";
		}
		
		if (isListSizePositive) {
			StringBuilder listOfTasksToPrint = new StringBuilder("Here are the tasks in your list:\n");
			for (int i = 0; i < listToPrint.size(); i++) {
				listOfTasksToPrint.append("\t").append(i + 1).append(".").append(listToPrint.get(i)).append("\n");
			}
			output = listOfTasksToPrint.toString();
		}
		return output;
	}
	
	/**
	 * Generate message when task is deleted from task list.
	 *
	 * @return String with message with information of deleted task and number of tasks in list.
	 */
	public String getDeletedTask(Task t, int taskCount) {
		StringBuilder deletedTaskMessage = new StringBuilder("Noted. I've removed this task:\n");
		deletedTaskMessage.append("\t").append(t.printTask()).append("\n");
		deletedTaskMessage.append("Now you have ").append(taskCount).append(" tasks in the list.");
		return deletedTaskMessage.toString();
	}
	
	/**
	 * Generate message when task is set to done in task list.
	 *
	 * @return String with message with information of task set to done and number of tasks in list.
	 */
	public String getDoneTask(Task t) {
		StringBuilder doneTaskMessage = new StringBuilder("Nice! I've marked this task as done:\n");
		doneTaskMessage.append("\t").append(t.printTask());
		return doneTaskMessage.toString();
	}
	
	/**
	 * Generate message when task number input by user is invalid.
	 *
	 * @return String with message with information of incorrect task numbers.
	 */
	public String getIndexError() {
		return "Invalid file or tasks. Please check again.";
	}
	
	/**
	 * Generate message to give matching task with given keyword.
	 *
	 * @return String with message with information of found task and number of tasks in list.
	 */
	public String getMatchingTaskList(ArrayList<String> listFound) {
		if (listFound.size() == 0) {
			return "No matching result from your list.";
		}
		
		StringBuilder matchingTaskResult = new StringBuilder("Here are the matching tasks in your list:\n");
		for (int i = 0; i < listFound.size(); i++) {
			matchingTaskResult.append("\t").append(i + 1).append(".").append(listFound.get(i)).append("\n");
		}
		return matchingTaskResult.toString();
	}
	
	/**
	 * Generate message when error in loading tasks from disk.
	 *
	 * @return String with message showing error in loading file.
	 */
	public String getLoadingError() {
		return "Error in Loading files\n";
	}
	
	/**
	 * Generate message when error in saving file to disk.
	 *
	 * @return String with message of error saving file to disk.
	 */
	public String getSavingError() {
		return "Error in saving to disk.";
	}
	
	public String getNoItemsInList() {
		return "There are no items in list.";
	}
}
