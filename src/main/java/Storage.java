
import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Storage {


    public Storage() throws IOException {}

    public void StoreData(LinkedList<Task> toStore) throws IOException {
        FileWriter fileWriter = new FileWriter("tasklist.txt");
        String fullList = "";
        for (Task task : toStore) {
            fullList = fullList + task.encodeForStorage() + "\n";
        }
        fileWriter.write(fullList);
        fileWriter.flush();
        fileWriter.close();
    }

    public LinkedList<Task> LoadData() throws IOException {
        FileReader reader = new FileReader("tasklist.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);
        TaskList storedData = new TaskList();
        Parser parser;
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            parser = new Parser(line);
            switch (parser.getCommandType()) {
            case ADDTODO:
                storedData.addTodo(parser.getDescription(), parser.isDone());
                break;
            case ADDDEADLINE:
                storedData.addDeadline(parser.getDescription(), parser.isDone(), parser.getDate());
                break;
            case ADDEVENT:
                storedData.addEvent(parser.getDescription(), parser.isDone(), parser.getDate());
            }
        }
        bufferedReader.close();
        return storedData.getTaskList();
    }

}

