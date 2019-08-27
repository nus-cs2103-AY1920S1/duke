import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Storage {
    final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("d/M/yyyy HHmm");
    private String filePath;
    private TaskList taskList;

    public Storage(TaskList taskList, String filePath) {
        this.filePath = filePath;
        this.taskList = taskList;
    }

    public void readTask() throws IODukeException{
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = null;

            while((line = br.readLine()) != null) {
                String[] tokens = line.split(" \\| ");
                if (tokens.length < 3) {
                    throw new IODukeException("Please enter date in this format: 2/12/2019 1800");
                }

                Task taskHolder = null;
                boolean isDone;
                String description = tokens[2];
                Date date =null;
                if (tokens[1].equals("1")) {
                    isDone = true;
                } else {
                    isDone = false;
                }
                if (tokens.length == 4) {
                    try {
                        date = DATE_FORMAT.parse(tokens[3]);
                    } catch (ParseException e) {
                        throw new IODukeException("Please enter date in this format: 2/12/2019 1800");
                    }
                }

                switch (tokens[0]) {
                case "T":
                    taskHolder = new ToDo(description, isDone);
                    break;
                case "D":
                    taskHolder = new Deadline(description, date, isDone);
                    break;
                case "E":
                    taskHolder = new Event(description, date, isDone);
                    break;
                default:
                    throw new IODukeException("Invalid task");
                }
                taskList.addTask(taskHolder);
            }

        } catch (FileNotFoundException e) {
            throw new IODukeException("File not found");
        } catch (IOException e) {
            throw new IODukeException("Error opening the file");
        }
    }

    public void saveTask() throws IODukeException{
        PrintWriter pr = null;
        try {
            pr = new PrintWriter(new BufferedWriter(new FileWriter(filePath)));

            for (int j = 0; j < taskList.getSize(); j ++) {

                int statusHolder = 0;

                if (taskList.getTask(j).getStatus()) {
                    statusHolder = 1;
                }

                String store = String.format("%s | %d | %s", taskList.getTask(j).getType(), statusHolder, taskList.getTask(j).getDescription());
                if (taskList.getTask(j) instanceof Deadline) {
                    store += " | " + ((Deadline) taskList.getTask(j)).getBy();
                } else if (taskList.getTask(j) instanceof Event) {
                    store += " | " + ((Event) taskList.getTask(j)).getAt();
                }

                store += "\n";

                pr.write(store);

            }
        } catch (IOException e){
            throw new IODukeException("File could not be saved");
        } finally {
            if (pr != null) {
                pr.close();
            }
        }
    }



}
