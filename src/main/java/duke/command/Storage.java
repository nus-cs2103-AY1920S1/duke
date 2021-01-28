package duke.command;

import duke.excaptions.IllegalDukeArgumentException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

import java.text.ParseException;

import java.util.LinkedList;

/**
 *The Storage class is one of the class in command package which
 * used to deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private static String filePath;
    //private String filePath;
    private LinkedList<Task> taskList = new LinkedList<>();
    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * method to read the information in file and convert them to Task object,
     * then add to the LinkedList abd finally return the list
     * @return LinkedList which contains the data in the text file
     * @throws IllegalDukeArgumentException
     */
    public LinkedList<Task> textRead() throws IllegalDukeArgumentException {
        try {
            FileReader reader = new FileReader("/Users/xutunan/duke/data/duke.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] fullCommand = line.split(" -- ");
                String commandType = fullCommand[0];
                String commandStatus = fullCommand[1];
                String commandContent = fullCommand[2];
                switch(commandType) {
                case "T" :
                    Task todo = new Todo(commandContent);
                    if (commandStatus.equals("\u2713")) {
                        todo.setDone();
                    }
                    taskList.add(todo);
                    break;
                case "D" :
                    Task deadline = new Deadline(commandContent, fullCommand[3]);
                    if (commandStatus.equals("\u2713")) {
                        deadline.setDone();
                    }
                    taskList.add(deadline);
                    break;
                case "E" :
                    Task event = new Event(commandContent, fullCommand[3]);
                    if (commandStatus.equals("\u2713")) {
                        event.setDone();
                    }
                    taskList.add(event);
                    break;
                }
            }
            return taskList;
        } catch (IOException | ParseException |ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            throw new IllegalDukeArgumentException();

        }
    }

    /**
     * This method write  tasks into the text file as strings
     * @param task the task that in String format
     * @param isAppend check where need append
     */
    public void textWrite(String task, boolean isAppend) {
        try {
            FileWriter writer = new FileWriter("/Users/xutunan/duke/data/duke.txt", isAppend);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(task);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
