import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Storage {
    private BufferedReader br;
    private FileReader fr;
    private ArrayList<Task> inputList;
    private String filepath;

    public Storage(String path) throws Exception {
        this.filepath = path;
        this.inputList = new ArrayList<Task>();
        try {
            this.fr = new FileReader(filepath);
        } catch (FileNotFoundException error) {
            writeToFile();
            this.fr = new FileReader(filepath);
        }
        this.br = new BufferedReader(fr);
    }

    public ArrayList<Task> getList() {
        return this.inputList;
    }

    public void updateTaskList(ArrayList<Task> updatedList) {
        this.inputList = updatedList;
    }

    public void loadTasks() throws Exception {
        String next = br.readLine();
        while(next != null) {
            String[] input = next.split(" ");
            String status = input[1];
            String type = input[0];
            String description = "";
            String extraInfo = "";
            for (int i = 2; i < input.length; i ++) {
                if (i == input.length - 1) {
                    description += input[i];
                } else {
                    description += input[i];
                    description += " ";
                }
            }
            if (type.equals("todo")) {
                inputList.add(new Todo(description));
            } else if (type.equals("event")){
                extraInfo = br.readLine();
                inputList.add(new Event(description, extraInfo));
            } else if (type.equals("deadline")) {
                extraInfo = br.readLine();
                inputList.add(new Deadline(description, extraInfo));
            }
            if (status.equals("done")) {
                inputList.get(inputList.size() - 1).completeTask();

            }
            next = br.readLine();
        }
    }

    public void writeToFile() throws Exception {
        FileWriter fw = new FileWriter(filepath);
        for (Task task : inputList) {
            String output = "";
            String status = "";
            if (task.getStatus()) {
                status = "done";
            } else {
                status = "pending";
            }
            if (task.getClass().getName().equals("Todo")) {
                output = "todo " + status + " " + task.getDescription();
                fw.write(output + "\n");
            } else if (task.getClass().getName().equals("Event")){
                output = "event " + status + " " + task.getDescription();
                fw.write(output + "\n");
                fw.write(task.getExtraInfo() + "\n");
            } else {
                output = "deadline " + status + " " + task.getDescription();
                fw.write(output + "\n");
                fw.write(task.getExtraInfo() + "\n");
            }
        }
        fw.close();
    }
}
