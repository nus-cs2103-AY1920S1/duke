package weomucat.duke.ui;

import weomucat.duke.exception.DukeException;
import weomucat.duke.task.Task;
import weomucat.duke.task.TaskListTasks;
import weomucat.duke.task.listener.AddTaskListener;
import weomucat.duke.task.listener.DeleteTaskListener;
import weomucat.duke.task.listener.DoneTaskListener;
import weomucat.duke.task.listener.ListTaskListener;
import weomucat.duke.ui.listener.UserInputListener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui implements AddTaskListener, DeleteTaskListener, DoneTaskListener, ListTaskListener {
	private static final String SAY_INDENTATION = "\t";
	private static final String SAY_HORIZONTAL_LINE = "============================================================";

	private ArrayList<UserInputListener> userInputListeners;
	private Scanner scanner;

	public Ui(InputStream inputStream) {
		this.userInputListeners = new ArrayList<>();
		this.scanner = new Scanner(inputStream);
	}

	public void newUserInputListener(UserInputListener listener) {
		this.userInputListeners.add(listener);
	}

	public void nextUserInput() throws DukeException {
		String userInput = this.scanner.nextLine();
		for (UserInputListener listener : userInputListeners) {
			listener.userInputUpdate(userInput);
		}
	}

	public void displayMessage(String... lines) {
		System.out.println(SAY_INDENTATION + SAY_HORIZONTAL_LINE);
		for (String line : lines) {
			System.out.println(SAY_INDENTATION + line);
		}
		System.out.println(SAY_INDENTATION + SAY_HORIZONTAL_LINE);
	}

	public void displayError(String... lines) {
		lines[0] = "\u2639 OOPS!!! " + lines[0];
		displayMessage(lines);
	}

	@Override
	public void addTaskUpdate(TaskListTasks tasks, Task task) {
		displayMessage("Got it. I've added this task:",
				task.toString(),
				String.format("Now you have %d task(s) in the list.", tasks.size()));
	}

	@Override
	public void deleteTaskUpdate(TaskListTasks tasks, Task task) {
		displayMessage("Noted. I've removed this task:",
				task.toString(),
				String.format("Now you have %d task(s) in the list.", tasks.size()));
	}

	@Override
	public void doneTaskUpdate(TaskListTasks tasks, Task task) {
		displayMessage("Nice! I've marked this task as done:", task.toString());
	}

	@Override
	public void listTaskUpdate(TaskListTasks tasks) {
		ArrayList<String> out = new ArrayList<>();
		out.add("Here are the tasks in your list:");

		for (int i = 0; i < tasks.size(); i++) {
			// Get task from tasks
			Task task = tasks.get(i);

			// Format task with no. in front
			out.add(String.format("%d. %s", i + 1, task));
		}

		displayMessage(out.toArray(new String[0]));
	}
}
