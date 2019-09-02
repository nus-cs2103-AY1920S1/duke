package org.duke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.duke.task.Task;
import org.duke.task.EventTask;
import org.duke.task.DeadlineTask;
import org.duke.task.TaskType;

import org.duke.json.JsonParser;
import org.duke.json.JsonWriter;
import org.duke.json.ValueHandler;

import org.duke.ui.DukeIO;
import org.duke.ui.Command;

import org.duke.util.CounterDecorator;

public class Duke {

	private static final String[] initialGreeting = new String[]{
		"Hello! I'm Duke",
		"What can I do for you?"
	};

	private DukeIO io;
	private ArrayList<Task> taskList;

	private void addTask(Task t) {
		this.taskList.add(t);
		this.io.say(
				"Got it. I've added this task:",
				"  " + t,
				String.format("Now you have %d task%s in the list.",
					this.taskList.size(),
					this.taskList.size() == 1 ? "" : "s")
				);
	}

	private boolean handleBye(Command input) {
		this.io.say("Bye. Hope to see you again soon!");
		return true;
	}

	private boolean displayList(Command input) {
		this.io.say("Here are the tasks in your list:");
		this.io.say(this.taskList.stream()
				.map(Object::toString)
				.map(new CounterDecorator(1))
				.iterator());
		return false;
	}

	private boolean makeDeadlineTask(Command input) {
		String description = input.arguments;
		String deadline = input.namedArguments.get("by");
		if(deadline == null) {
			deadline = "unknown";
		}

		DeadlineTask task = new DeadlineTask(description, deadline);

		this.addTask(task);
		return false;
	}

	private boolean makeEventTask(Command input) {
		String description = input.arguments;
		String timing = input.namedArguments.get("at");
		if(timing == null) {
			timing = "unknown";
		}

		EventTask task = new EventTask(description, timing);

		this.addTask(task);
		return false;
	}
	private boolean makeToDoTask(Command input) {
		this.addTask(new Task(input.arguments));
		return false;
	} 

	private boolean markAsDone(Command input) {
		int index;
		try {
			index = Integer.parseInt(input.arguments);
		} catch(NumberFormatException e) {
			throw new DukeException("Index provided was not an integer!", e);
		}

		if(index < 0 || index > this.taskList.size()) {
			throw new DukeException("There's no task with that index!");
		}

		Task selectedTask = this.taskList.get(index-1);
		selectedTask.markComplete();
		this.io.say("Nice! I've marked this task as done:",
			"  " + selectedTask);
		return false;
	}

	private boolean deleteTask(Command input) {
		int index;
		try {
			index = Integer.parseInt(input.arguments);
		} catch(NumberFormatException e) {
			throw new DukeException("Index provided was not an integer!", e);
		}

		if(index < 0 || index > this.taskList.size()) {
			throw new DukeException("There's no task with that index!");
		}

		Task selectedTask = this.taskList.remove(index-1);
		this.io.say("Nice! I've marked this task as done:",
			"  " + selectedTask,
			String.format("Now you have %d task%s in the list.",
				this.taskList.size(),
				this.taskList.size() == 1 ? "" : "s"));

	
		return false;
	}

	private Duke() {
		this.io = new DukeIO();
		//Bind command handlers
		this.io.bindCommand("list", this::displayList);
		this.io.bindCommand("done", this::markAsDone);
		this.io.bindCommand("bye", this::handleBye);
		this.io.bindCommand("deadline", this::makeDeadlineTask);
		this.io.bindCommand("event", this::makeEventTask);
		this.io.bindCommand("todo", this::makeToDoTask);
		this.io.bindCommand("delete", this::deleteTask);
		this.io.setUnknownCommandHandler(cmd -> { throw new DukeException("I'm sorry, but I don't know what that means. :-("); });
	}

	private static final String SAVE_PATH = "./duke.json";
	private ArrayList<Task> loadStorage() {
		try(FileReader read = new FileReader(SAVE_PATH)) {
			return JsonParser.parse(read, ValueHandler.listOf(new TaskType.Builder()));
		} catch(FileNotFoundException e) {
			return new ArrayList<>();
		} catch(IOException e) {
			throw new DukeException("Unable to load saved data", e);
		}
	}

	private void saveStorage(ArrayList<Task> tasks) {
		try(FileWriter write = new FileWriter(SAVE_PATH);
			JsonWriter jw = new JsonWriter(write)) {
			jw.writeValue(tasks);
		} catch(Exception e) {
			throw new DukeException(e);
		}
	}

	private void run() {
		this.loadStorage();
		//Start off greeting the user.
		this.io.withDialogBlock(() -> {
			this.io.say(initialGreeting);
			this.taskList = this.loadStorage();
		});

		//Start listen loop.
		this.io.listen();
		this.io.withDialogBlock(() -> {
			this.saveStorage(this.taskList);
		});
	}

    public static void main(String[] args) {
		Duke duke = new Duke();
		duke.run();
    }
}
