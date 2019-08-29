import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;

import java.text.ParseException;
import java.util.LinkedList;

public class Storage {
    private static String filePath;
    //private String filePath;
    private LinkedList<Task> taskList = new LinkedList<>();
    public Storage(String filePath) {
        Storage.filePath = filePath;
    }
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
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
            throw new IllegalDukeArgumentException();

        }
    }

    public void textWrite(String task, boolean isAppend) {
        File file = new File("/Users/xutunan/duke/data/duke.txt");
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
