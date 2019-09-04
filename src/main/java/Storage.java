import java.io.*;
import java.util.ArrayList;

public class Storage {

	String filePath;

	public Storage(String filePath) {
		this.filePath = filePath;
	}

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
        	File file = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String taskString;
            while ((taskString = br.readLine()) != null) {
                String[] taskStringArray = taskString.split("\\|");
                if (taskStringArray[0].trim().equals("T")) {
                    Task task = new ToDo(taskStringArray[2].trim());
                    if (taskStringArray[1].trim().equals("1")) {
                        task.isDone = true;
                    }
                    taskList.add(task);
                } else if (taskStringArray[0].trim().equals("D")) {
                    Task task = new Deadline(taskStringArray[2].trim(), taskStringArray[3].trim());
                    if (taskStringArray[1].trim().equals("1")) {
                        task.isDone = true;
                    }
                    taskList.add(task);
                } else {
                    Task task = new Event(taskStringArray[2].trim(), taskStringArray[3].trim());
                    if (taskStringArray[1].trim().equals("1")) {
                        task.isDone = true;
                    }
                    taskList.add(task);
                }
            }
        } catch (IOException e) {
            throw new DukeException("File not found");
        }
        return taskList;
    } 

    public void save(ArrayList<Task> list) {
    	File file = new File("/Users/sihao/Desktop/NUS AY19:20 Sem 1/CS2103/Duke/Data/Duke.txt");
        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for (Task t : list) {
                bw.write(t.taskSavedTextFormat() + "\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
