package duke;

import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidDescriptionException;
import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Duke.Storage class deals with loading Duke.tasks from the file and
 * saving Duke.tasks into the file.
 */
public class Storage {
    protected String filePath;
    
    /**
     * Class constructor.
     *
     * @param filePath the path of the file that is used to load and save the task into.
     * @throws IOException if the required filePath cannot be found.
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
    }
    
    /**
     * This method loads the saved Duke.tasks list from the saved file into the program.
     *
     * @return the array list of Duke.tasks that keep tracks of the Duke.tasks.
     */
    public ArrayList<Task> load() {
        return this.readStored();
    }
    
    /**
     * This method stores the to do task keyed in by the user into the file.
     *
     * @param input the description of the to do task
     * @throws EmptyDescriptionException if the description is empty for the to do task.
     */
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
    
    /**
     * This method stores the deadline task keyed in by the user into the file.
     *
     * @param input the description of the deadline task
     * @throws EmptyDescriptionException   if the description is empty for the deadline task.
     * @throws InvalidDescriptionException if the format of the deadline keyed in by the user is wrong.
     * @throws ParseException              if the date format for the deadline keyed in by the user is wrong.
     */
    public void storeDeadline(String input) throws EmptyDescriptionException, InvalidDescriptionException,
        ParseException {
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
    
    /**
     * This method stores the event task keyed in by the user into the file.
     *
     * @param input the description of the event task
     * @throws EmptyDescriptionException   if the description is empty for the event task.
     * @throws InvalidDescriptionException if the format of the event keyed in by the user is wrong.
     * @throws ParseException              if the date format for the event keyed in by the user is wrong.
     */
    public void storeEvent(String input) throws EmptyDescriptionException, InvalidDescriptionException,
        ParseException {
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
    
    /**
     * This method deletes the task as requested by the user input in the file. It rewrites
     * everything from the saved file and writes from the updated task list in the program.
     *
     * @param input the Duke.command given by the user input which includes the index of the
     *              array list that user wants to delete.
     */
    public void updateDelete(String input) {
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
            for (String s : stored) {
                bw.write(s);
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }
    
    /**
     * This method completes the task as requested by the user input in the file. It rewrites
     * everything from the saved file and writes from the updated task list in the program.
     *
     * @param input the Duke.command given by the user input which includes the index of the
     *              array list that user wants to complete.
     */
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
            for (String s : stored) {
                bw.write(s);
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }
    
    /**
     * This method reads the Duke.tasks that has been saved in the file and load the Duke.tasks into the program.
     *
     * @return a array list of Duke.tasks.
     */
    public ArrayList<Task> readStored() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            this.readData(tasks);
        } catch (ParseException e) {
            System.out.println("Please write your deadline/event date in this format: dd/MM/yyyy HH:mm, example: "
                + "02/08/2019 14:30\n");
        }
        return tasks;
    }
    
    /**
     * This method works together with readStored() method and makes sense of the data stored in the saved file
     * and load the Duke.tasks into the program.
     *
     * @param tasksList the task list to store the Duke.tasks from the saved file.
     * @throws ParseException if the data from the saved file does not follow a certain format.
     */
    public void readData(ArrayList<Task> tasksList) throws ParseException {
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
    
    /**
     * This method adds the saved to do task from the file into the program task list.
     *
     * @param input     description of the to do task.
     * @param tasksList task list in which the to do task will be added into.
     */
    public void addStoredTodo(String[] input, ArrayList<Task> tasksList) {
        Todo todo = new Todo(input[2]);
        if (Integer.parseInt(input[1]) == 1) {
            todo.complete();
        }
        tasksList.add(todo);
    }
    
    /**
     * This method adds the saved deadline task from the file into the program task list.
     *
     * @param input     description and deadline date of the deadline task.
     * @param tasksList task list in which the deadline task will be added into.
     * @throws ParseException if saved data does not follow the required format.
     */
    public void addStoredDeadline(String[] input, ArrayList<Task> tasksList) throws ParseException {
        Deadline deadline = new Deadline(input[2], convertStringToDate(input[3]));
        if (Integer.parseInt(input[1]) == 1) {
            deadline.complete();
        }
        tasksList.add(deadline);
    }
    
    /**
     * This method adds the saved event task from the file into the program task list.
     *
     * @param input     description and event date of the event task.
     * @param tasksList task list in which the event task will be added into.
     * @throws ParseException if saved data does not follow the required format.
     */
    public void addStoredEvent(String[] input, ArrayList<Task> tasksList) throws ParseException {
        Event event = new Event(input[2], convertStringToDate(input[3]));
        if (Integer.parseInt(input[1]) == 1) {
            event.complete();
        }
        tasksList.add(event);
    }
    
    /**
     * Method which converts the user input date into a Date instead of a String value.
     *
     * @param input a string input to be converted into the date following a fixed format.
     * @return Date value which has been converted from a string.
     * @throws ParseException if user did not key in the date as the requested format.
     */
    public Date convertStringToDate(String input) throws ParseException {
        Date result = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(input);
        return result;
    }
    
}