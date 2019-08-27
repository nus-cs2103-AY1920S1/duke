import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

//"/users/junhup/desktop/duke/src/main/java/duke.txt"
public class Storage {
	protected String filePath;
	
	public Storage(String filePath) throws IOException {
		this.filePath = filePath;
	}
	
	public ArrayList<Task> load() {
		return this.readStored();
	}
	
	public void storeTodo(String input) throws EmptyDescriptionException {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true));
			if (!input.substring(4).isEmpty()) {
				String description = input.substring(4);
				Todo todo = new Todo(description);
				bw.write("T | 0 | " + description);
				bw.newLine();
				bw.flush();
				bw.close();
			} else {
				throw new EmptyDescriptionException("todo");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void storeDeadline(String input) throws EmptyDescriptionException, InvalidDescriptionException, ParseException{
		try {
			FileWriter fw = new FileWriter(this.filePath, true);
			BufferedWriter bw = new BufferedWriter(fw);
			if (input.contains("/by")) {
				int index = input.lastIndexOf("/by");
				String description = input.substring(8, index);
				String by = input.substring(index + 3);
				Date byDeadline = convertStringToDate(by);
				if (description.isBlank()) {
					throw new EmptyDescriptionException("deadline");
				}
				if (by.isBlank()) {
					throw new InvalidDescriptionException("deadline");
				}
				Deadline deadline = new Deadline(description, byDeadline);
				bw.write("D | 0 | " + description + " | " + by);
				bw.newLine();
				bw.flush();
				bw.close();
			} else {
				throw new InvalidDescriptionException("deadline");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void storeEvent(String input) throws EmptyDescriptionException, InvalidDescriptionException, ParseException{
		try {
			if (input.contains("/at")) {
				BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true));
				int index = input.lastIndexOf("/at");
				String description = input.substring(5, index);
				String at = input.substring(index + 3);
				Date atEvent = convertStringToDate(at);
				if (description.isBlank()) {
					throw new EmptyDescriptionException("event");
				}
				if (at.isBlank()) {
					throw new InvalidDescriptionException("event");
				}
				Event event = new Event(description, atEvent);
				bw.write("E | 0 | " + description + " | " + at);
				bw.newLine();
				bw.flush();
				bw.close();
			} else {
				throw new InvalidDescriptionException("event");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updateDelete(String input) throws IOException{
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String[] inputs = input.split(" ");
			int index = Integer.parseInt(inputs[1]) - 1;
			String line = null;
			int count = 0;
			ArrayList<String> stored = new ArrayList<>();
			while ((line = br.readLine()) != null) {
				if (count == index) {
					count++;
				} else {
					count++;
					stored.add(line);
				}
			}
			br.close();
			BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
			for(String s : stored) {
				bw.write(s);
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}
	
	public void updateComplete(String input) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			
			String[] inputs = input.split(" ");
			int index = Integer.parseInt(inputs[1]) - 1;
			String line = null;
			int count = 0;
			ArrayList<String> stored = new ArrayList<>();
			while ((line = br.readLine()) != null) {
				if (count == index) {
					count++;
					line = line.replaceFirst("0", "1");
					stored.add(line);
				} else {
					count++;
					stored.add(line);
				}
			}
			br.close();
			BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
			for(String s : stored) {
				bw.write(s);
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}
	
	public ArrayList<Task> readStored(){
		ArrayList<Task> tasks = new ArrayList<>();
		try {
			this.readData(tasks);
		} catch (ParseException e) {
			System.out.println("Please write your deadline/event date in this format: dd/MM/yyyy HH:mm, example: 02/08/2019 14:30\n");
		}
		return tasks;
	}
	
	public void readData(ArrayList<Task> tasksList) throws ParseException{
		String inputLine = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			while ((inputLine = br.readLine()) != null) {
				System.out.println(inputLine);
				String[] input = inputLine.split(Pattern.quote(" | "));
				String typeOfTasks = input[0];
				if (typeOfTasks.equals("T")) {
					addStoredTodo(input, tasksList);
				} else if (typeOfTasks.equals("D")) {
					addStoredDeadline(input, tasksList);
				} else {
					addStoredEvent(input, tasksList);
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void addStoredTodo(String[] input, ArrayList<Task> tasksList) {
		Todo todo = new Todo(input[2]);
		if (Integer.parseInt(input[1]) == 1) {
			todo.complete();
		}
		tasksList.add(todo);
	}
	
	public void addStoredDeadline(String[] input, ArrayList<Task> tasksList) throws ParseException{
		Deadline deadline = new Deadline(input[2], convertStringToDate(input[3]));
		if (Integer.parseInt(input[1]) == 1) {
			deadline.complete();
		}
		tasksList.add(deadline);
	}
	
	public void addStoredEvent(String[] input, ArrayList<Task> tasksList) throws ParseException{
		Event event = new Event(input[2], convertStringToDate(input[3]));
		if (Integer.parseInt(input[1]) == 1) {
			event.complete();
		}
		tasksList.add(event);
	}
	
	public Date convertStringToDate(String input) throws ParseException {
		Date result = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(input);
		return result;
	}
	
}